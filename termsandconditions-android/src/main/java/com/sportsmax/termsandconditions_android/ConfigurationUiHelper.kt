package com.sportsmax.termsandconditions_android

import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import android.util.TypedValue
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.applicaster.app.CustomApplication
import com.applicaster.util.StringUtil

class ConfigurationUiHelper {
    companion object {
        private val TAG = "UiHelper"

        @JvmStatic
        fun updateTextViewText(textView: TextView?, key: String) {
            if (textView != null) {
                var textValue = ConfigurationHelper.getConfigurationValue(key)
                textValue = if (StringUtil.isNotEmpty(textValue)) textValue else key
                textView.text = textValue
            }
        }

        @JvmStatic
        fun updateButtonStyle(button: Button?, text: String, backgroundColor: String, textColor: String) {
            if (button != null) {
                var textValue = ConfigurationHelper.getConfigurationValue(text)
                textValue = if (StringUtil.isNotEmpty(textValue)) textValue else text
                button.text = textValue

                var textColorValue = ConfigurationHelper.getConfigurationValue(textColor)
                textColorValue = if (StringUtil.isNotEmpty(textColorValue)) textColorValue else "#000000"
                button.setTextColor(Color.parseColor(textColorValue))

                var bgColorValue = ConfigurationHelper.getConfigurationValue(backgroundColor)
                bgColorValue = if (StringUtil.isNotEmpty(bgColorValue)) bgColorValue else "#000000"
                button.setBackgroundColor(Color.parseColor(bgColorValue))
            }
        }

        @JvmStatic
        fun getValue(resource: String) : String? {
            var resourceValue = ConfigurationHelper.getConfigurationValue(resource)
            resourceValue = if (StringUtil.isNotEmpty(resourceValue)) resourceValue else resource
            return resourceValue
        }

        @JvmStatic
        fun updateToolbarBackgroundColor(toolbar: Toolbar?, backgroundColor: String) {
            toolbar.let {
                var bgColorValue = ConfigurationHelper.getConfigurationValue(backgroundColor)
                bgColorValue = if (StringUtil.isNotEmpty(bgColorValue)) bgColorValue else "#000000"
                it?.setBackgroundColor(Color.parseColor(bgColorValue))
            }
        }

        @JvmStatic
        fun updateTextViewFont(textView: TextView?, key: String) {
            var fontName = ConfigurationHelper.getConfigurationValue(key)
            if (textView != null && StringUtil.isNotEmpty(fontName)) {
                var typeface = getFontFromAssets(fontName,".otf")
                typeface?.let {
                    textView.typeface = typeface
                    return
                }
                typeface = getFontFromAssets(fontName,".ttf")
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
            }catch (err:Exception){
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