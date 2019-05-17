package su.zencode.testapp05.intravisiontestappapiclient;

import java.util.ArrayList;

import su.zencode.testapp05.intravisiontestapprepositories.entities.CarClass;
import su.zencode.testapp05.intravisiontestapprepositories.entities.City;
import su.zencode.testapp05.intravisiontestapprepositories.entities.ShowRoom;
import su.zencode.testapp05.intravisiontestapprepositories.entities.WorkSheet;

public interface IIntraVisionApiClient {
    ArrayList<CarClass> getClasses();
    ArrayList<City> getCities();
    ArrayList<ShowRoom> getDealers(int cityId);
    boolean sendWorkSheet(WorkSheet blank);
}