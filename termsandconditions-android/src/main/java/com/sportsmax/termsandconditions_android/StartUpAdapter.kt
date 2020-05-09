package com.sportsmax.termsandconditions_android

import android.content.Context
import android.content.Intent
import android.util.Log
import com.applicaster.plugin_manager.hook.ApplicationLoaderHookUpI
import com.applicaster.plugin_manager.hook.HookListener
import com.applicaster.storage.LocalStorage

class StartUpAdapter: ApplicationLoaderHookUpI {
    companion object {
        var hookListener: HookListener? = null
    }
    override fun executeOnStartup(context: Context?, listener: HookListener?) {
        Log.wtf("** executeOnStartup","executeOnStartup")
        hookListener = listener
        Log.wtf("** hookListener","null? " + (hookListener == null))
        if(ConfigurationUiHelper.getValue(SHOW_SCREEN_AT_LAUNCH) == "1"){
            Log.wtf("** show"," screen")
            val isTermsAndConditionsAccepted = LocalStorage.storageRepository.get(ACCEPTED_TERMS_AND_CONDITIONS, PLUGIN_NAME)
            Log.wtf("** isTermsAndConditionsAccepted", "is  $isTermsAndConditionsAccepted")
            if(isTermsAndConditionsAccepted != "1"){
                val intent = Intent(context!!, TermsAndConditionActivity::class.java)
                context.startActivity(intent)
            }else{
                Log.wtf("** finish"," listener")
                listener?.onHookFinished()
            }
        }else{
            Log.wtf("** finish"," listener")
            listener?.onHookFinished()
        }
    }

    override fun executeOnApplicationReady(context: Context?, listener: HookListener?) {
        Log.wtf("** executeOnApplicationReady","executeOnApplicationReady")
        listener?.onHookFinished()
    }

    override fun setPluginConfigurationParams(params: MutableMap<Any?, Any?>?) {
        Log.wtf("** setPluginConfigurationParams","setPluginConfigurationParams")
        ConfigurationHelper.setConfigurationMap(params as Map<String, String>)
    }
}