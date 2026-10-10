package uml_diagrams.assignment_problems.p1_code_sprint;

public class InnovationTrack implements TrackScoringRule {
    @Override
    public String getTrackName() {
        return "Innovation track";
    }

    @Override
    public double calculateFinalScore(double idea, double execution, double presentation) {
        return (idea * 0.50) + (execution * 0.30) + (presentation * 0.20);
    }
}
