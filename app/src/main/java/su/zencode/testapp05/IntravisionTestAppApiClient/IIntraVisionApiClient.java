package su.zencode.testapp05.IntravisionTestAppApiClient;

import java.util.ArrayList;

import su.zencode.testapp05.IntravisionTestAppRepositories.Entities.CarClass;
import su.zencode.testapp05.IntravisionTestAppRepositories.Entities.City;
import su.zencode.testapp05.IntravisionTestAppRepositories.Entities.ShowRoom;

public interface IIntraVisionApiClient {
    ArrayList<CarClass> getClasses();
    ArrayList<City> getCities();
    ArrayList<ShowRoom> getDealers(int cityId);
    //void sendUserBlank(UserBlank blank);
}