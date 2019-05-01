package su.zencode.testapp05.IntravisionTestAppApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import su.zencode.testapp05.Config.IntraVisionUrlsMap;
import su.zencode.testapp05.IntravisionTestAppRepositories.Entities.CarClass;
import su.zencode.testapp05.IntravisionTestAppRepositories.Entities.City;
import su.zencode.testapp05.IntravisionTestAppRepositories.Entities.ShowRoom;

public class IntraVisionApiClient implements IIntraVisionApiClient {
    private AuthTokenHolder mAuthTokenHolder;

    public IntraVisionApiClient() {
        mAuthTokenHolder = AuthTokenHolder.getInstance();
    }

    public String getClassesString() {
        if(mAuthTokenHolder.getToken() == null) {
            mAuthTokenHolder.setAuthToken(getNewToken());
        }
        OkHttpClient client = new OkHttpClient();
        String url = IntraVisionUrlsMap.HOST +IntraVisionUrlsMap.CLASSES;
        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + mAuthTokenHolder.getToken())
                .build();

        try {
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<CarClass> getClasses() {
        if(mAuthTokenHolder.getToken() == null) {
            mAuthTokenHolder.setAuthToken(getNewToken());
        }
        OkHttpClient client = new OkHttpClient();
        String url = IntraVisionUrlsMap.HOST +IntraVisionUrlsMap.CLASSES;
        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + mAuthTokenHolder.getToken())
                .build();

        try {
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                return parseCarClassesJson(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<City> getCities() {
        if(mAuthTokenHolder.getToken() == null) {
            mAuthTokenHolder.setAuthToken(getNewToken());
        }
        OkHttpClient client = new OkHttpClient();
        String url = IntraVisionUrlsMap.HOST +IntraVisionUrlsMap.CITIES;
        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + mAuthTokenHolder.getToken())
                .build();

        try {
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                return parseCitiesJson(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<ShowRoom> getDealers(int cityId) {
        return null;
    }

    private String getNewToken() {
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("grant_type", "custom_client_credentials")
                .add("scope", "profile")
                .build();

        String url = IntraVisionUrlsMap.AUTHORIZATION_HOST;
        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic Q3VzdG9tR3JhbnRUeXBlQ2xpZW50SWQ6Q3VzdG9tR3JhbnRUeXBlQ2xpZW50U2VjcmV0")
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return parseTokenJson(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String parseTokenJson(String jsonBody){
        try {
            JSONObject jsonObject = new JSONObject(jsonBody);
            return jsonObject.getString("access_token");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<CarClass> parseCarClassesJson(String jsonBody) {
        try {
            ArrayList<CarClass> classesArray = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(jsonBody);
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonCarClass = jsonArray.getJSONObject(i);
                CarClass carClass = new CarClass(
                        jsonCarClass.getInt("Id"),
                        jsonCarClass.getString("Name"));
                classesArray.add(carClass);
            }
            return classesArray;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<City> parseCitiesJson(String jsonBody) {
        try {
            ArrayList<City> citiesArray = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(jsonBody);
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonCity = jsonArray.getJSONObject(i);
                City city = new City(
                        jsonCity.getInt("Id"),
                        jsonCity.getString("Name"));
                citiesArray.add(city);
            }
            return citiesArray;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
