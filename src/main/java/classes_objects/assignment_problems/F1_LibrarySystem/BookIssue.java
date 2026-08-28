package main.java.classes_objects.assignment_problems.F1_LibrarySystem;


public class BookIssue {
    String borrower,title;
    int daysOverdue;
    BookIssue(String borrower,String title,int daysOverdue){
        this.borrower=borrower;
        this.title=title;
        this.daysOverdue=daysOverdue;
    }
    double fineAmount(){
        if(daysOverdue>0){
            return daysOverdue*5;
        }
        return 0;
    }
    boolean isSeverelyOverdue(){
        return daysOverdue>14;
    }
    static double totalFineCollected(BookIssue[] issues){
        double sum=0.0;
        for(BookIssue issue:issues){
            sum+=issue.fineAmount();
        }
        return sum;
    }
    public static void main(String[] args) {

        BookIssue[] issues = {
                new BookIssue("A", "Clean Code", 18),
                new BookIssue("B", "Effective Java", 5),
                new BookIssue("C", "Refactoring", 0),
                new BookIssue("D", "DSA Handbook", 21),
                new BookIssue("E", "Design Patterns", 9)
        };

        for (BookIssue issue : issues) {
            System.out.println(issue.title + " - " + issue.daysOverdue
                    + " days - "
                    + (issue.isSeverelyOverdue() ? "Severely overdue" : "OK"));
        }

        System.out.println("Total fine collected: Rs "
                + BookIssue.totalFineCollected(issues));
    }
}
