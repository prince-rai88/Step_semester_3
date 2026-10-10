package session7.class_problems.p4_media_launcher;

public class MediaLauncher {
    public static void launchAll(Playable[] items) {
        for (Playable p : items) {
            System.out.println(p.play());
        }
    }
}
