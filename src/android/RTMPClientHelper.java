package com.aia.stream;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.aia.showtimes.R;
import com.github.faucamp.simplertmp.RtmpHandler;
import com.seu.magicfilter.filter.helper.MagicFilterType;
import com.seu.magicfilter.widget.RTMPCameraStreaming;

import net.ossrs.yasea.SrsEncodeHandler;
import net.ossrs.yasea.SrsRecordHandler;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * Created by STEPPE on 1/12/2017.
 */
public class RTMPClientHelper implements RtmpHandler.RtmpListener, SrsRecordHandler.SrsRecordListener, SrsEncodeHandler.SrsEncodeListener {
    private int RESULT_CONNECTING = 0;
    private int RESULT_CONNECTED = 1;
    private int RESULT_VIDEO_STREAMING = 2;
    private int RESULT_RTMP_STOPPED = 3;
    private int RESULT_RTMP_DISCONNECTED = 4;
    private int RESULT_RTMP_NETWORK_WEAK = 5;

    private Activity mActivity;
    private RTMPCameraStreaming mRTMPClient;
    private CallbackContext mCallback;
    ArrayList<MagicFilterType> filters = new ArrayList<>();

    RTMPClientHelper(Activity activity) {
        this.mActivity = activity;
        this.init();
        this.setVisible(true);
    }

    public void setCallback(CallbackContext mCallback) {
        this.mCallback = mCallback;
    }

    private void init() {
        mRTMPClient = (RTMPCameraStreaming) this.mActivity.findViewById(R.id.magic_cameraview);
        mRTMPClient.setEncodeHandler(new SrsEncodeHandler(this));
        mRTMPClient.setRtmpHandler(new RtmpHandler(this));
        mRTMPClient.setRecordHandler(new SrsRecordHandler(this));

        filters.add(MagicFilterType.SKINWHITEN);
        filters.add(MagicFilterType.BEAUTY);
        filters.add(MagicFilterType.WHITECAT);
        filters.add(MagicFilterType.FAIRYTALE);
        filters.add(MagicFilterType.BLACKCAT);
        filters.add(MagicFilterType.ROMANCE);
        filters.add(MagicFilterType.SAKURA);
        filters.add(MagicFilterType.AMARO);
        filters.add(MagicFilterType.WALDEN);
        filters.add(MagicFilterType.ANTIQUE);
        filters.add(MagicFilterType.CALM);
        filters.add(MagicFilterType.BRANNAN);
        filters.add(MagicFilterType.BROOKLYN);
        filters.add(MagicFilterType.EARLYBIRD);
        filters.add(MagicFilterType.FREUD);
        filters.add(MagicFilterType.HEFE);
        filters.add(MagicFilterType.HUDSON);
        filters.add(MagicFilterType.INKWELL);
        filters.add(MagicFilterType.KEVIN);
        filters.add(MagicFilterType.LOMO);
        filters.add(MagicFilterType.N1977);
        filters.add(MagicFilterType.NASHVILLE);
        filters.add(MagicFilterType.PIXAR);
        filters.add(MagicFilterType.RISE);
        filters.add(MagicFilterType.SIERRA);
        filters.add(MagicFilterType.SUTRO);
        filters.add(MagicFilterType.TOASTER2);
        filters.add(MagicFilterType.VALENCIA);
        filters.add(MagicFilterType.XPROII);
        filters.add(MagicFilterType.EVERGREEN);
        filters.add(MagicFilterType.HEALTHY);
        filters.add(MagicFilterType.COOL);
        filters.add(MagicFilterType.EMERALD);
        filters.add(MagicFilterType.LATTE);
        filters.add(MagicFilterType.WARM);
        filters.add(MagicFilterType.TENDER);
        filters.add(MagicFilterType.SWEETS);
        filters.add(MagicFilterType.NOSTALGIA);
        filters.add(MagicFilterType.SUNRISE);
        filters.add(MagicFilterType.SUNSET);
        filters.add(MagicFilterType.CRAYON);
        filters.add(MagicFilterType.SKETCH);
    }

    public void setFilter(int filter) {
        if (filter >= this.filters.size()) filter = 0;
        this.mRTMPClient.setFilter(this.filters.get(filter));
    }

