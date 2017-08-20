package Batch5.ita.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pl.droidsonroids.gif.GifImageButton;

public class GifImagesSample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_images_sample);

        GifImageButton gib = new GifImageButton(this);
        setContentView(gib);
        gib.setImageResource(R.drawable.cry);
//        final MediaController mc = new MediaController(this);
//        mc.setMediaPlayer((GifDrawable) gib.getDrawable());
//        mc.setAnchorView(gib);
//        gib.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mc.show();
//            }
//        });
    }
}
