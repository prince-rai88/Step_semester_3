package abstraction_and_interface.class_problems.p5_library_system;

public class Magazine extends LibraryItem implements Renewable {
    private String title;

    public Magazine(String title) {
        super();
        this.title = title;
    }

    @Override
    public int getLoanPeriodDays() {
        return 7;
    }

    @Override
    public String renew() {
        return title + " renewed";
    }
}
