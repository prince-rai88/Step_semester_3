package uml_diagrams.class_problems.p1_exam_system;

import java.util.HashMap;
import java.util.Map;

public class Attempt {
    public enum Status {
        IN_PROGRESS,
        SUBMITTED
    }

    private final Student student;
    private final Examination examination;
    private final Map<Integer, String> studentAnswers;
    private Status status;
    private int score;

    public Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
        this.studentAnswers = new HashMap<>();
        this.status = Status.IN_PROGRESS;
        this.score = 0;
    }

    public Student getStudent() {
        return student;
    }

    public Examination getExamination() {
        return examination;
    }

    public Status getStatus() {
        return status;
    }

    public int getScore() {
        return score;
    }

    public void answerQuestion(int questionId, String answer) {
        if (status == Status.SUBMITTED) {
            throw new IllegalStateException("Cannot change answers after submission.");
        }
        studentAnswers.put(questionId, answer);
    }

    public void submit() {
        if (status == Status.SUBMITTED) {
            throw new IllegalStateException("Attempt is already submitted.");
        }
        status = Status.SUBMITTED;
        evaluate();
    }

    private void evaluate() {
        int correctCount = 0;
        for (Question q : examination.getQuestions()) {
            String answer = studentAnswers.get(q.getId());
            if (answer != null && q.checkAnswer(answer)) {
                correctCount++;
            }
        }
        this.score = correctCount;
    }

    public String getResultSummary() {
        return score + "/" + examination.getQuestions().size() + " correct";
    }
}
