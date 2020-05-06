package com.sportsmax.termsandconditions_android

object ConfigurationHelper {

    private var pluginConfiguration: HashMap<String, String> = HashMap()

    fun setConfigurationMap(pluginConfiguration: Map<String, String>) {
        this.pluginConfiguration.putAll(pluginConfiguration)
    }

    fun getConfigurationValue(key: String): String? {
        return this.pluginConfiguration[key]
    }
}