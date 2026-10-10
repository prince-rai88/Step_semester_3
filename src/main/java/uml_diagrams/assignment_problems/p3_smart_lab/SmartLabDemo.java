package uml_diagrams.assignment_problems.p3_smart_lab;

import java.util.Arrays;
import java.util.List;

public class SmartLabDemo {
    public static void main(String[] args) {
        // 1. Create 'Lab AC' with Power and Temperature, 'Ceiling Lights' with Power and Brightness, and 'Projector' with Power only.
        Device labAc = new Device("Lab AC");
        labAc.addCapability(new PowerCapability());
        labAc.addCapability(new TemperatureCapability());

        Device ceilingLights = new Device("Ceiling Lights");
        ceilingLights.addCapability(new PowerCapability());
        ceilingLights.addCapability(new BrightnessCapability());

        Device projector = new Device("Projector");
        projector.addCapability(new PowerCapability());

        List<Device> labDevices = Arrays.asList(labAc, ceilingLights, projector);

        // 2. Run scene 'Lecture Mode': turn everything ON, set brightness to 40%, set temperature to 24°C.
        Scene lectureMode = new Scene("Lecture Mode");

        // Step 1: Turn everything ON
        lectureMode.addStep(device -> {
            if (device.hasCapability(PowerCapability.TYPE)) {
                PowerCapability power = device.getCapability(PowerCapability.TYPE);
                power.setOn(true);
                System.out.println(device.getName() + ": ON.");
                return true;
            }
            return false;
        });

        // Step 2: Set brightness to 40%
        lectureMode.addStep(device -> {
            if (device.hasCapability(BrightnessCapability.TYPE)) {
                BrightnessCapability brightness = device.getCapability(BrightnessCapability.TYPE);
                if (brightness.setBrightness(device.getName(), 40)) {
                    System.out.println(device.getName() + ": brightness set to 40%.");
                    return true;
                }
            }
            return false;
        });

        // Step 3: Set temperature to 24°C
        lectureMode.addStep(device -> {
            if (device.hasCapability(TemperatureCapability.TYPE)) {
                TemperatureCapability temp = device.getCapability(TemperatureCapability.TYPE);
                if (temp.setTemperature(device.getName(), 24)) {
                    System.out.println(device.getName() + ": temperature set to 24°C.");
                    return true;
                }
            }
            return false;
        });

        lectureMode.execute(labDevices);

        // 3. Admin sets temperature of 'Lab AC' to 12°C.
        TemperatureCapability acTemp = labAc.getCapability(TemperatureCapability.TYPE);
        acTemp.setTemperature(labAc.getName(), 12);

        // 4. Admin adds Brightness to 'Projector' and sets its brightness to 70%.
        BrightnessCapability projectorBrightness = new BrightnessCapability();
        projector.addCapability(projectorBrightness);
        System.out.println("Projector: Brightness capability added.");
        if (projectorBrightness.setBrightness(projector.getName(), 70)) {
            System.out.println("Projector: brightness set to 70%.");
        }
    }
}
