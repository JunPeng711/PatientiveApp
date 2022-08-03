package com.example.mpsd2.education;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.mpsd2.R;
import com.example.mpsd2.education.HealthEducation;

public class Modules1 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules1);

        Button backToHomeBtn;

        backToHomeBtn = findViewById(R.id.back);
        backToHomeBtn.setOnClickListener(this);

        //wash hand video
        VideoView videoView = findViewById(R.id.video_view);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.wash_hand_video;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.back:
                startActivity(new Intent(this, HealthEducation.class));
                break;

        }
    }
}