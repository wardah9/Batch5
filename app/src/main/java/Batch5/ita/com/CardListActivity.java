package Batch5.ita.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CardListActivity extends AppCompatActivity {

    String[] Url_images = {
            "https://shechive.files.wordpress.com/2012/01/cute-cat-14.jpg?quality=100&strip=info&w=500",
            "https://lph5i1b6c053kq7us26bdk75-wpengine.netdna-ssl.com/wp-content/uploads/2016/04/cat-facts.jpg",
            "http://2.bp.blogspot.com/-rz8ZPaigFTM/T7xleWsQojI/AAAAAAAIq7g/1rYFcwTF4pU/s1600/cute+cat+pictures+%2810%29.jpg",
            "https://i.ytimg.com/vi/R9NCI-naXbU/hqdefault.jpg"
    };

    String[] inames = {
            "white car looking up ",
            "cat playing on game",
            "human hand playing with cat",
            "cat playing with baby child"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);

        ListView card_list = (ListView) findViewById(R.id.card_list);

        card_list.setAdapter(new CustomCardList());
    }


    class CustomCardList extends BaseAdapter {

        @Override
        public int getCount() {
            return Url_images.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View view1 = inflater.inflate(R.layout.card_list, null);


            ImageView imageView = (ImageView) view1.findViewById(R.id.card_image);
            Picasso.with(CardListActivity.this).load(Url_images[position]).into(imageView);


            TextView image_card_text = (TextView) view1.findViewById(R.id.image_card_text);
            image_card_text.setText(inames[position]);

            return view1;
        }
    }
}
