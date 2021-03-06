package com.sportsmax.termsandconditions_android

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.text.Html
import android.util.Log
import android.util.TypedValue
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.applicaster.app.CustomApplication
import com.applicaster.util.StringUtil

class ConfigurationUiHelper {
    companion object {
        private val TAG = "UiHelper"

        @JvmStatic
        fun updateTextViewText(textView: TextView?, key: String, isHtml: Boolean = false) {
            if (textView != null) {
                var textValue = ConfigurationHelper.getConfigurationValue(key)
                textValue = if (StringUtil.isNotEmpty(textValue)) textValue else key
                if (isHtml) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        textView.text = Html.fromHtml(textValue, Html.FROM_HTML_MODE_LEGACY)
                    } else {
                        textView.text = Html.fromHtml(textValue)
                    }
                } else {
                    textView.text = textValue
                }
            }
        }

        @JvmStatic
        fun updateCheckboxStyle(checkbox: CheckBox?, text: String) {
            checkbox?.let {
                var textValue = ConfigurationHelper.getConfigurationValue(text)
                textValue = if (StringUtil.isNotEmpty(textValue)) textValue else text
                checkbox.text = textValue
            }
        }

        @JvmStatic
        fun updateButtonStyle(checkboxButtonAccepted: Boolean, button: Button?, text: String, backgroundColor: String, textColor: String) {
            button?.let {
                var textValue = ConfigurationHelper.getConfigurationValue(text)
                textValue = if (StringUtil.isNotEmpty(textValue)) textValue else text

                var textColorValue = ConfigurationHelper.getConfigurationValue(textColor)
                textColorValue = if (StringUtil.isNotEmpty(textColorValue)) textColorValue else "#FFFFFF"

                var bgColorValue = ConfigurationHelper.getConfigurationValue(backgroundColor)
                bgColorValue = if (StringUtil.isNotEmpty(bgColorValue)) bgColorValue else "#356bac"

                if (!checkboxButtonAccepted) {
                    textColorValue = "#FFFFFF"
                    bgColorValue = "#C0C0C0"
                    button.isEnabled = false
                    button.isClickable = false
                } else {
                    button.isEnabled = true
                    button.isClickable = true
                }

                button.text = textValue
                button.setTextColor(Color.parseColor(textColorValue))
                button.setBackgroundColor(Color.parseColor(bgColorValue))
            }

        }

        @JvmStatic
        fun getValue(resource: String): String? {
            var resourceValue = ConfigurationHelper.getConfigurationValue(resource)
            resourceValue = if (StringUtil.isNotEmpty(resourceValue)) resourceValue else resource
            return resourceValue
        }

        @JvmStatic
        fun updateToolbarBackgroundColor(toolbar: Toolbar?, backgroundColor: String) {
            toolbar.let {
                var bgColorValue = ConfigurationHelper.getConfigurationValue(backgroundColor)
                bgColorValue = if (StringUtil.isNotEmpty(bgColorValue)) bgColorValue else "#356bac"
                it?.setBackgroundColor(Color.parseColor(bgColorValue))
            }
        }

        @JvmStatic
        fun updateTextViewFont(textView: TextView?, key: String) {
            var fontName = ConfigurationHelper.getConfigurationValue(key)
            if (textView != null && StringUtil.isNotEmpty(fontName)) {
                var typeface = getFontFromAssets(fontName, ".otf")
                typeface?.let {
                    textView.typeface = typeface
                    return
                }
                typeface = getFontFromAssets(fontName, ".ttf")
                typeface?.let {
                    textView.typeface = typeface
                    return
                }
            }
        }

        @JvmStatic
        fun updateTextViewSize(textView: TextView?, key: String) {
            var size: Float = 15F
            try {
                var sizeInPixel = ConfigurationHelper.getConfigurationValue(key)
                sizeInPixel?.let {
                    size = sizeInPixel?.toFloat();
                }
            } catch (err: Exception) {
                Log.d(TAG, "key: ${key} couldn't be paresed to float.")
            }

            if (textView != null) {
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
            }
        }

        @JvmStatic
        fun updateTextViewColor(textView: TextView?, key: String) {
            if (textView != null) {
                var colorValue = ConfigurationHelper.getConfigurationValue(key)
                colorValue = if (StringUtil.isNotEmpty(colorValue)) colorValue else "#000000"
                textView.setTextColor(Color.parseColor(colorValue))
            }
        }


        @JvmStatic
        fun getFontFromAssets(fontName: String?, suffix: String?): Typeface? {
            if (StringUtil.isNotEmpty(fontName)) {
                try {
                    var customFontTypeface = Typeface.createFromAsset(CustomApplication.getAppContext().assets, "fonts/$fontName$suffix")
                    return customFontTypeface
                } catch (e: Exception) {
                    Log.d("", "")
                }
            }
            return null
        }

    }
}