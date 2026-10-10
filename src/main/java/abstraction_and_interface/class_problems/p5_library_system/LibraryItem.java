package abstraction_and_interface.class_problems.p5_library_system;

public abstract class LibraryItem {
    private static int counter = 1000;
    private final String itemId;

    public LibraryItem() {
        this.itemId = "LI-" + (++counter);
    }

    public abstract int getLoanPeriodDays();

    public String getItemId() {
        return itemId;
    }
}
