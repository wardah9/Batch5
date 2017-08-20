package batch5.ita.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class week4Activity extends AppCompatActivity {
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week4);

        relativeLayout = (RelativeLayout) findViewById(R.id.week4_layout1);

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view3 =inflater.inflate(R.layout.week4_header,null);
        relativeLayout.addView(view3);


        Button button = (Button) findViewById(R.id.week4_buttonView);
        EditText editText = (EditText) findViewById(R.id.week4_editText);
        ImageView imageView = (ImageView) findViewById(R.id.week4_imageView);
        TextView textView = (TextView) findViewById(R.id.week4_textView);


        editText.setText("1234QWER");
        imageView.setImageResource(R.drawable.soundcloud);
        textView.setText("welcome again to SAS Mobile  Development");


//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        View view4 =inflater.inflate(R.layout.week4_footer,null);
        relativeLayout.addView(view4);

    }

    public void onButtonClicked(View view) {
        Toast.makeText(this, "Toast message for button", Toast.LENGTH_SHORT).show();

        Intent in = new Intent(this,secondScreen.class);
        in.putExtra("studebnName","wardah");
        in.putExtra("serialNum", 123);
        in.putExtra("isPresent",true);
        startActivity(in);
    }

    public void onTextClicked(View view) {
    }

    public void onImageClicked(View view) {
    }
}
