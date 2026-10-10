package uml_diagrams.assignment_problems.p3_smart_lab;

public interface SceneStep {
    // Returns true if applied to the device, false if skipped
    boolean apply(Device device);
}
