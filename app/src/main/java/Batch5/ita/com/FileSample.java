package Batch5.ita.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileSample extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        tv = (TextView) findViewById(R.id.content);
        // ArrayList<Student> studentList = readTextFile();
    }

//    public ArrayList<Student> readTextFile() {
//        ArrayList<Student> studentList = null;
//        String data = readFile();
//        try {
//            JSONArray response = new JSONArray(data);
//            studentList = parseData(response);
//        } catch (JSONException e) {
//
//
//        }
//        tv.setText(data);
//        return studentList;
//    }

    private ArrayList<Student> parseData(JSONArray response) {

        ArrayList<Student> studentList = new ArrayList<Student>();
        for (int i = 0; i < response.length(); i++) {
//            try {
////                Student student = new Student();
////                JSONObject studentInfo = response.getJSONObject(i);
////                String name = studentInfo.getString("studentName");
////                student.setStudentName(name);
////                String studentId = studentInfo.getString("studentId");
////                student.setStudentId(studentId);
////                String studentEmail = studentInfo.getString("studentEmail");
////                student.setStudentEmail(studentEmail);
////                studentList.add(student);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
        }
        return studentList;
    }


    private String readFile() {
        InputStream inputStream = getResources().openRawResource(R.raw.file_sample);
        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader bufferedreader = new BufferedReader(inputreader);
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while ((line = bufferedreader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }
        } catch (IOException e) {
            return null;
        }
        Log.v("data is", "" + stringBuilder.toString());
        return stringBuilder.toString();
    }

    public void readTextFile(View view) {
        ArrayList<Student> studentList = null;
        String data = readFile();
        try {
            JSONArray response = new JSONArray(data);
            studentList = parseData(response);
            ListView lv = (ListView) findViewById(R.id.student_list);
            lv.setAdapter(new CustomAdapter(studentList));
        } catch (JSONException e) {


        }
        //tv.setText(data);
    }

    class CustomAdapter extends BaseAdapter {

        ArrayList<Student> studentList;

        public CustomAdapter(ArrayList<Student> studentList) {
            this.studentList = studentList;
        }

        @Override
        public int getCount() {
            return studentList.size();
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
        public View getView(int i, View view, ViewGroup viewGroup) {

            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.student_item, null);

            TextView name = (TextView) rowView.findViewById(R.id.student_name);
            Student student = studentList.get(i);

            name.setText(student.getStudentName());

            TextView id = (TextView) rowView.findViewById(R.id.student_id);
            id.setText(student.getStudentId());


            TextView email = (TextView) rowView.findViewById(R.id.student_email);
            email.setText(student.getStudentEmail());

            return rowView;
        }
    }

}









