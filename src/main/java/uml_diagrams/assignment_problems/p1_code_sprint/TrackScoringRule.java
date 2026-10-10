package uml_diagrams.assignment_problems.p1_code_sprint;

public interface TrackScoringRule {
    String getTrackName();
    double calculateFinalScore(double idea, double execution, double presentation);
}
