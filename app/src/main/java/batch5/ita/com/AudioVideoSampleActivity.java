package batch5.ita.com;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import java.io.IOException;

public class AudioVideoSampleActivity extends AppCompatActivity {

    private MediaPlayer mp;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_video_sample);

        videoView = (VideoView) findViewById(R.id.VideoView1);

    }

    public void playAudioFile(View view) {

        mp  = MediaPlayer.create(this,R.raw.sound2);
        try {
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.start();
    }

    public void playAudioFromSDcard(View view) {

        mp = new MediaPlayer();
        try {
            mp.setDataSource(Environment.getExternalStorageDirectory().toString()+"/Downloads/sound1");
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void playVideoFile(View view) {
        Uri uri = Uri.parse("android.resources://"+getPackageName()+"/"+ R.raw.short_video2);
        videoView.setVideoURI(uri);
        videoView.start();
    }

    public void playVideoFromSDcard(View view) {

        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().toString()+"Download/animation.mp4");
        videoView.setVideoURI(uri);
        videoView.start();
    }
}
