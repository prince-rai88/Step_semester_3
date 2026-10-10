package uml_diagrams.class_problems.p1_exam_system;

import java.util.HashMap;
import java.util.Map;

public class ExamSystem {
    // Tracks submitted attempts per student ID + exam name to enforce single submission
    private final Map<String, Attempt> submittedAttempts = new HashMap<>();

    public Attempt startAttempt(Student student, Examination examination) {
        String key = student.getId() + "_" + examination.getExamName();
        if (submittedAttempts.containsKey(key)) {
            throw new IllegalStateException("Student has already submitted an attempt for this examination.");
        }
        System.out.println("Examination '" + examination.getExamName() + "' started by " + student.getName() + ".");
        return new Attempt(student, examination);
    }

    public void answerQuestion(Attempt attempt, int questionId, String answer) {
        attempt.answerQuestion(questionId, answer);
        System.out.println("Question " + questionId + " answered with '" + answer + "'.");
    }

    public void submitAttempt(Attempt attempt) {
        attempt.submit();
        String key = attempt.getStudent().getId() + "_" + attempt.getExamination().getExamName();
        submittedAttempts.put(key, attempt);
        System.out.println("Examination '" + attempt.getExamination().getExamName() + "' submitted successfully.");
        System.out.println("Result for '" + attempt.getExamination().getExamName() + "' attempt: " + attempt.getResultSummary() +
                " (assuming Question 1 correct answer is 'A', Question 2 correct answer is 'B').");
    }

    public static void main(String[] args) {
        Examination mathQuiz = new Examination("Math Quiz");
        mathQuiz.addQuestion(new MultipleChoiceQuestion(1, "What is 2 + 2?", "A"));
        mathQuiz.addQuestion(new MultipleChoiceQuestion(2, "What is 3 * 3?", "B"));

        Student student = new Student("S101", "Student");
        ExamSystem system = new ExamSystem();

        Attempt attempt = system.startAttempt(student, mathQuiz);
        system.answerQuestion(attempt, 1, "A");
        system.answerQuestion(attempt, 2, "C");
        system.submitAttempt(attempt);
    }
}
