package abstraction_and_interface.assignment_problems.p1_drawing_canvas;

public class CircleShape extends Shape {
    public CircleShape(double radius) {
        super(radius, radius);
    }

    @Override
    public double calculateArea() {
        return Math.PI * width * height; // Assuming unequal scaling produces an ellipse
    }
}
