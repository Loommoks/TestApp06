package su.zencode.testapp05;

public class Config {

    public static class IntraVisionApi {

        public static final String AUTH_KEY = "Q3VzdG9tR3JhbnRUeXBlQ2xpZW50SWQ6Q3VzdG9tR3JhbnRUeXBlQ2xpZW50U2VjcmV0";

        public static class HEADERS_PARAMETERS {
            public static final String ACCEPT_KEY = "Accept";
            public static final String KEY_VALUE_JSON = "application/json";
            public static final String CONTENT_TYPE_KEY = "Content-Type";
            public static final String AUTHORIZATION_KEY = "Authorization";
            public static final String KEY_VALUE_URLENCODED = "application/x-www-form-urlencoded";

            public static final String KEY_VALUE_BEARER = "Bearer ";
            public static final String KEY_VALUE_BASIC = "Basic ";

        }

        public static class URI_PARAMETERS {
            public static final String CITY_ID_KEY = "CityId";
        }

        public static class BODY_PARAMETERS {
            public static final String GRANT_TYPE_KEY = "grant_type";
            public static final String SCOPE_KEY = "scope";
            public static final String KEY_VALUE_CUSTOM_CREDENTIALS = "custom_client_credentials";
            public static final String KEY_VALUE_PROFILE = "profile";
        }

        public static class JsonSerializeMap {
            public static final String GENDER = "Gender";
            public static final String LASTNAME = "LastName";
            public static final String FIRSTNAME = "FirstName";
            public static final String MIDDLENAME = "MiddleName";
            public static final String EMAIL = "Email";
            public static final String PHONE = "Phone";
            public static final String VIN = "Vin";
            public static final String YEAR = "Year";
            public static final String CLASS_ID = "ClassId";
            public static final String CITY_ID = "City";
            public static final String SHOWROOM_ID = "ShowRoomId";

        }

        public static class JsonDeserializeMap {
            public static final String ACCESS_TOKEN = "access_token";
            public static final String ID = "Id";
            public static final String NAME = "Name";
            public static final String CITY_ID = "CityId";
        }

    }

    public static class IntraVisionUrlsMap {
        public static final String HOST = "http://3plus-authless.test.intravision.ru/api";
        public static final String AUTHORIZATION_HOST = "http://identity-server.test.intravision.ru/core/connect/token";
        public static final String CLASSES = "/Classes";
        public static final String CITIES = "/Cities";
        public static final String SHOW_ROOMS = "/ShowRooms";
        public static final String WORKSHEETS = "/WorkSheets";
    }


    public static class DbSchema {
        public static final boolean SAVE_ONLY_ONE_COPY = true;
        public static final String LONER_ID = "1";

        public static final class UserDataTable {
            public static final String NAME = "userblank";

            public static final class Cols {
                public static final String ID = "id";
                public static final String GENDER = "Gender";
                public static final String LASTNAME = "LastName";
                public static final String FIRSTNAME = "FirstName";
                public static final String MIDDLENAME = "MiddleName";
                public static final String EMAIL = "Email";
                public static final String PHONE = "Phone";
                /** Lazy type */
                public static final String VIN = "Vin";
                public static final String YEAR = "YEAR";
            }
        }
    }
}
