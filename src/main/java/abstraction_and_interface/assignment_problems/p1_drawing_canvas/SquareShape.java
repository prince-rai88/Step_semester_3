package abstraction_and_interface.assignment_problems.p1_drawing_canvas;

public class SquareShape extends Shape {
    public SquareShape(double side) {
        super(side, side);
    }

    @Override
    public double calculateArea() {
        return width * height; // Rectangle area if scaled unevenly
    }
}
