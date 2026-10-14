package dsa_sorting.class_problems;

import java.util.*;

public class P3_StudentMarksGrid {

    public static class StudentRecord {
        private final String name;
        private final Integer[] marks; // null if subject mark is missing

        public StudentRecord(String name, Integer[] marks) {
            this.name = name;
            this.marks = marks;
        }

        public String getName() {
            return name;
        }

        public Integer[] getMarks() {
            return marks;
        }

        public int getTotalMarks() {
            int total = 0;
            for (Integer m : marks) {
                if (m != null) {
                    total += m;
                }
            }
            return total;
        }
    }

    public static class MarksSystem {
        private final List<String> subjects;
        private final List<StudentRecord> students = new ArrayList<>();

        public MarksSystem(List<String> subjects) {
            this.subjects = new ArrayList<>(subjects);
        }

        public void addStudent(StudentRecord record) {
            students.add(record);
        }

        public void printTotals() {
            StringBuilder sb = new StringBuilder("Totals ");
            for (int i = 0; i < students.size(); i++) {
                StudentRecord s = students.get(i);
                sb.append(s.getName()).append(" ").append(s.getTotalMarks());
                if (i < students.size() - 1) {
                    sb.append(", ");
                }
            }
            System.out.println(sb);
        }

        public void printToppers() {
            StringBuilder sb = new StringBuilder("toppers ");
            for (int subIdx = 0; subIdx < subjects.size(); subIdx++) {
                String subName = subjects.get(subIdx);
                String topperName = "None";
                int highest = -1;

                for (StudentRecord student : students) {
                    Integer[] marks = student.getMarks();
                    if (subIdx < marks.length && marks[subIdx] != null) {
                        if (marks[subIdx] > highest) {
                            highest = marks[subIdx];
                            topperName = student.getName();
                        }
                    }
                }

                sb.append(subName).append(" ").append(topperName).append(" ").append(highest);
                if (subIdx < subjects.size() - 1) {
                    sb.append(", ");
                }
            }
            System.out.println(sb);
        }
    }

    public static void main(String[] args) {
        List<String> subjects = Arrays.asList("Math", "Sci", "Eng");
        MarksSystem system = new MarksSystem(subjects);

        system.addStudent(new StudentRecord("Anu", new Integer[]{80, 90, 70}));
        system.addStudent(new StudentRecord("Ravi", new Integer[]{60, 85})); // missing Eng
        system.addStudent(new StudentRecord("Meena", new Integer[]{95, 75, 88}));

        system.printTotals();
        system.printToppers();
    }
}
