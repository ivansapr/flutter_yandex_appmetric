import Flutter
import UIKit
import YandexMobileMetrica

enum Methods: String {
    case INITIALIZE_ACTION = "initialize"
    case REPORT_CUSTOM_EVENT = "report_custom_event"
    case API_KEY = "API_KEY"
    case EVENT_NAME = "EVENT_NAME"
}

public class SwiftFlutterYandexAppmetricPlugin: NSObject, FlutterPlugin {
    
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "com.kf.flutter_yandex_appmetric", binaryMessenger: registrar.messenger())
    let instance = SwiftFlutterYandexAppmetricPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    switch call.method {
    case Methods.INITIALIZE_ACTION.rawValue:
        if let apiKey: String  = getArgument(Methods.API_KEY.rawValue, from: call.arguments) {
            YandexAppmetricDelegate.initialize(apiKey)
        }
    case Methods.REPORT_CUSTOM_EVENT.rawValue:
        if let eventName: String  = getArgument(Methods.EVENT_NAME.rawValue, from: call.arguments) {
            YandexAppmetricDelegate.reportCustomEvent(eventName)
        }

    default:
      result("iOS " + UIDevice.current.systemVersion)
    }
  }
    
    private func getArgument<T>(_ name: String, from arguments: Any?) -> T? {
        guard let arguments = arguments as? [String: Any] else { return nil }
        return arguments[name] as? T
    }

}

class YandexAppmetricDelegate  {
    public static func initialize(_ apiKey: String){
        // Initializing the AppMetrica SDK.
        let configuration = YMMYandexMetricaConfiguration.init(apiKey: apiKey)
        configuration?.logs = true
        YMMYandexMetrica.activate(with: configuration!)
    }

    public static func reportCustomEvent(_ eventName: String){
          YMMYandexMetrica.reportEvent(eventName, onFailure: { (error) in
              print("DID FAIL REPORT EVENT: %@", eventName)
              print("REPORT ERROR: %@", error.localizedDescription)
          })
    }
}
