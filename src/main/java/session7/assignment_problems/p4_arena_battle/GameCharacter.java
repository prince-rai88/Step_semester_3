package session7.assignment_problems.p4_arena_battle;

public abstract class GameCharacter {
    private static int counter = 1000;
    private final String characterId;

    public GameCharacter() {
        this.characterId = "GC-" + (++counter);
    }

    public abstract String getSpecialMove();

    public String getCharacterId() {
        return characterId;
    }
}
