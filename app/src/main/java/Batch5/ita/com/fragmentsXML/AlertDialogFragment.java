package Batch5.ita.com.fragmentsXML;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import Batch5.ita.com.R;

/**
 * A simple {@link Fragment} subclass.
 */

public class AlertDialogFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view=  inflater.inflate(R.layout.fragment_alert_dialog, container, false);

       final Button dialog_button = (Button) view.findViewById(R.id.dialog_btn);

        dialog_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), "This is Dialog Fragment ..", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        return view;
    }

}
