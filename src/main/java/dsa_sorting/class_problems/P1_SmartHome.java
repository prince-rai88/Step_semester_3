package dsa_sorting.class_problems;

import java.util.*;

public class P1_SmartHome {

    public interface Dimmable {
        void dim(int level);
    }

    public interface Schedulable {
        void schedule(String time);
    }

    public interface EnergyMonitorable {
        int getEnergyUsage();
    }

    public static abstract class Device {
        protected final String id;
        protected boolean isOn;

        public Device(String id) {
            this.id = id;
            this.isOn = false;
        }

        public String getId() {
            return id;
        }

        public boolean isOn() {
            return isOn;
        }

        public void turnOn() {
            this.isOn = true;
        }

        public void turnOff() {
            this.isOn = false;
        }
    }

    public static class Light extends Device implements Dimmable, Schedulable {
        private int brightness;
        private String scheduledTime;

        public Light(String id) {
            super(id);
            this.brightness = 100;
        }

        @Override
        public void dim(int level) {
            this.brightness = level;
        }

        @Override
        public void schedule(String time) {
            this.scheduledTime = time;
        }

        public int getBrightness() {
            return brightness;
        }

        public String getScheduledTime() {
            return scheduledTime;
        }
    }

    public static class Fan extends Device implements Schedulable {
        private String scheduledTime;

        public Fan(String id) {
            super(id);
        }

        @Override
        public void schedule(String time) {
            this.scheduledTime = time;
        }

        public String getScheduledTime() {
            return scheduledTime;
        }
    }

    public static class SmartPlug extends Device implements EnergyMonitorable {
        private final int energyKWh;

        public SmartPlug(String id, int energyKWh) {
            super(id);
            this.energyKWh = energyKWh;
        }

        @Override
        public int getEnergyUsage() {
            return energyKWh;
        }
    }

    public static class SmartHomeController {
        private final Map<String, Device> devices = new HashMap<>();

        public void registerDevice(Device device) {
            devices.put(device.getId(), device);
        }

        public String executeCommand(String commandLine) {
            String[] parts = commandLine.trim().split("\\s+");
            if (parts.length == 0 || parts[0].isEmpty()) return "";

            String action = parts[0].toUpperCase();
            if (parts.length < 2) return "Invalid command";
            String deviceId = parts[1];

            Device device = devices.get(deviceId);
            if (device == null) {
                return deviceId + " not found";
            }

            switch (action) {
                case "ON":
                    device.turnOn();
                    return deviceId + " is ON";

                case "OFF":
                    device.turnOff();
                    return deviceId + " is OFF";

                case "DIM":
                    if (device instanceof Dimmable) {
                        int level = Integer.parseInt(parts[2]);
                        ((Dimmable) device).dim(level);
                        return deviceId + " dimmed to " + level;
                    } else {
                        return deviceId + " rejected: DIM unsupported";
                    }

                case "SCHEDULE":
                    if (device instanceof Schedulable) {
                        String time = parts[2];
                        ((Schedulable) device).schedule(time);
                        return deviceId + " scheduled " + time;
                    } else {
                        return deviceId + " rejected: SCHEDULE unsupported";
                    }

                case "ENERGY":
                    if (device instanceof EnergyMonitorable) {
                        int energy = ((EnergyMonitorable) device).getEnergyUsage();
                        return deviceId + " energy " + energy + " kWh";
                    } else {
                        return deviceId + " rejected: ENERGY unsupported";
                    }

                default:
                    return deviceId + " rejected: " + action + " unsupported";
            }
        }
    }

    public static void main(String[] args) {
        SmartHomeController controller = new SmartHomeController();
        controller.registerDevice(new Light("L1"));
        controller.registerDevice(new Fan("F1"));
        controller.registerDevice(new SmartPlug("P1", 12));

        String[] commands = {
            "ON L1",
            "DIM L1 40",
            "DIM F1 30",
            "SCHEDULE F1 22:00",
            "ENERGY P1",
            "ENERGY L1"
        };

        for (String cmd : commands) {
            System.out.println(controller.executeCommand(cmd));
        }
    }
}
