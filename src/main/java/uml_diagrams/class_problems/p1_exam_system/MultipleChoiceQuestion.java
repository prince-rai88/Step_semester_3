package uml_diagrams.class_problems.p1_exam_system;

public class MultipleChoiceQuestion implements Question {
    private final int id;
    private final String prompt;
    private final String correctAnswer;

    public MultipleChoiceQuestion(int id, String prompt, String correctAnswer) {
        this.id = id;
        this.prompt = prompt;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getPrompt() {
        return prompt;
    }

    @Override
    public boolean checkAnswer(String studentAnswer) {
        return correctAnswer != null && correctAnswer.trim().equalsIgnoreCase(studentAnswer != null ? studentAnswer.trim() : "");
    }
}
