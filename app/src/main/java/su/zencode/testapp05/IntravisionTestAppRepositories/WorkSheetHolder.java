package su.zencode.testapp05.IntravisionTestAppRepositories;

import su.zencode.testapp05.IntravisionTestAppRepositories.Entities.WorkSheet;

public class WorkSheetHolder {
    private static WorkSheetHolder sWorkSheetHolder;
    private WorkSheet sWorkSheet;

    public static WorkSheetHolder getInstance(){
        if(sWorkSheetHolder == null){
            sWorkSheetHolder = new WorkSheetHolder();
        }
        return sWorkSheetHolder;
    }

    private WorkSheetHolder(){
        sWorkSheet = new WorkSheet();
    }

    public boolean isCompletelyFilled() {
        if(
                sWorkSheet.getGender() == -1 ||
                sWorkSheet.getLastName() == null ||
                sWorkSheet.getFirstName() == null ||
                sWorkSheet.getMiddleName() == null ||
                sWorkSheet.getEmail() == null ||
                sWorkSheet.getPhone() == null ||
                sWorkSheet.getVin() == null ||
                sWorkSheet.getYear() == null ||
                sWorkSheet.getClassId() == -1 ||
                sWorkSheet.getShowRoomId() == -1
                ) {
            return false;
        }
        return true;
    }

}
