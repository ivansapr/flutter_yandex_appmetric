#import "FlutterYandexAppmetricPlugin.h"
#import <flutter_yandex_appmetric/flutter_yandex_appmetric-Swift.h>

@implementation FlutterYandexAppmetricPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterYandexAppmetricPlugin registerWithRegistrar:registrar];
}
@end
