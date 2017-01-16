#import <Cordova/CDV.h>
#import <Cordova/CDVPlugin.h>
#import <Cordova/CDVInvokedUrlCommand.h>

#import "CameraSessionManager.h"
#import "CameraRenderController.h"

@interface CameraPreview : CDVPlugin <TakePictureDelegate>

- (void) startCamera:(CDVInvokedUrlCommand*)command;
- (void) stopCamera:(CDVInvokedUrlCommand*)command;
- (void) showCamera:(CDVInvokedUrlCommand*)command;
- (void) hideCamera:(CDVInvokedUrlCommand*)command;
- (void) switchCamera:(CDVInvokedUrlCommand*)command;
- (void) takePicture:(CDVInvokedUrlCommand*)command;
- (void) setOnPictureTakenHandler:(CDVInvokedUrlCommand*)command;
- (void) setColorEffect:(CDVInvokedUrlCommand*)command;

- (void) invokeTakePicture:(CGFloat) maxWidth withHeight:(CGFloat) maxHeight;
- (void) invokeTakePicture;

- (void) initBroadcast:(CDVInvokedUrlCommand*)command;
- (void) startCameraPreview:(CDVInvokedUrlCommand*)command;
- (void) stopCameraPreview:(CDVInvokedUrlCommand*)command;
- (void) startBroadcast:(CDVInvokedUrlCommand*)command;
- (void) stopBroadcast:(CDVInvokedUrlCommand*)command;
- (void) stopBroadcastAndPreview:(CDVInvokedUrlCommand*)command;
- (void) setFilter:(CDVInvokedUrlCommand*)command;
- (void) setAudioEnable:(CDVInvokedUrlCommand*)command;


@property (nonatomic) CameraSessionManager *sessionManager;
@property (nonatomic) CameraRenderController *cameraRenderController;
@property (nonatomic) NSString *onPictureTakenHandlerId;

@end
