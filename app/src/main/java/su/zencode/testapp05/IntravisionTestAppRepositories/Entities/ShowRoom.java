package su.zencode.testapp05.IntravisionTestAppRepositories.Entities;

public class ShowRoom {
    private int mId;
    private String mName;
    private int mCityId;

    public ShowRoom(int id, String name, int cityId) {
        mId = id;
        mName = name;
        mCityId = cityId;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public int getCityId() {
        return mCityId;
    }
}
