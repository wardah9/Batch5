package project.batch5.ita.com.model;

/**
 * Created by batch3 on 8/17/17.
 */

public class PUsers {

    public String puserName;
    public String puserPhone;
    public String puserEmail;
    public String puserpassword;
    public String puserGender;


    public String pFirebaseUserId;
PUsers(){

}

    public PUsers(String puserName, String puserPhone, String puserEmail, String puserpassword, String puserGender, String pFirebaseUserId) {
        this.puserName = puserName;
        this.puserPhone = puserPhone;
        this.puserEmail = puserEmail;
        this.puserpassword = puserpassword;
        this.puserGender = puserGender;
        this.pFirebaseUserId = pFirebaseUserId;
    }

    public String getPuserName() {
        return puserName;
    }

    public String getPuserPhone() {
        return puserPhone;
    }

    public String getPuserEmail() {
        return puserEmail;
    }

    public String getPuserpassword() {
        return puserpassword;
    }

    public String getPuserGender() {
        return puserGender;
    }

    public String getpFirebaseUserId() {
        return pFirebaseUserId;
    }
}
