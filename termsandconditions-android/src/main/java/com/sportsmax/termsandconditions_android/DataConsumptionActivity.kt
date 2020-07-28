package com.sportsmax.termsandconditions_android

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.applicaster.activities.base.APBaseActivity
import com.applicaster.storage.LocalStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_data_consumption.*
import kotlinx.android.synthetic.main.activity_terms_and_condition.*


/**
 *
 * Created by Lucas Farias on 7/28/20.
 * Copyright © 2019 CME. All rights reserved.
 *
 */
class DataConsumptionActivity : APBaseActivity() {

    private var toolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_consumption)

        initStyles()
    }

    private fun initStyles(){
        toolbar = findViewById(R.id.dc_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val title = findViewById<TextView>(R.id.tv_dcTitle)
        val logo = findViewById<ImageView>(R.id.dc_logo_sportsmax)
        val tvDataConsumption = findViewById<TextView>(R.id.tv_dataConsumption)
        tvDataConsumption.movementMethod = LinkMovementMethod.getInstance()

        ConfigurationUiHelper.updateTextViewText(tvDataConsumption, DATA_CONSUMPTION_TEXT, true)
        ConfigurationUiHelper.updateTextViewText(title, NAVIGATION_HEADER_TEXT)
        ConfigurationUiHelper.updateButtonStyle(checkbox_agreeDataConsumption.isChecked, btn_agreeDataConsumption, text = DATA_CONSUMPTION_BUTTON_TEXT, backgroundColor = AGREE_NEXT_BUTTON_BG_COLOR, textColor = AGREE_NEXT_BUTTON_TEXT_COLOR)
        ConfigurationUiHelper.updateCheckboxStyle(checkbox_agreeDataConsumption, DATA_CONSUMPTION_CHECKBOX_TEXT)
        ConfigurationUiHelper.updateToolbarBackgroundColor(toolbar = toolbar, backgroundColor = NAVIGATION_HEADER_BACKGROUND)
        val imageUrl = ConfigurationUiHelper.getValue(NAVIGATION_HEADER_IMAGE)

        if(!imageUrl.isNullOrEmpty()){
            title.visibility = View.INVISIBLE
            Picasso.get().load(imageUrl).into(logo)
        }else{
            if(title.text.isNullOrEmpty()){
                title.text = getString(R.string.terms_and_conditions)
            }
        }

        checkbox_agreeDataConsumption.setOnCheckedChangeListener { compoundButton, b ->
            ConfigurationUiHelper.updateButtonStyle(checkbox_agreeDataConsumption.isChecked, btn_agreeDataConsumption, text = DATA_CONSUMPTION_BUTTON_TEXT, backgroundColor = AGREE_NEXT_BUTTON_BG_COLOR, textColor = AGREE_NEXT_BUTTON_TEXT_COLOR)
        }

        btn_agreeDataConsumption.setOnClickListener {
            LocalStorage.storageRepository.set(ACCEPTED_TERMS_AND_CONDITIONS_DATA_CONSENT, "1", PLUGIN_NAME)
            StartUpAdapter.hookListener?.onHookFinished()
            this.finish()
        }
    }

    override fun onBackPressed() {}

}