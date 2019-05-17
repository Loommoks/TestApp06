package su.zencode.testapp05.intravisiontestappapiclient;

public class AuthTokenHolder {
    private static AuthTokenHolder sAuthTokenHolder;
    private String mAuthToken;

    public static AuthTokenHolder getInstance() {
        if(sAuthTokenHolder == null) {
            sAuthTokenHolder = new AuthTokenHolder();
        }
        return sAuthTokenHolder;
    }

    private AuthTokenHolder() {
        mAuthToken = null;
    }

    public String getToken() {
        return mAuthToken;
    }

    public void setAuthToken(String newToken) {
        mAuthToken = newToken;
    }
}
