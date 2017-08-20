package batch5.ita.com;

/**
 * Created by batch3 on 8/14/17.
 */

class Student {

    private String studentName;
    private String studentId;
    private String studentEmail;
    private String studentFirebaseId;


    public Student() {
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getStudentFirebaseId() {
        return studentFirebaseId;
    }

    public Student(String studentName, String studentId, String studentEmail, String studentFirebaseId) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.studentEmail = studentEmail;
        this.studentFirebaseId = studentFirebaseId;
    }
}
