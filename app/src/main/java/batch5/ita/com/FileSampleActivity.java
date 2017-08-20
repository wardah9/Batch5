package batch5.ita.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileSampleActivity extends AppCompatActivity {

    TextView txt ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_sample);
        txt = (TextView) findViewById(R.id.txt);
    }


    public void readTextFileClicked(View view) {
        String data = readFile();
        try {
            JSONObject response = new JSONObject(data);
            ParseData(response);

        } catch (JSONException e) {
            e.printStackTrace();
        }



        String jsonFile = readJsonFile();
        try {
            JSONArray response = new JSONArray(jsonFile);
            ParseJsonData(response);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void ParseJsonData(JSONArray response) throws JSONException {
        for (int i=0 ; i<response.length(); i++){
           JSONObject object = response.getJSONObject(i);
            object.getInt("id");

            JSONObject jsonObject = object.getJSONObject("entities");
           JSONArray tagsArray = jsonObject.getJSONArray("hashtags");

            for (int j=0; j<tagsArray.length(); j++){
               JSONObject tags =  tagsArray.getJSONObject(j);
               String text =  tags.getString("text");

                JSONArray indices = tags.getJSONArray("indices");
                for (int x=0; x<indices.length(); x++){

                   int value = (int) indices.get(i);
                }
            }
        }
    }

    private void ParseData(JSONObject response) {

        try {
            JSONArray jsonArray = response.getJSONArray("markers");
            for (int i=0; i<jsonArray.length();i++){

                JSONObject object = jsonArray.getJSONObject(i);
                object.getString("name");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String readFile(){

        InputStream stream = getResources().openRawResource(R.raw.sample);
        InputStreamReader streamReader =  new InputStreamReader(stream);
        BufferedReader bufferedReader = new BufferedReader(streamReader);

        String line ;

        StringBuilder stringBuilder = new StringBuilder();
        try {
            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        Log.v("data is ", ""+stringBuilder.toString());
        return stringBuilder.toString();
    }


    private String readJsonFile(){

        InputStream stream = getResources().openRawResource(R.raw.sample);
        InputStreamReader streamReader =  new InputStreamReader(stream);
        BufferedReader bufferedReader = new BufferedReader(streamReader);

        String line ;

        StringBuilder stringBuilder = new StringBuilder();
        try {
            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        Log.v("data is ", ""+stringBuilder.toString());
        return stringBuilder.toString();
    }

}
