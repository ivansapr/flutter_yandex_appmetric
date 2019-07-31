package com.example.flutter_yandex_appmetric

import android.app.Application
import android.content.Intent
import android.util.Log
import io.flutter.plugin.common.PluginRegistry
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import java.lang.Exception


class FlutterAppMetricaDelegate constructor(val registrar: PluginRegistry.Registrar) : PluginRegistry.ActivityResultListener {
    override fun onActivityResult(p0: Int, p1: Int, p2: Intent?): Boolean {
        return true
    }

    init {
        registrar.addActivityResultListener(this)
    }

    fun init(API_KEY: String): Boolean {
        try {
            val config = YandexMetricaConfig.newConfigBuilder(API_KEY).withLogs().build()
            // Initializing the AppMetrica SDK.
            YandexMetrica.activate(registrar.context(), config)
            // Automatic tracking of user activity.
            YandexMetrica.enableActivityAutoTracking(Application())
        } catch (e: Exception) {
            Log.e("APPMETRICA PLUGIN", e.message)
            return false
        }
        return true
    }

    fun sendCustomEvent(eventName: String): Boolean {
        try {
            YandexMetrica.reportEvent(eventName)
        } catch (e: Exception) {
            Log.e("APPMETRICA CUSTOM_EVENT", e.message)
            return false
        }
        return true
    }

}
