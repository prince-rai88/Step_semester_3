package uml_diagrams.class_problems.p1_exam_system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Examination {
    private final String examName;
    private final List<Question> questions;

    public Examination(String examName) {
        this.examName = examName;
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public String getExamName() {
        return examName;
    }

    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }

    public Question getQuestion(int id) {
        for (Question q : questions) {
            if (q.getId() == id) {
                return q;
            }
        }
        return null;
    }
}
