package vitals;

public class TestBMS {
    final static float maxTemperature = 45f;
    final static float minTemperature = 0f;
    final static float maxSoc = 80f;
    final static float minSoc = 20f;
    final static float maxChargeRate = 0.8f;

    public static void testBatteryStatus(BatteryManagementSystem BMS) {
        assert(BMS.isBatteryOkay(25F, 70, 0.7f));
        assert(!BMS.isBatteryOkay(50F, 85, 0.0f));

        // below threshold
        assert (!BMS.isBatteryOkay(minTemperature - 5, minSoc, 0));
        assert (!BMS.isBatteryOkay(maxTemperature, minSoc - 20, 0));

        // above threshold
        assert (!BMS.isBatteryOkay(maxTemperature + 5, minSoc, 0));
        assert (!BMS.isBatteryOkay(minTemperature, maxSoc + 10, 0));
        assert (!BMS.isBatteryOkay(minTemperature, minSoc, maxChargeRate + 0.5f));

        // threshold
        assert (BMS.isBatteryOkay(minTemperature, minSoc, maxChargeRate));
        assert (BMS.isBatteryOkay(maxTemperature, maxSoc, maxChargeRate));

        // additional tests
        assert (!BMS.isBatteryOkay(minTemperature,-5,0));
        assert (!BMS.isBatteryOkay(maxTemperature,50,1.5f));
        assert (!BMS.isBatteryOkay(-280.0f,minSoc,0.1f));
        assert (BMS.isBatteryOkay(12f,60,0.4f));
    }

    public static void testPropertyStatus(BatteryManagementSystem BMS) {
        //below threshold
        assert (!BMS.isSocValid(minSoc - 0.1f));
        assert (!BMS.isTemperatureValid(minTemperature - 0.01f));

        //above threshold
        assert (!BMS.isSocValid(maxSoc + 0.1f));
        assert (!BMS.isChargeRateValid(maxChargeRate + 0.01f));
        assert (!BMS.isTemperatureValid(maxTemperature + 0.01f));

        //threshold
        assert (BMS.isChargeRateValid(maxChargeRate));
        assert (BMS.isTemperatureValid(maxTemperature));
        assert (BMS.isTemperatureValid(maxTemperature));
        assert (BMS.isSocValid(minSoc));
        assert (BMS.isSocValid(maxSoc));

        //additional tests
        assert (BMS.isSocValid(50));
        assert (BMS.isTemperatureValid(10.5f));
        assert (!BMS.isSocValid(0));
        assert (!BMS.isChargeRateValid(1.2f));
        assert (!BMS.isTemperatureValid(105f));
    }

    public static void testMessages(){
        Message.setMessageLanguage(new String[]{"EN"});
        BatteryProperty prop;
        String message;

        prop = new SOC();
        message = Message.getMessage(prop,Message.highBreach).toUpperCase();
        assert (message.contains("BREACH") && message.contains("CHARGE LIMIT"));
        message = Message.getMessage(prop,Message.lowWarn).toUpperCase();
        assert (message.contains("WARNING") && message.contains("APPROACHING DISCHARGE"));

        prop = new ChargeRate();
        message = Message.getMessage(prop,Message.highWarn).toUpperCase();
        assert (message.contains("WARNING") && message.contains("CHARGE RATE"));
        message = Message.getMessage(prop,Message.highBreach).toUpperCase();
        assert (message.contains("BREACH") && message.contains("CHARGE RATE"));
    }
}
