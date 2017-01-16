package com.aia.stream;

import android.app.Activity;
import android.content.res.Configuration;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import com.aia.showtimes.R;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by STEPPE on 12/29/2016.
 */
public class RTMPPlayer {
    Activity mActivity;
    private String TAG = getClass().getSimpleName();
    private View mPlayerContainer;

    VideoView mVideoView;
    private String mStreamUrl = "";
    StreamListener mStreamListener;
    private boolean mStreamPlaying = false;
    private int mStreamOrientation = Configuration.ORIENTATION_PORTRAIT;

    RTMPPlayer(Activity activity) {
        setActivity(activity);
    }

    public void setActivity(Activity activity) {
        this.mActivity = activity;
    }

    public void initVideoPlayer() {
        mPlayerContainer = this.mActivity.findViewById(R.id.layout_video);
        mVideoView = (VideoView) this.mActivity.findViewById(R.id.mVideoView);
        mVideoView.setBufferSize(512);
        mVideoView.setVideoChroma(MediaPlayer.VIDEOCHROMA_RGB565);
        mVideoView.setMediaController(null);
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setPlaybackSpeed(1.0f);
                if (mStreamUrl.contains(".mp4")) {
                    mediaPlayer.setLooping(true);
                }
            }
        });
        mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mediaPlayer, int status, int extra) {
                if (mStreamListener != null) {
                    mStreamListener.onStreamState(status);
                }
                return true;
            }
        });
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (mStreamUrl.contains(".mp4")) {
                }
            }
        });
    }

    public void setStreamListener(StreamListener listener) {
        this.mStreamListener = listener;
    }

    public interface StreamListener {
        public void onStreamState(int state);
    }


    public void onConfigurationChanged(Configuration newConfig) {
        this.onChangeOrientation(newConfig.orientation);
    }

    public void onChangeOrientation(int orientation) {
        mStreamOrientation = (mVideoView.getVideoWidth() > mVideoView.getVideoHeight()) ? Configuration.ORIENTATION_LANDSCAPE : Configuration.ORIENTATION_PORTRAIT;
        if (mStreamOrientation == Configuration.ORIENTATION_PORTRAIT) {
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
            } else {
                mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_ZOOM, this.mVideoView.getVideoAspectRatio());
            }
        } else {
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_ZOOM, this.mVideoView.getVideoAspectRatio());
            } else {
                mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
            }
        }
    }

    public void setVisible(boolean visible) {
        mPlayerContainer.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void setStreamUrl(String url) {
        this.mStreamUrl = url;
    }

    public void playStream() {
        Log.e(TAG, "playStream: " + this.mStreamUrl);
        if (this.mStreamUrl.contains("rtmp") || this.mStreamUrl.contains(".mp4")) {
            mVideoView.setVideoURI(Uri.parse(this.mStreamUrl));
            mVideoView.start();
            this.mStreamPlaying = true;
        } else {
            this.stopStream();
        }
    }

    public void reloadStream() {
        if (mVideoView != null) {
            this.playStream();
        }

    }

    public void stopStream() {
        mVideoView.stopPlayback();
        this.mStreamPlaying = false;
    }


    public void onPause() {
    }

    public void onResume() {

        if (this.mStreamPlaying && this.mVideoView != null) {
            this.stopStream();
            this.reloadStream();
        }
    }

    public void onDestroy() {


    }

}
