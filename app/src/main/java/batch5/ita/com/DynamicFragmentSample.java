package batch5.ita.com;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import batch5.ita.com.fragmentsXML.AlertDialogFragment;
import batch5.ita.com.fragmentsXML.AlertDialogFragment2;
import batch5.ita.com.fragmentsXML.FirstFragment;
import batch5.ita.com.fragmentsXML.SecondFragment;
import batch5.ita.com.fragmentsXML.ThirdFragment;

public class DynamicFragmentSample extends AppCompatActivity {

    private FragmentTransaction ft;
    private FragmentTransaction ft2;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;
    private AlertDialogFragment dialogFragment;
    private AlertDialogFragment2 alertFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_fragment_sample);

        FirstFragment firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();
        thirdFragment = new ThirdFragment();


        ft = getFragmentManager().beginTransaction();

        ft.add(R.id.container1,firstFragment);
        ft.add(R.id.container2,secondFragment);

        ft.commit();


       // FragmentManager fm = getFragmentManager();                    android.app
       // FragmentManager fm2 = getSupportFragmentManager();           android.support.v7.app

    }

    public void OnFragmentReplace(View view) {

        try {

            ft2 = getFragmentManager().beginTransaction();
            ft2.replace(R.id.container2,thirdFragment);
            ft2.commit();
        }catch (Exception e) {
            Log.v("fragment",e.toString());
        }


    }

    public void OnShowDialogFragment(View view) {
        dialogFragment = new AlertDialogFragment();
        dialogFragment.show(getSupportFragmentManager(),"Dialog");
    }

    public void OnAlertDialogFragment(View view) {
        alertFragment = new AlertDialogFragment2();
        alertFragment.show(getSupportFragmentManager(),"AlertDialog");
    }

    public void OnOKClicked(View view) {
        Toast.makeText(DynamicFragmentSample.this, "This is Dialog Fragment second button.", Toast.LENGTH_SHORT).show();
    }
}
