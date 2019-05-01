package su.zencode.testapp05.IntravisionTestAppRepositories.Entities;

public class City {
    private int mId;
    private String mName;

    public City(int id, String name) {
        mId = id;
        mName = name;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }
}
