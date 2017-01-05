
package com.aia.stream;

import android.content.res.Configuration;
import android.graphics.Color;
import android.util.Log;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.vov.vitamio.MediaPlayer;


public class AiaStream extends CordovaPlugin implements RTMPPlayer.StreamListener {
    private String TAG = getClass().getSimpleName();
    private REStreamHelper mHelper;
    private String mRTMPAddress = "";
    private RTMPPlayer mPlayer;
    private boolean mSuccessEnable = true;
    private CallbackContext mPlayeStreamCallback;


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if (action.equals("startCameraPreview")) {
            this.onStartCameraPreview(args.getJSONObject(0), callbackContext);
            return true;
        } else if (action.equals("stopCameraPreview")) {
            this.onStopCameraPreview(args, callbackContext);
            return true;
        } else if (action.equals("startBroadcast")) {
            this.onStartBroadcast(args, callbackContext);
            return true;
        } else if (action.equals("stopBroadcast")) {
            this.onStopBroadcast(args, callbackContext);
            return true;
        } else if (action.equals("switchCamera")) {
            this.onSwitchCamera(args, callbackContext);
            return true;
        } else if (action.equals("setVideoQuality")) {
            this.onSetVideoQuality(args, callbackContext);
            return true;
        } else if (action.equals("setBroadcastConfig")) {
            this.onSetBroadcastConfig(args, callbackContext);
            return true;
        } else if (action.equals("setAudioEnable")) {
            this.onSetAudioEnable(args, callbackContext);
            return true;
        } else if (action.equals("isAudioEnable")) {
            this.isAudioEnable(args, callbackContext);
            return true;
        } else if (action.equals("isBroadcasting")) {
            this.isBroadcasting(args, callbackContext);
            return true;
        } else if (action.equals("getCameraDirection")) {
            this.onGetCameraDirection(args, callbackContext);
            return true;
        } else if (action.equals("initVideoPlayer")) {
            this.onInitVideoPlayer(args, callbackContext);
            return true;
        } else if (action.equals("stopVideoPlayer")) {
            this.onStopVideoPlayer(args, callbackContext);
            return true;
        } else if (action.equals("stopAll")) {
            this.onStopAll(args, callbackContext);
            return true;
        } else if (action.equals("playVideo")) {
            this.onPlayVideo(args, callbackContext);
            return true;
        } else if (action.equals("playDefaultVideo")) {
            this.onPlayDefaultVideo(args, callbackContext);
            return true;
        } else if (action.equals("reloadVideo")) {
            this.onReloadVideo(args, callbackContext);
            return true;
        }
        return false;
    }

    private void onSwitchCamera(JSONArray args, CallbackContext callbackContext) {
        Log.d(TAG, "onSwitchCamera: " + args.toString());
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mHelper.switchCamera();
            }
        });
    }

    private void onStopBroadcast(JSONArray args, CallbackContext callbackContext) {
        Log.d(TAG, "onStopBroadcast: " + args.toString());
        enableWebviewBackground(true);
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mHelper.stopBroadcast();
            }
        });
    }

    private void onSetVideoQuality(JSONArray args, CallbackContext callbackContext) {
        Log.d(TAG, "onSetVideoQuality: " + args.toString());
    }

    private void onSetBroadcastConfig(JSONArray args, CallbackContext callbackContext) {
        Log.d(TAG, "onSetBroadcastConfig: " + args.toString());
    }

    private void onSetAudioEnable(JSONArray args, CallbackContext callbackContext) {
        Log.d(TAG, "onSetAudioEnable: " + args.toString());
    }

    private void isAudioEnable(JSONArray args, CallbackContext callbackContext) {
        Log.d(TAG, "isAudioEnable: " + args.toString());
    }

    private void isBroadcasting(JSONArray args, CallbackContext callbackContext) {
        Log.d(TAG, "isBroadcasting: " + args.toString());
    }

    private void onGetCameraDirection(JSONArray args, CallbackContext callbackContext) {
        Log.d(TAG, "onGetCameraDirection: " + args.toString());
    }

    private void onStartBroadcast(JSONArray args, CallbackContext callbackContext) {
        Log.d(TAG, "onStartBroadcast: " + args.toString());
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mHelper != null) {
                    mHelper.startStreaming();
                }
            }
        });
    }

    private void enableWebviewBackground(boolean enable) {
        webView.getView().setBackgroundColor(enable ? Color.WHITE : 0x00000000);
    }

    private void onStartCameraPreview(JSONObject args, CallbackContext callbackContext) {

        this.mRTMPAddress = "";
        try {
            mRTMPAddress = args.getString("rtmp");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (mRTMPAddress == "") {
            callbackContext.error("Không tìm thấy địa chỉ server");
        } else {
            enableWebviewBackground(false);
            callbackContext.success("Start camera success");
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mHelper == null) {
                        mHelper = new REStreamHelper(cordova.getActivity());
                        mHelper.init();
                    }
                    mHelper.prepare(mRTMPAddress);
                }
            });
        }
        Log.d(TAG, "onStartCameraPreview: " + mRTMPAddress);
    }

    private void onStopCameraPreview(JSONArray args, CallbackContext callbackContext) {
        Log.d(TAG, "onStopCameraPreview: " + args.toString());
    }

    private void onReloadVideo(JSONArray args, CallbackContext callbackContext) {
        Log.d(TAG, "onReloadVideo: ");

        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPlayer.reloadStream();
            }
        });
    }
     private void onPlayDefaultVideo(JSONArray args, CallbackContext callbackContext) {
        this.mPlayeStreamCallback = callbackContext;
        mSuccessEnable = true;
        enableWebviewBackground(false);
        try {
            JSONObject object = args.getJSONObject(0);
            final String url = object.getString("url");
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mPlayer.setStreamUrl(url);
                    mPlayer.playStream();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "onPlayDefaultVideo: " + args.toString());
    }

    private void onPlayVideo(JSONArray args, CallbackContext callbackContext) {

        this.mPlayeStreamCallback = callbackContext;
        mSuccessEnable = true;
        enableWebviewBackground(false);
        try {
            JSONObject object = args.getJSONObject(0);
            final String url = object.getString("url");
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mPlayer.setStreamUrl(url);
                    mPlayer.playStream();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "onPlayVideo: " + args.toString());
    }

    private void onStopVideoPlayer(JSONArray args, CallbackContext callbackContext) {
        Log.d(TAG, "onStopVideoPlayer: ");
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                enableWebviewBackground(true);
                mPlayer.stopStream();
            }
        });
    }
    private void onStopAll(JSONArray args, CallbackContext callbackContext) {
        Log.d(TAG, "onStopAll: ");        
    }

    private void onInitVideoPlayer(JSONArray args, CallbackContext callbackContext) {
        Log.d(TAG, "onInitVideoPlayer: ");
        if (this.mPlayer == null) {
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mPlayer = new RTMPPlayer(cordova.getActivity());
                    mPlayer.initVideoPlayer();
                    mPlayer.setStreamListener(AiaStream.this);
                }
            });

        }
    }

    @Override
    public void onStreamState(int state) {
        if (state == MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED && mSuccessEnable) {
            if (this.mPlayeStreamCallback != null) {
                this.mPlayeStreamCallback.success();
                Log.d(TAG, "onStreamState: play sucess");
                this.mPlayeStreamCallback = null;
            }
            mSuccessEnable = false;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.mPlayer.onConfigurationChanged(newConfig);
    }

    @Override
    public void onResume(boolean multitasking) {
        super.onResume(multitasking);
        this.mPlayer.onResume();
    }

    @Override
    public void onDestroy() {
        if (this.mHelper != null) this.mHelper.onDestroy();
        if (this.mPlayer != null) this.mPlayer.onDestroy();
    }
}
