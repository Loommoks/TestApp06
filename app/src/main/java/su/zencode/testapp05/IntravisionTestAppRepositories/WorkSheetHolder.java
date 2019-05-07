package su.zencode.testapp05.IntravisionTestAppRepositories;

import su.zencode.testapp05.IntravisionTestAppRepositories.Entities.WorkSheet;

public class WorkSheetHolder {
    private static WorkSheetHolder sWorkSheetHolder;
    private WorkSheet mWorkSheet;

    public static WorkSheetHolder getInstance(){
        if(sWorkSheetHolder == null){
            sWorkSheetHolder = new WorkSheetHolder();
        }
        return sWorkSheetHolder;
    }

    private WorkSheetHolder(){
        mWorkSheet = new WorkSheet();
    }

    public WorkSheet getWorkSheet() {
        return mWorkSheet;
    }

    public void updateWorkSheet(WorkSheet workSheet) {
        mWorkSheet.setGender(workSheet.getGender());
        mWorkSheet.setLastName(workSheet.getLastName());
        mWorkSheet.setFirstName(workSheet.getFirstName());
        mWorkSheet.setMiddleName(workSheet.getMiddleName());
        mWorkSheet.setEmail(workSheet.getEmail());
        mWorkSheet.setPhone(workSheet.getPhone());
        mWorkSheet.setVin(workSheet.getVin());
        mWorkSheet.setYear(workSheet.getYear());
        mWorkSheet.setClassId(workSheet.getClassId());
        mWorkSheet.setCityId(workSheet.getCityId());
        mWorkSheet.setShowRoomId(workSheet.getShowRoomId());
    }

    public boolean isCompletelyFilled() {
        if(     mWorkSheet.getGender() == -1 ||
                mWorkSheet.getLastName() == null ||
                mWorkSheet.getFirstName() == null ||
                mWorkSheet.getMiddleName() == null ||
                mWorkSheet.getEmail() == null ||
                mWorkSheet.getPhone() == null ||
                mWorkSheet.getVin() == null ||
                mWorkSheet.getYear() == null ||
                mWorkSheet.getClassId() == -1 ||
                mWorkSheet.getCityId() == -1 ||
                mWorkSheet.getShowRoomId() == -1
                ) {
            return false;
        }
        return true;
    }

}
