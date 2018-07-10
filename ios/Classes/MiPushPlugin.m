#import "MiPushPlugin.h"
#import <mi_push/mi_push-Swift.h>

@implementation MiPushPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftMiPushPlugin registerWithRegistrar:registrar];
}
@end
