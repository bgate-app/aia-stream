
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
    enum ViewType {
        NONE, STREAM_BROADCAST, STREAM_PLAYER
    }

    ViewType mViewType = ViewType.NONE;
    private String mRTMPAddress = "";
    private RTMPPlayer mPlayer;
    private RTMPClientHelper mBroadcast;
    private boolean mSuccessEnable = true;
    private CallbackContext mPlayerStreamCallback;

    public void setViewType(ViewType newType) {
        this.mViewType = newType;
        if (this.mViewType == ViewType.NONE) {
            if (this.mBroadcast != null) this.mBroadcast.setVisible(false);
            if (this.mPlayer != null) this.mPlayer.setVisible(false);
            this.enableWebviewBackground(true);
        } else if (this.mViewType == ViewType.STREAM_BROADCAST) {
            if (this.mBroadcast != null) this.mBroadcast.setVisible(true);
            if (this.mPlayer != null) this.mPlayer.setVisible(false);
            this.enableWebviewBackground(false);
        } else if (this.mViewType == ViewType.STREAM_PLAYER) {
            if (this.mBroadcast != null) this.mBroadcast.setVisible(false);
            if (this.mPlayer != null) this.mPlayer.setVisible(true);
            this.enableWebviewBackground(false);
        }
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("initAll")) {
            this.onInitAll(args, callbackContext);
            return true;
        } else if (action.equals("initBroadcast")) {
            this.onInitBroadcast(args, callbackContext);
            return true;
        } else if (action.equals("startCameraPreview")) {
            this.onStartCameraPreview(args, callbackContext);
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
        } else if (action.equals("stopBroadcastAndPreview")) {
            this.onStopBroadcastAndPreview(args, callbackContext);
            return true;
        } else if (action.equals("switchCamera")) {
            this.onSwitchCamera(args, callbackContext);
            return true;
        } else if (action.equals("setFilter")) {
            this.onSetFilter(args, callbackContext);
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
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBroadcast.switchCamera();
            }
        });
    }

    private void onStopBroadcast(JSONArray args, CallbackContext callbackContext) {
        if (this.mBroadcast != null) {
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mBroadcast.stopBroadcast();
                    AiaStream.this.setViewType(ViewType.STREAM_BROADCAST);
                }
            });
        }

    }

    private void onStopBroadcastAndPreview(JSONArray args, CallbackContext callbackContext) {
        if (this.mBroadcast != null) {
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mBroadcast.stopBroadcastAndPreview();
                    AiaStream.this.setViewType(ViewType.NONE);
                }
            });
        }
    }


    private void onSetFilter(JSONArray args, CallbackContext callbackContext) {
        try {
            JSONObject data = args.getJSONObject(0);
            final int filter = data.getInt("filter");
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mBroadcast.setFilter(filter);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
            callbackContext.error("Có lỗi xảy ra");
        }
    }

    private void onSetAudioEnable(JSONArray args, CallbackContext callbackContext) {
        try {
            JSONObject data = args.getJSONObject(0);
            final boolean enable = data.getBoolean("audio_enable");
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mBroadcast.setAudioEnable(enable);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
            callbackContext.error("Có lỗi xảy ra");
        }
    }

    private void isAudioEnable(JSONArray args, CallbackContext callbackContext) {

    }

    private void isBroadcasting(JSONArray args, CallbackContext callbackContext) {

    }

    private void onGetCameraDirection(JSONArray args, CallbackContext callbackContext) {

    }

    private void onStartBroadcast(JSONArray args, CallbackContext callbackContext) {
        if (mBroadcast != null) mBroadcast.setCallback(callbackContext);
        try {
            final JSONObject data = args.getJSONObject(0);
            mRTMPAddress = data.getString("rtmp");
            if (mRTMPAddress == "") {
                callbackContext.error("Không tìm thấy địa chỉ server");
            } else {
                cordova.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mBroadcast.startBroadcast(mRTMPAddress);
                        if (data.has("audio_enable")) {
                            try {
                                boolean audioEnable = data.getBoolean("audio_enable");
                                mBroadcast.setAudioEnable(audioEnable);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        if (data.has("filter")) {
                            try {
                                int filter = data.getInt("filter");
                                mBroadcast.setFilter(filter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
            callbackContext.error("Có lỗi xảy ra");
        }
    }

    private void enableWebviewBackground(boolean enable) {
        webView.getView().setBackgroundColor(enable ? Color.WHITE : 0x00000000);
    }

    private void onStartCameraPreview(JSONArray args, CallbackContext callbackContext) {
        callbackContext.success("Start camera success");
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBroadcast.startCameraPreview();
                AiaStream.this.setViewType(ViewType.STREAM_BROADCAST);
            }
        });
    }

    private void onStopCameraPreview(JSONArray args, CallbackContext callbackContext) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBroadcast.stopBroadcastAndPreview();
                AiaStream.this.setViewType(ViewType.NONE);
            }
        });
    }

    private void onReloadVideo(JSONArray args, CallbackContext callbackContext) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPlayer.reloadStream();
            }
        });
    }

    private void onPlayDefaultVideo(JSONArray args, CallbackContext callbackContext) {
        this.mPlayerStreamCallback = callbackContext;
        mSuccessEnable = true;
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
    }

    private void onPlayVideo(JSONArray args, CallbackContext callbackContext) {
        this.mPlayerStreamCallback = callbackContext;
        mSuccessEnable = true;
        try {
            JSONObject object = args.getJSONObject(0);
            final String url = object.getString("url");
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setViewType(ViewType.STREAM_PLAYER);
                    mPlayer.setStreamUrl(url);
                    mPlayer.playStream();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void onStopVideoPlayer(JSONArray args, CallbackContext callbackContext) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPlayer.stopStream();
                AiaStream.this.setViewType(ViewType.NONE);
            }
        });
    }

    private void onStopAll(JSONArray args, CallbackContext callbackContext) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPlayer.stopStream();
                AiaStream.this.setViewType(ViewType.NONE);
            }
        });
    }

    private void onInitAll(JSONArray args, final CallbackContext callbackContext) {
        Log.e("AHUHU", "onInitAll: ");
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mBroadcast == null) mBroadcast = new RTMPClientHelper(cordova.getActivity());
                if (mPlayer == null) {
                    mPlayer = new RTMPPlayer(cordova.getActivity());
                    mPlayer.initVideoPlayer();
                    mPlayer.setStreamListener(AiaStream.this);
                }
                callbackContext.success();
            }
        });
    }


    private void onInitBroadcast(JSONArray args, CallbackContext callbackContext) {
        if (this.mBroadcast == null) {
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mBroadcast = new RTMPClientHelper(cordova.getActivity());
                }
            });
        }
    }

    private void onInitVideoPlayer(JSONArray args, CallbackContext callbackContext) {
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
            if (this.mPlayerStreamCallback != null) {
                this.mPlayerStreamCallback.success();
                this.mPlayerStreamCallback = null;
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
    public void onPause(boolean multitasking) {
        if (this.mPlayer != null) this.mPlayer.onPause();
        if (this.mBroadcast != null) this.mBroadcast.onPause();
        super.onPause(multitasking);
    }

    @Override
    public void onResume(boolean multitasking) {
        if (this.mPlayer != null) this.mPlayer.onResume();
        if (this.mBroadcast != null) this.mBroadcast.onResume();
        super.onResume(multitasking);

    }

    @Override
    public void onDestroy() {
        if (this.mPlayer != null) this.mPlayer.onDestroy();
        if (this.mBroadcast != null) this.mBroadcast.onDestroy();
        super.onDestroy();
    }

}
