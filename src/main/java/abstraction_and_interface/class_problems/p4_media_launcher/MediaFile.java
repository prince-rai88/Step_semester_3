package abstraction_and_interface.class_problems.p4_media_launcher;

public abstract class MediaFile {
    private static int counter = 1000;
    private final String fileId;

    public MediaFile() {
        this.fileId = "MF-" + (++counter);
    }

    public abstract String getFormatInfo();

    public String getFileId() {
        return fileId;
    }
}
