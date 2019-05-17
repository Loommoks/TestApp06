package su.zencode.testapp05.intravisiontestappapiclient;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import su.zencode.testapp05.Config;
import su.zencode.testapp05.Config.IntraVisionApi.BODY_PARAMETERS;
import su.zencode.testapp05.Config.IntraVisionApi.HEADERS_PARAMETERS;
import su.zencode.testapp05.Config.IntraVisionApi.JsonDeserializeMap;
import su.zencode.testapp05.Config.IntraVisionApi.JsonSerializeMap;
import su.zencode.testapp05.Config.IntraVisionApi.URI_PARAMETERS;
import su.zencode.testapp05.Config.IntraVisionUrlsMap;
import su.zencode.testapp05.intravisiontestapprepositories.entities.CarClass;
import su.zencode.testapp05.intravisiontestapprepositories.entities.City;
import su.zencode.testapp05.intravisiontestapprepositories.entities.ShowRoom;
import su.zencode.testapp05.intravisiontestapprepositories.entities.WorkSheet;

public class IntraVisionApiClient implements IIntraVisionApiClient {
    private AuthTokenHolder mAuthTokenHolder;
    private static final String TAG = ".IntraVisionApiClient";
    public static final MediaType JSON
            = MediaType.parse("application/json");

    public IntraVisionApiClient() {
        mAuthTokenHolder = AuthTokenHolder.getInstance();
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
                .header(HEADERS_PARAMETERS.ACCEPT_KEY, HEADERS_PARAMETERS.KEY_VALUE_JSON)
                .header(HEADERS_PARAMETERS.CONTENT_TYPE_KEY, HEADERS_PARAMETERS.KEY_VALUE_JSON)
                .header(HEADERS_PARAMETERS.AUTHORIZATION_KEY,
                        HEADERS_PARAMETERS.KEY_VALUE_BEARER
                                + " " + mAuthTokenHolder.getToken())
                .build();

        try {
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                return parseCarClassesJson(response.body().string());
            }
        } catch (IOException e) {
            Log.e(TAG, "Classes request failed", e);
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
                .header(HEADERS_PARAMETERS.ACCEPT_KEY, HEADERS_PARAMETERS.KEY_VALUE_JSON)
                .header(HEADERS_PARAMETERS.CONTENT_TYPE_KEY, HEADERS_PARAMETERS.KEY_VALUE_JSON)
                .header(HEADERS_PARAMETERS.AUTHORIZATION_KEY,
                        HEADERS_PARAMETERS.KEY_VALUE_BEARER
                                + " " + mAuthTokenHolder.getToken())
                .build();

        try {
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                return parseCitiesJson(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "Cities request failed: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public ArrayList<ShowRoom> getDealers(int cityId) {
        if(mAuthTokenHolder.getToken() == null) {
            mAuthTokenHolder.setAuthToken(getNewToken());
        }
        OkHttpClient client = new OkHttpClient();

        String urlS = IntraVisionUrlsMap.HOST +IntraVisionUrlsMap.SHOW_ROOMS;

        HttpUrl.Builder urlBuilder = HttpUrl.parse(urlS).newBuilder();
        urlBuilder.addQueryParameter(URI_PARAMETERS.CITY_ID_KEY, Integer.toString(cityId));
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .header(HEADERS_PARAMETERS.ACCEPT_KEY, HEADERS_PARAMETERS.KEY_VALUE_JSON)
                .header(HEADERS_PARAMETERS.CONTENT_TYPE_KEY, HEADERS_PARAMETERS.KEY_VALUE_JSON)
                .header(HEADERS_PARAMETERS.AUTHORIZATION_KEY,
                        HEADERS_PARAMETERS.KEY_VALUE_BEARER
                                + " " + mAuthTokenHolder.getToken())
                .build();

        try {
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                return parseShowRoomsJson(response.body().string());
            }
        } catch (IOException e) {
            Log.e(TAG, "dealers request failed", e);
        }
        return null;
    }

    @Override
    public boolean sendWorkSheet(WorkSheet blank) {
        JSONObject threePlusOrder = new JSONObject();
        try {
            threePlusOrder.put(JsonSerializeMap.GENDER, blank.getGender());
            threePlusOrder.put(JsonSerializeMap.LASTNAME, blank.getLastName());
            threePlusOrder.put(JsonSerializeMap.FIRSTNAME, blank.getFirstName());
            threePlusOrder.put(JsonSerializeMap.MIDDLENAME, blank.getMiddleName());
            threePlusOrder.put(JsonSerializeMap.EMAIL, blank.getEmail());
            threePlusOrder.put(JsonSerializeMap.PHONE, blank.getPhone());
            threePlusOrder.put(JsonSerializeMap.VIN, blank.getVin());
            threePlusOrder.put(JsonSerializeMap.YEAR, blank.getYear());
            threePlusOrder.put(JsonSerializeMap.CLASS_ID, blank.getClassId());
            threePlusOrder.put(JsonSerializeMap.CITY_ID, blank.getCityId());
            threePlusOrder.put(JsonSerializeMap.SHOWROOM_ID, blank.getShowRoomId());
        } catch (JSONException e) {
            Log.e(TAG, "failed to fill Json", e);
        }

        if(mAuthTokenHolder.getToken() == null) {
            mAuthTokenHolder.setAuthToken(getNewToken());
        }

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, threePlusOrder.toString());

        String url = IntraVisionUrlsMap.HOST + IntraVisionUrlsMap.WORKSHEETS;
        Request request = new Request.Builder()
                .url(url)
                .header(HEADERS_PARAMETERS.ACCEPT_KEY, HEADERS_PARAMETERS.KEY_VALUE_JSON)
                .header(HEADERS_PARAMETERS.CONTENT_TYPE_KEY, HEADERS_PARAMETERS.KEY_VALUE_JSON)
                .header(HEADERS_PARAMETERS.AUTHORIZATION_KEY,
                        HEADERS_PARAMETERS.KEY_VALUE_BEARER
                                + " " + mAuthTokenHolder.getToken())
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            Log.e(TAG, "sendWorkSheet request failed", e);
        }
        return false;
    }

    private String getNewToken() {
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add(BODY_PARAMETERS.GRANT_TYPE_KEY, BODY_PARAMETERS.KEY_VALUE_CUSTOM_CREDENTIALS)
                .add(BODY_PARAMETERS.SCOPE_KEY, BODY_PARAMETERS.KEY_VALUE_PROFILE)
                .build();

        String url = IntraVisionUrlsMap.AUTHORIZATION_HOST;
        Request request = new Request.Builder()
                .url(url)
                .header(HEADERS_PARAMETERS.CONTENT_TYPE_KEY, HEADERS_PARAMETERS.KEY_VALUE_URLENCODED)
                .header(HEADERS_PARAMETERS.AUTHORIZATION_KEY,
                        HEADERS_PARAMETERS.KEY_VALUE_BASIC + Config.IntraVisionApi.AUTH_KEY)
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return parseTokenJson(response.body().string());
            }
        } catch (IOException e) {
            Log.e(TAG, "token request failed", e);
        }
        return null;
    }

    private String parseTokenJson(String jsonBody){
        try {
            JSONObject jsonObject = new JSONObject(jsonBody);
            return jsonObject.getString(JsonDeserializeMap.ACCESS_TOKEN);
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse Json-Token", e);
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
                        jsonCarClass.getInt(JsonDeserializeMap.ID),
                        jsonCarClass.getString(JsonDeserializeMap.NAME));
                classesArray.add(carClass);
            }
            return classesArray;
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse Json - car classes", e);
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
                        jsonCity.getInt(JsonDeserializeMap.ID),
                        jsonCity.getString(JsonDeserializeMap.NAME));
                citiesArray.add(city);
            }
            return citiesArray;
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse Json - cities", e);
        }
        return null;
    }

    private ArrayList<ShowRoom> parseShowRoomsJson(String jsonBody) {
        try {
            ArrayList<ShowRoom> showRoomsArray = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(jsonBody);
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonShowRoom = jsonArray.getJSONObject(i);
                ShowRoom showRoom = new ShowRoom(
                        jsonShowRoom.getInt(JsonDeserializeMap.ID),
                        jsonShowRoom.getString(JsonDeserializeMap.NAME),
                        jsonShowRoom.getInt(JsonDeserializeMap.CITY_ID));
                showRoomsArray.add(showRoom);
            }
            return showRoomsArray;
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse Json - Dealers", e);
        }
        return null;
    }
}
