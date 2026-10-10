package uml_diagrams.assignment_problems.p1_code_sprint;

public class OpenTrack implements TrackScoringRule {
    @Override
    public String getTrackName() {
        return "Open track";
    }

    @Override
    public double calculateFinalScore(double idea, double execution, double presentation) {
        return (idea + execution + presentation) / 3.0;
    }
}
