package su.zencode.testapp05.IntravisionTestAppRepositories.Entities;

public class WorkSheet {
    private int mGender;
    private String mLastName;
    private String mFirstName;
    private String mMiddleName;
    private String mEmail;
    private String mPhone;
    private String mVin;
    private String mYear;
    private int mClassId;
    private int mShowRoomId;

    public WorkSheet() {
        mGender = -1;
        mClassId = -1;
        mShowRoomId = -1;
    }

    public int getGender() {
        return mGender;
    }

    public void setGender(int gender) {
        mGender = gender;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getMiddleName() {
        return mMiddleName;
    }

    public void setMiddleName(String middleName) {
        mMiddleName = middleName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getVin() {
        return mVin;
    }

    public void setVin(String vin) {
        mVin = vin;
    }

    public String getYear() {
        return mYear;
    }

    public void setYear(String year) {
        mYear = year;
    }

    public int getClassId() {
        return mClassId;
    }

    public void setClassId(int classId) {
        mClassId = classId;
    }

    public int getShowRoomId() {
        return mShowRoomId;
    }

    public void setShowRoomId(int showRoomId) {
        mShowRoomId = showRoomId;
    }

}
