package abstraction_and_interface.assignment_problems.p4_arena_battle;

public class Warrior extends GameCharacter implements Attackable, Defendable {
    private String name;

    public Warrior(String name) {
        super();
        this.name = name;
    }

    @Override
    public String attack() {
        return name + " strikes with a blade";
    }

    @Override
    public String attack(String weaponName) {
        return name + " strikes with an " + weaponName;
    }

    @Override
    public String defend() {
        return name + " raises a shield";
    }

    @Override
    public String getSpecialMove() {
        return name + " unleashes Whirlwind Slash";
    }
}
