package vitals;

public class Main {
    private static BatteryManagementSystem BMS;

    public static void testBatteryStatus() {
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

        // additional tests
        assert (!BMS.isBatteryOkay(BMSLimits.minTemperature,-5,0));
        assert (!BMS.isBatteryOkay(BMSLimits.maxTemperature,50,1.5f));
        assert (!BMS.isBatteryOkay(-280.0f,BMSLimits.minSoc,0.1f));
        assert (BMS.isBatteryOkay(12f,60,0.4f));
    }

    public static void testPropertyStatus() {
        //below threshold
        assert (!BMS.isSocValid(BMSLimits.minSoc - 0.1f));
        assert (!BMS.isTemperatureValid(BMSLimits.minTemperature - 0.01f));

        //above threshold
        assert (!BMS.isSocValid(BMSLimits.maxSoc + 0.1f));
        assert (!BMS.isChargeRateValid(BMSLimits.maxChargeRate + 0.01f));
        assert (!BMS.isTemperatureValid(BMSLimits.maxTemperature + 0.01f));

        //threshold
        assert (BMS.isChargeRateValid(BMSLimits.maxChargeRate));
        assert (BMS.isTemperatureValid(BMSLimits.maxTemperature));
        assert (BMS.isTemperatureValid(BMSLimits.maxTemperature));
        assert (BMS.isSocValid(BMSLimits.minSoc));
        assert (BMS.isSocValid(BMSLimits.maxSoc));

        //additional tests
        assert (BMS.isSocValid(50));
        assert (BMS.isTemperatureValid(10.5f));
        assert (!BMS.isSocValid(0));
        assert (!BMS.isChargeRateValid(1.2f));
        assert (!BMS.isTemperatureValid(105f));
    }


    public static void main(String[] args) {
        BMS = new BMSImplementation();
        testPropertyStatus();
        testBatteryStatus();

        // for printing battery status with I/O
        float temperature = 25.5f, soc = 75f, chargeRate = 1.2f;
        BMS.printBatteryStatus(temperature, soc, chargeRate);
        System.out.println("----------");
    }
}
