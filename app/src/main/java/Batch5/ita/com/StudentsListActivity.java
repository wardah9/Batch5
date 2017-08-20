package Batch5.ita.com;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StudentsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);

        ArrayList<StudentInfoModule> studentList = null;
        String data = readFile();
        try {
            JSONArray response = new JSONArray(data);
            studentList = parseData(response);
            ListView student_listview = (ListView) findViewById(R.id.students_listview);
            student_listview.setAdapter(new CustomAdepter(studentList));
        } catch (JSONException e) {

        }
    }


    private ArrayList<StudentInfoModule> parseData(JSONArray response) {

        ArrayList<StudentInfoModule> studentList = new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            try {
                StudentInfoModule student = new StudentInfoModule();
                JSONObject studentInfo = response.getJSONObject(i);
                String name = studentInfo.getString("StudentName");
                student.setStudentName(name);
                String studentId = studentInfo.getString("StudentId");
                student.setStudentId(studentId);

                JSONObject marks = studentInfo.getJSONObject("StudentMarks");
                int computer = marks.getInt("Computers");
                int MAth = marks.getInt("MAth");
                int Scince = marks.getInt("Scince");

                String grade =  student.calculation(computer,MAth,Scince);
                student.setStudentGPA(grade);

                String url = studentInfo.getString("StudentPhotoUrl");
                student.setStudentPhotoUrl(url);

                studentList.add(student);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return studentList;
    }


    private String readFile() {
        InputStream inputStream = getResources().openRawResource(R.raw.hello);
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
            return e.toString();
        }
        Log.v("data is", "" + stringBuilder.toString());
        return stringBuilder.toString();
    }

    private class CustomAdepter extends BaseAdapter {

        ArrayList<StudentInfoModule> studentList;

        public CustomAdepter(ArrayList<StudentInfoModule> studentList) {
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
            View rowView = inflater.inflate(R.layout.student_interface, null);

            TextView name = (TextView) rowView.findViewById(R.id.student_name1);
            StudentInfoModule student = studentList.get(i);

            name.setText(student.getStudentName());

            TextView id = (TextView) rowView.findViewById(R.id.student_id1);
            id.setText(student.getStudentId());


            TextView GPA = (TextView) rowView.findViewById(R.id.student_GPA1);
            GPA.setText("Grade : "+student.getStudentGPA());

            ImageView profile  = (ImageView) rowView.findViewById(R.id.student_profile_image);

            final ProgressDialog dialog = new ProgressDialog(StudentsListActivity.this);
            dialog.setMessage("Loading");
            dialog.show();
            Picasso.with(StudentsListActivity.this).load(student.getStudentPhotoUrl()).into(profile);

            return rowView;
        }
    }
}
