package com.chenyingjie.suplibs.ui;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.chenyingjie.suplibs.R;
import com.dueeeke.videoplayer.player.VideoView;

/**
 * create by chenyingjie on 2020/6/9
 * desc
 */
public class SRewardVideoActivity extends Activity {

    private VideoView videoView;
    private String url = "https://c3.123qkk.com/dsp/video/25d6cda90ab430e7e503df70a8abcc10.mp4";
    private boolean canBack = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActionBar() != null) getActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_sreward_video);

        videoView = findViewById(R.id.videoView);

        videoView.setUrl(url);
        videoView.setScreenScaleType(VideoView.SCREEN_SCALE_DEFAULT);
        videoView.setOnStateChangeListener(new VideoView.OnStateChangeListener() {
            @Override
            public void onPlayerStateChanged(int playerState) {

            }

            @Override
            public void onPlayStateChanged(int playState) {
                switch (playState) {
                    case VideoView.STATE_PLAYING:
                        canBack = false;
                        break;
                    case VideoView.STATE_PLAYBACK_COMPLETED:
                        canBack = true;
                        break;
                }
            }
        });
        videoView.start();
    }

    @Override
    public void onBackPressed() {
        if (canBack) super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null) videoView.release();
    }
}
