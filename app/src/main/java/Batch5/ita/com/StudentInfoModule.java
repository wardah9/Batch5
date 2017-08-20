package Batch5.ita.com;

/**
 * Created by batch3 on 8/15/17.
 */

public class StudentInfoModule {


    private String StudentName;
    private String StudentId;
    private String StudentPhotoUrl;
    private String StudentGPA;


    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public String getStudentPhotoUrl() {
        return StudentPhotoUrl;
    }

    public void setStudentPhotoUrl(String studentPhotoUrl) {
        StudentPhotoUrl = studentPhotoUrl;
    }

    public String getStudentGPA() {
        return StudentGPA;
    }

    public void setStudentGPA(String studentGPA) {
        StudentGPA = studentGPA;
    }

    public String calculation (int M1, int M2, int M3){
        int result = (M1 + M2 + M3 )/3;
        return String.valueOf(result);
    }
}
