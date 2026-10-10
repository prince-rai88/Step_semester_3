package session7.assignment_problems.p4_arena_battle;

public class Arena {
    public static void resolveDefense(Defendable[] combatants) {
        for (Defendable d : combatants) {
            System.out.println(d.defend());
        }
    }
}
