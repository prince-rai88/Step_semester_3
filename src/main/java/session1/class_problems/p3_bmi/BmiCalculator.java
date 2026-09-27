package session1.class_problems.p3_bmi;

public class BmiCalculator {
    public String getBmiStatus(double bmi) {
        if (bmi < 18.5) return "Underweight";
        if (bmi <= 24.9) return "Normal";
        if (bmi <= 29.9) return "Overweight";
        return "Obese";
    }
    
    public void printWellnessReport(double[] heights, double[] weights) {
        for (int i = 0; i < heights.length; i++) {
            double h = heights[i];
            double w = weights[i];
            double bmi = w / (h * h);
            String status = getBmiStatus(bmi);
            System.out.printf("Person %d — Height: %.2f m, Weight: %.2f kg | BMI: %.2f | Status: %s%n", (i+1), h, w, bmi, status);
        }
    }
}
