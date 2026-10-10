package session7.assignment_problems.p5_connected_home;

public class MobileApp implements RemoteControllable {
    private String appName;

    public MobileApp(String appName) {
        this.appName = appName;
    }

    @Override
    public String connect(String appId) {
        return appName + " connected to " + appId;
    }
}
