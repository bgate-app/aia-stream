
package com.aia.stream;

import android.util.Log;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;

public class AiaStream extends CordovaPlugin {
    private String TAG = getClass().getSimpleName();
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("init")) {
            this.onInit();
            return true;
        }else if (action.equals("startCameraPreview")) {
            this.onStartCameraPreview(args, callbackContext);
            return true;
        }else if (action.equals("stopCameraPreview")) {
            this.onStopCameraPreview(args, callbackContext);
            return true;
        }else if (action.equals("startBroadcast")) {
            this.onStartBroadcast(args, callbackContext);
            return true;
        }else if (action.equals("stopBroadcast")) {
            this.onStopBroadcast(args, callbackContext);
            return true;
        }else if (action.equals("switchCamera")) {
            this.onSwitchCamera(args, callbackContext);
            return true;
        }else if (action.equals("setVideoQuality")) {
            this.onSetVideoQuality(args, callbackContext);
            return true;
        }else if (action.equals("setBroadcastConfig")) {
            this.onSetBroadcastConfig(args, callbackContext);
            return true;
        }else if (action.equals("setAudioEnable")) {
            this.onSetAudioEnable(args, callbackContext);
            return true;
        }else if (action.equals("isAudioEnable")) {
            this.isAudioEnable(args, callbackContext);
            return true;
        }else if (action.equals("isBroadcasting")) {
            this.isBroadcasting(args, callbackContext);
            return true;
        }else if (action.equals("getCameraDirection")) {
            this.onGetCameraDirection(args, callbackContext);
            return true;
        } else if (action.equals("initVideoPlayer")) {
            this.onInitVideoPlayer(args, callbackContext);
            return true;
        } else if (action.equals("stopVideoPlayer")) {
            this.onStopVideoPlayer(args, callbackContext);
            return true;
        } else if (action.equals("playVideo")) {
            this.onPlayVideo(args, callbackContext);
            return true;
        } else if (action.equals("reloadVideo")) {
            this.onReloadVideo(args, callbackContext);
            return true;
        }
        return false;
    }

    private void onInit() {
        Log.d(TAG, "onInit: ");
    }

    private void onSwitchCamera(JSONArray args, CallbackContext callbackContext) {
        Log.d(TAG, "onSwitchCamera: "+args.toString());
    }

    private void onStopBroadcast(JSONArray args, CallbackContext callbackContext) {
        Log.d(TAG, "onStopBroadcast: "+args.toString());
    }

    private void onSetVideoQuality(JSONArray args, CallbackContext callbackContext) {
        Log.d(TAG, "onSetVideoQuality: "+ args.toString());
    }

    private void onSetBroadcastConfig(JSONArray args, CallbackContext callbackContext) {
        Log.d(TAG, "onSetBroadcastConfig: "+args.toString());
    }

    private void onSetAudioEnable(JSONArray args, CallbackContext callbackContext) {
        Log.d(TAG, "onSetAudioEnable: "+args.toString());
    }

    private void isAudioEnable(JSONArray args, CallbackContext callbackContext) {
        Log.d(TAG, "isAudioEnable: "+args.toString());
    }

    private void isBroadcasting(JSONArray args, CallbackContext callbackContext) {
        Log.d(TAG, "isBroadcasting: "+args.toString());
    }

    private void onGetCameraDirection(JSONArray args, CallbackContext callbackContext) {
        Log.d(TAG, "onGetCameraDirection: "+args.toString());
    }

    private void onStartBroadcast(JSONArray args, CallbackContext callbackContext) {
        Log.d(TAG, "onStartBroadcast: "+args.toString());
    }

    private void onStartCameraPreview(JSONArray args, CallbackContext callbackContext){
        Log.d(TAG, "onStartCameraPreview: "+args.toString());
    }
    private void onStopCameraPreview(JSONArray args, CallbackContext callbackContext){
        Log.d(TAG, "onStopCameraPreview: "+args.toString());
    }

    private void startStream( CallbackContext callbackContext) {
        Log.d(TAG, "startStream: ");
    }
    private void stopStream( CallbackContext callbackContext) {
        Log.d(TAG, "stopStream: ");
    }

    private void onReloadVideo(JSONArray args, CallbackContext callbackContext) {
          Log.d(TAG, "onReloadVideo: ");
    }

    private void onPlayVideo(JSONArray args, CallbackContext callbackContext) {
        Log.d(TAG, "onPlayVideo: " + args.toString());
    }

    private void onStopVideoPlayer(JSONArray args, CallbackContext callbackContext) {
         Log.d(TAG, "onStopVideoPlayer: ");
    }

    private void onInitVideoPlayer(JSONArray args, CallbackContext callbackContext) {
          Log.d(TAG, "onInitVideoPlayer: ");
    }

}
