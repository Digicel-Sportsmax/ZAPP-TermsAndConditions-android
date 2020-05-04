package com.sportsmax.termsandconditions_android

import android.content.Context
import android.content.Intent
import com.applicaster.plugin_manager.hook.ApplicationLoaderHookUpI
import com.applicaster.plugin_manager.hook.HookListener
import com.applicaster.session.SessionStorageUtil

class StartUpAdapter: ApplicationLoaderHookUpI {

    override fun executeOnStartup(context: Context?, listener: HookListener?) {

    }

    override fun executeOnApplicationReady(context: Context?, listener: HookListener?) {
        if(UiHelper.getValue(SHOW_SCREEN_AT_LAUNCH) == "1"){
            val isTermsAndConditionsAccepted = SessionStorageUtil.get(ACCEPTED_TERMS_AND_CONDITIONS, PLUGIN_NAME)
            if(isTermsAndConditionsAccepted != "1"){
                val intent = Intent(context!!, TermsAndConditionActivity::class.java)
                context.startActivity(intent)
            }
        }
    }

    override fun setPluginConfigurationParams(params: MutableMap<Any?, Any?>?) {
        PluginConfigurationHelper.setConfigurationMap(params as Map<String, String>)
    }
}