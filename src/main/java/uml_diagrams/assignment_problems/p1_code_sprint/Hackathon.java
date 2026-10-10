package uml_diagrams.assignment_problems.p1_code_sprint;

import java.util.*;

public class Hackathon {
    public enum State {
        OPEN,
        JUDGING,
        PUBLISHED
    }

    private final String name;
    private final List<Team> teams = new ArrayList<>();
    private final Set<Student> registeredStudents = new HashSet<>();
    private State state = State.OPEN;

    public Hackathon(String name) {
        this.name = name;
    }

    public boolean registerTeam(Team team) {
        if (state != State.OPEN) {
            System.out.println("Registration failed: Hackathon registration is closed.");
            return false;
        }

        if (team.getMembers().size() < 2 || team.getMembers().size() > 4) {
            System.out.println("Registration failed: A team must have 2 to 4 members.");
            return false;
        }

        for (Student s : team.getMembers()) {
            if (registeredStudents.contains(s)) {
                System.out.println("Registration failed: Student " + s.getName() + " is already in another team.");
                return false;
            }
        }

        teams.add(team);
        registeredStudents.addAll(team.getMembers());
        System.out.println("Team " + team.getName() + " registered (" + team.getMembers().size() + " members, " + team.getTrack().getTrackName() + ").");
        return true;
    }

    public boolean submitProject(Team team, Project project) {
        if (team.getProject() != null) {
            System.out.println("Submission failed: Team has already submitted a project.");
            return false;
        }
        team.submitProject(project);
        System.out.println("Project '" + project.getName() + "' submitted by " + team.getName() + ".");
        return true;
    }

    public boolean scoreProject(Team team, double idea, double execution, double presentation) {
        if (state == State.PUBLISHED) {
            System.out.println("Rescore rejected: Results have already been published.");
            return false;
        }

        Project p = team.getProject();
        if (p == null) {
            System.out.println("Scoring failed: No project submitted yet.");
            return false;
        }

        double finalScore = team.getTrack().calculateFinalScore(idea, execution, presentation);
        Score score = new Score(idea, execution, presentation, finalScore);
        p.setScore(score);
        System.out.printf(Locale.US, "Score recorded for '%s'. Final score: %.2f.%n", p.getName(), finalScore);
        return true;
    }

    public void publishResults() {
        this.state = State.PUBLISHED;
        System.out.println("Results published.");
    }
}
