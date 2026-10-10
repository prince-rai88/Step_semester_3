package abstraction_and_interface.assignment_problems.p4_arena_battle;

public class Trap implements Defendable {
    private String trapType;

    public Trap(String trapType) {
        this.trapType = trapType;
    }

    @Override
    public String defend() {
        return trapType + " triggers automatically";
    }
}
