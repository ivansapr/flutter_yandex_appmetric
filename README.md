# flutter_yandex_appmetric

A new flutter plugin project.

## Getting Started

To use this plugin, add flutter_yandex_appmetric as a dependency in your pubspec.yaml file.

## Usage


```dart

  void initState() {
    super.initState();
    FlutterYandexAppmetric.init(apiKey: 'appKey');
  }


  void onTap() {
      FlutterYandexAppmetric.reportCustomEvent(eventName: 'button_tap');
  }
```