    public void startCameraPreview() {
        this.setVisible(true);
        this.mRTMPClient.startCameraPreview();
    }

    public void stopCameraPreview() {
        this.mRTMPClient.stopCameraPreview();
        this.setVisible(false);
    }

    public void setVisible(boolean visible) {
        this.mRTMPClient.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }

    public void switchCamera() {
        this.mRTMPClient.switchCamera();
    }

    public void startBroadcast(String streamURL) {
        this.mRTMPClient.startBroadcast(streamURL);
    }

    public void stopBroadcast() {
        this.mRTMPClient.stopBroadcast();
    }

    public void stopBroadcastAndPreview() {
        this.mRTMPClient.stopBroadcastAndPreview();
        this.setVisible(false);
    }

    public void setAudioEnable(boolean enable) {
        this.mRTMPClient.setAudioEnable(enable);
    }

    public boolean isAudioEnable() {
        return this.mRTMPClient.isAudioEnable();
    }

    public RTMPCameraStreaming.BroadcastState getBroadcastState() {
        return this.mRTMPClient.getBroadcastState();
    }

    public void onPause() {
        this.mRTMPClient.onViewPause();
    }

    public void onResume() {
        this.mRTMPClient.onViewResume();

    }

    public void onDestroy() {
        this.mRTMPClient.onViewDestroy();
    }

    public void sendCallback(int message) {
        if (mCallback != null) {
            try {
                JSONObject data = new JSONObject();
                data.put("status", message);
                PluginResult result = new PluginResult(PluginResult.Status.OK, data);
                result.setKeepCallback(true);
                mCallback.sendPluginResult(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    // ==================================== overide methods ===========================
    boolean mSentStreamingEvent = false;

    @Override
    public void onRtmpConnecting(String msg) {
        sendCallback(RESULT_CONNECTING);
        mSentStreamingEvent = false;
        Log.d("Ahuhu", "onRtmpConnecting: ");
    }

    @Override
    public void onRtmpConnected(String msg) {
        sendCallback(RESULT_CONNECTED);
        mSentStreamingEvent = false;
        Log.d("Ahuhu", "onRtmpConnected: ");

    }

    @Override
    public void onRtmpVideoStreaming() {
        if (!mSentStreamingEvent) {
            sendCallback(RESULT_VIDEO_STREAMING);
            mSentStreamingEvent = true;
            Log.d("Ahuhu", "onRtmpVideoStreaming: ");
        }
    }

    @Override
    public void onRtmpAudioStreaming() {

    }

    @Override
    public void onRtmpStopped() {
        sendCallback(RESULT_RTMP_STOPPED);
        Log.d("Ahuhu", "onRtmpStopped: ");
    }

    @Override
    public void onRtmpDisconnected() {
        sendCallback(RESULT_RTMP_DISCONNECTED);
        Log.d("Ahuhu", "onRtmpDisconnected: ");
        mCallback = null;
    }

    @Override
    public void onRtmpVideoFpsChanged(double fps) {

    }

    @Override
    public void onRtmpVideoBitrateChanged(double bitrate) {

    }

    @Override
    public void onRtmpAudioBitrateChanged(double bitrate) {

    }

    @Override
    public void onRtmpSocketException(SocketException e) {

    }

    @Override
    public void onRtmpIOException(IOException e) {

    }

    @Override
    public void onRtmpIllegalArgumentException(IllegalArgumentException e) {

    }

    @Override
    public void onRtmpIllegalStateException(IllegalStateException e) {

    }

    @Override
    public void onNetworkWeak() {
        sendCallback(RESULT_RTMP_NETWORK_WEAK);
    }

    @Override
    public void onNetworkResume() {

    }

    @Override
    public void onEncodeIllegalArgumentException(IllegalArgumentException e) {

    }

    @Override
    public void onRecordPause() {

    }

    @Override
    public void onRecordResume() {

    }

    @Override
    public void onRecordStarted(String msg) {

    }

    @Override
    public void onRecordFinished(String msg) {

    }

    @Override
    public void onRecordIllegalArgumentException(IllegalArgumentException e) {

    }

    @Override
    public void onRecordIOException(IOException e) {

    }
}
