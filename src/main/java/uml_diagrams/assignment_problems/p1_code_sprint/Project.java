package uml_diagrams.assignment_problems.p1_code_sprint;

public class Project {
    private final String name;
    private Score score;

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
