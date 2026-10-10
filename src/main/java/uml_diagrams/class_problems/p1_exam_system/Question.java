package uml_diagrams.class_problems.p1_exam_system;

public interface Question {
    int getId();
    String getPrompt();
    boolean checkAnswer(String studentAnswer);
}
