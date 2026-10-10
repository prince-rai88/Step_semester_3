package uml_diagrams.assignment_problems.p1_code_sprint;

public class Score {
    private final double idea;
    private final double execution;
    private final double presentation;
    private final double finalScore;

    public Score(double idea, double execution, double presentation, double finalScore) {
        this.idea = idea;
        this.execution = execution;
        this.presentation = presentation;
        this.finalScore = finalScore;
    }

    public double getIdea() {
        return idea;
    }

    public double getExecution() {
        return execution;
    }

    public double getPresentation() {
        return presentation;
    }

    public double getFinalScore() {
        return finalScore;
    }
}
