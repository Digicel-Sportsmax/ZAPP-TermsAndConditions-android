package com.sportsmax.termsandconditions_android

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import coil.api.load
import com.applicaster.session.SessionStorageUtil


class TermsAndConditionActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_and_condition)

        initStyles()
    }

    private fun initStyles(){
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val title = findViewById<TextView>(R.id.tv_title)
        val logo = findViewById<ImageView>(R.id.logo_sportsmax)
        val tvTermsAndConditions = findViewById<TextView>(R.id.tv_termsAndConditions)
        val btnAccept = findViewById<Button>(R.id.btn_agreeAndContinue)

        ConfigurationUiHelper.updateTextViewText(tvTermsAndConditions, TERMS_AND_CONDITIONS_TEXT)
        ConfigurationUiHelper.updateTextViewText(title, NAVIGATION_HEADER_TEXT)
        ConfigurationUiHelper.updateButtonStyle(btnAccept, text = AGREE_BUTTON_TEXT, backgroundColor = AGREE_BUTTON_BG_COLOR, textColor = AGREE_BUTTON_TEXT_COLOR)
        ConfigurationUiHelper.updateToolbarBackgroundColor(toolbar = toolbar, backgroundColor = NAVIGATION_HEADER_BACKGROUND)

        val imageUrl = ConfigurationUiHelper.getValue(NAVIGATION_HEADER_IMAGE)
        logo.load(imageUrl)

        btnAccept.setOnClickListener {
            SessionStorageUtil.set(ACCEPTED_TERMS_AND_CONDITIONS, "1", PLUGIN_NAME)
            this.finish()
        }
    }
}
