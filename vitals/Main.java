package vitals;

public class Main {
    private static BatteryManagementSystem BMS;

    public static void main(String[] args) {
        Message.setMessageLanguage(args);

        BMS = new BMSImplementation();
        TestBMS.testPropertyStatus(BMS);
        TestBMS.testBatteryStatus(BMS);

        // for printing battery status with I/O
        BMS.printBatteryStatus(25.5f, 75f, 1.2f);
        System.out.println("----------");
        BMS.printBatteryStatus(1.5f, 23.5f, 0.775f);
        System.out.println("----------");
        BMS.printBatteryStatus(60f, 80f, 0f);
        System.out.println("----------");
        BMS.printBatteryStatus(35f, 50f, 0.4f);
        System.out.println("----------");
    }
}
