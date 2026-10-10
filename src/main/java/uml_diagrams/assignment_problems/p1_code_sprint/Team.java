package uml_diagrams.assignment_problems.p1_code_sprint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team {
    private final String name;
    private final List<Student> members;
    private final TrackScoringRule track;
    private Project project;

    public Team(String name, TrackScoringRule track) {
        this.name = name;
        this.track = track;
        this.members = new ArrayList<>();
    }

    public void addMember(Student student) {
        members.add(student);
    }

    public String getName() {
        return name;
    }

    public List<Student> getMembers() {
        return Collections.unmodifiableList(members);
    }

    public TrackScoringRule getTrack() {
        return track;
    }

    public Project getProject() {
        return project;
    }

    public void submitProject(Project project) {
        this.project = project;
    }
}
