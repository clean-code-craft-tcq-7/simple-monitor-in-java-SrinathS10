package vitals;

public class Main {
    private static BatteryManagementSystem BMS;

    public static void testBatteryStatus(){
        assert(BMS.isBatteryOkay(25F, 70, 0.7f));
        assert(!BMS.isBatteryOkay(50F, 85, 0.0f));

        // below threshold
        assert (!BMS.isBatteryOkay(BMSLimits.minTemperature - 5, BMSLimits.minSoc, 0));
        assert (!BMS.isBatteryOkay(BMSLimits.maxTemperature, BMSLimits.minSoc - 20, 0));

        // above threshold
        assert (!BMS.isBatteryOkay(BMSLimits.maxTemperature + 5, BMSLimits.minSoc, 0));
        assert (!BMS.isBatteryOkay(BMSLimits.minTemperature, BMSLimits.maxSoc + 10, 0));
        assert (!BMS.isBatteryOkay(BMSLimits.minTemperature, BMSLimits.minSoc, BMSLimits.maxChargeRate + 0.5f));

        // threshold
        assert (BMS.isBatteryOkay(BMSLimits.minTemperature, BMSLimits.minSoc, BMSLimits.maxChargeRate));
        assert (BMS.isBatteryOkay(BMSLimits.maxTemperature, BMSLimits.maxSoc, BMSLimits.maxChargeRate));
    }

    // for I/O
    public static void printBatteryStatus(float temperature, float soc, float chargeRate){
        if(BMS.isBatteryOkay(temperature,soc,chargeRate)){
            System.out.println("Battery is okay");
        }
        else {
            System.out.println("Battery is not okay!!!");
            if (!BMS.isTemperatureValid(temperature)) {
                System.out.println("Temperature is out of range!");
            }
            if (!BMS.isSocValid(soc)) {
                System.out.println("State of Charge is out of range!");
            }
            if (!BMS.isChargeRateValid(chargeRate)) {
                System.out.println("Charge Rate is out of range!");
            }
        }
    }

    public static void main(String[] args) {
        BMS = new BMSImplementation();
        testBatteryStatus();

        // for checking battery status with I/O
        printBatteryStatus(25.5f, 90f, 1.2f);
    }
}
