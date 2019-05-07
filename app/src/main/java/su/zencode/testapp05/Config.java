package su.zencode.testapp05;

public class Config {
    public static class Settings {
        /** Datababe Objects TtL in milliseconds */
        public static final long DATABASE_DATA_TTL = 24*(60*60*1000);
    }

    public static class IntraVisionApi {

        public static final String API_KEY = "i&j*3&^2TZ&d";

        public static class JsonDeserializeMap {
            public static final String JSON_ARRAY_SUB_CATEGORIES = "categories";
            public static final String JSON_ARRAY_OFFERS = "offers";
            public static final String JSON_SUB_CATEGORY_ID = "id";
            public static final String JSON_SUB_CATEGORY_NAME = "name";
            public static final String JSON_SUB_CATEGORY_HAS_SUBCATEGORIES = "subcategories";
            public static final String JSON_OFFER_ID = "id";
            public static final String JSON_OFFER_PICTURES_URLS_ARRAY = "pictures";
            public static final String JSON_OFFER_NAME = "name";
            public static final String JSON_OFFER_ICON_URL = "icon";
        }

        public static class Credentials {
            public static final String USERNAME = "eapteka";
            public static final String PASSWORD = "stage";
        }
    }

    public static class IntraVisionHttp {
        public static final long CONNECT_TIMEOUT = 15;
        public static final long WRITE_TIMEOUT = 15;
        public static final long READ_TIMEOUT = 30;
        public static final boolean RETRY_ON_CONNECTION_FAILURE = true;

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

            public static final class JsonSerialisation {

                public static final String GENDER = "Gender";
                public static final String LASTNAME = "LastName";
                public static final String FIRSTNAME = "FirstName";
                public static final String MIDDLENAME = "MiddleName";
                public static final String EMAIL = "Email";
                public static final String PHONE = "Phone";
                public static final String VIN = "Vin";
                public static final String Year = "YEAR";
                public static final String CLASS_ID = "ClassId";
                public static final String CITY_ID = "City";
                public static final String SHOWROOM_ID = "ShowRoomId";
            }
        }
    }
}
