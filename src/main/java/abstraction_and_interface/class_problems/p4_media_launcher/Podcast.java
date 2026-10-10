package abstraction_and_interface.class_problems.p4_media_launcher;

public class Podcast implements Playable {
    private String showName;
    private int episodeNumber;

    public Podcast(String showName, int episodeNumber) {
        this.showName = showName;
        this.episodeNumber = episodeNumber;
    }

    @Override
    public String play() {
        return "Streaming episode " + episodeNumber + " of " + showName;
    }

    @Override
    public String play(int fromSecond) {
        return "Streaming episode " + episodeNumber + " of " + showName + " from 0:" + fromSecond;
    }

    @Override
    public String pause() {
        return "Paused episode " + episodeNumber + " of " + showName;
    }
}
