package constructors.assignment_problems;

public class DeliverySlot {
    String orderId,timeSlot;
    DeliverySlot(String orderId,String timeSlot){
        this.orderId=orderId;
        this.timeSlot=timeSlot;
    }
    DeliverySlot(String orderId){
        this(orderId,"ASAP");
    }
    boolean isPeakHour(){

        return timeSlot.equals("12:00-13:00") || timeSlot.equals("13:00-14:00") || timeSlot.equals("19:00-20:00")
                || timeSlot.equals("20:00-21:00");
    }

    public static void main() {

        DeliverySlot d1=new DeliverySlot("1","12:00-13:00");
        DeliverySlot d2=new DeliverySlot("2");

        System.out.println(d1.isPeakHour());
        System.out.println(d2.isPeakHour());

    }

}
