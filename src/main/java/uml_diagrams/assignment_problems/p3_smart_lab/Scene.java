package uml_diagrams.assignment_problems.p3_smart_lab;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private final String name;
    private final List<SceneStep> steps = new ArrayList<>();

    public Scene(String name) {
        this.name = name;
    }

    public void addStep(SceneStep step) {
        steps.add(step);
    }

    public void execute(List<Device> devices) {
        System.out.println("Scene '" + name + "' started.");
        int actionsApplied = 0;
        for (SceneStep step : steps) {
            for (Device device : devices) {
                if (step.apply(device)) {
                    actionsApplied++;
                }
            }
        }
        System.out.println("Scene '" + name + "' completed: " + actionsApplied + " actions applied.");
    }
}
