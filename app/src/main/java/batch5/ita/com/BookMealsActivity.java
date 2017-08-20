package batch5.ita.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class BookMealsActivity extends AppCompatActivity {

    String meal_name[]={
            "Pasta",
            "Burger",
            "Cupcake",
            "IceCream",
            "Pizza",
            "Rasagulla"
    };

    String meal_price[]={
            "price: $5",
            "price: $1.98",
            "price: $1",
            "price: $6",
            "price: $120",
            "price: $9"
    };

    int meal_images[]={
            R.drawable.pasta3,
            R.drawable.burger,
            R.drawable.cupcake3,
            R.drawable.ice_cream,
            R.drawable.pizza,
            R.drawable.rasagulla

    };

    boolean imageclicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_meals);

        GridView gridView = (GridView) findViewById(R.id.grid_meal);
        gridView.setAdapter(new CustomGridMeal());

    }

    class CustomGridMeal extends BaseAdapter{

        @Override
        public int getCount() {
            return meal_name.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View vie = inflater.inflate(R.layout.transparent_layout, null);


            ImageView imageMeal = (ImageView) vie.findViewById(R.id.meal_image);
            TextView textPrice = (TextView) vie.findViewById(R.id.price);
            TextView textname = (TextView) vie.findViewById(R.id.titel);


            imageMeal.setImageResource(meal_images[position]);
            textPrice.setText(meal_price[position]);
            textname.setText(meal_name[position]);

            final ImageView cart = (ImageView) vie.findViewById(R.id.image_color);
            cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!imageclicked){

                        imageclicked = true;
                        cart.setImageResource(R.drawable.online_shopping_cart);
                    }else
                    {
                        imageclicked = false;
                        cart.setImageResource(R.drawable.online_shopping_cart2);
                    }
                }
            });

            return vie;
        }
    }
}
