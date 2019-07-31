package com.example.flutter_yandex_appmetric

import android.util.Log
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class FlutterYandexAppmetricPlugin(registrar: Registrar) : MethodCallHandler {

    private var delegate: FlutterAppMetricaDelegate

    init {
        Log.d("APPMETRICA PLUGIN", "_________________________CALL CONSTRUCTOR")
        delegate = FlutterAppMetricaDelegate(registrar)
    }

    companion object {
        private const val CHANNEL_NAME: String = "com.kf.flutter_yandex_appmetric"

        const val INITIALIZE_ACTION: String = "initialize"
        const val REPORT_CUSTOM_EVENT: String = "report_custom_event"
        const val GET_PLATFORM_VERSION: String = "getPlatformVersion"

        const val API_KEY: String = "API_KEY"
        const val EVENT_NAME: String = "EVENT_NAME"

        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), CHANNEL_NAME)
            channel.setMethodCallHandler(FlutterYandexAppmetricPlugin(registrar))
        }
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        Log.d("APPMETRICA PLUGIN", "________________________CALL METHOD: ${call.method}")

        when (call.method) {
            INITIALIZE_ACTION -> {
                val apiKey: String? = call.argument(API_KEY)
                delegate.init(apiKey as String)
            }
            REPORT_CUSTOM_EVENT -> {
                val eventName: String? = call.argument(EVENT_NAME)
                delegate.sendCustomEvent(eventName as String)
            }
            GET_PLATFORM_VERSION -> {
                result.success("Android ${android.os.Build.VERSION.RELEASE}")
            }
            else ->
                result.notImplemented()
        }

    }
}
