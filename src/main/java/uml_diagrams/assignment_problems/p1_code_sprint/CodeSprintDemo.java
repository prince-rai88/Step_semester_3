package uml_diagrams.assignment_problems.p1_code_sprint;

public class CodeSprintDemo {
    public static void main(String[] args) {
        Hackathon hackathon = new Hackathon("Code Sprint");

        // 1. Team 'ByteBusters' registers with 3 members (Asha, Ravi, Neha) in the Innovation track.
        Team byteBusters = new Team("ByteBusters", new InnovationTrack());
        byteBusters.addMember(new Student("Asha"));
        byteBusters.addMember(new Student("Ravi"));
        byteBusters.addMember(new Student("Neha"));
        hackathon.registerTeam(byteBusters);

        // 2. Team 'SoloCoder' registers with 1 member (Kiran) in the Open track.
        Team soloCoder = new Team("SoloCoder", new OpenTrack());
        soloCoder.addMember(new Student("Kiran"));
        hackathon.registerTeam(soloCoder);

        // 3. ByteBusters submits project 'SmartAttend'.
        Project smartAttend = new Project("SmartAttend");
        hackathon.submitProject(byteBusters, smartAttend);

        // 4. Judge scores 'SmartAttend': idea 8, execution 7, presentation 9.
        hackathon.scoreProject(byteBusters, 8, 7, 9);

        // 5. Organiser publishes results.
        hackathon.publishResults();

        // 6. Judge attempts to change the idea score of 'SmartAttend' to 10.
        hackathon.scoreProject(byteBusters, 10, 7, 9);
    }
}
