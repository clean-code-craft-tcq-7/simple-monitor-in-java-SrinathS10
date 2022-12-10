package vitals;

public class BMSImplementation implements BatteryManagementSystem {
    boolean isValueWithinLimits(float value, float lowerLimit,float upperLimit){
        return (value >= lowerLimit && value <= upperLimit);
    }

    @Override
    public boolean isTemperatureValid(float temperature) {
        return isValueWithinLimits(temperature, BMSLimits.minTemperature,BMSLimits.maxTemperature);
    }

    @Override
    public boolean isSocValid(float soc) {
        return isValueWithinLimits(soc,BMSLimits.minSoc,BMSLimits.maxSoc);
    }

    @Override
    public boolean isChargeRateValid(float chargeRate) {
        return chargeRate<=BMSLimits.maxChargeRate;
    }

    @Override
    public boolean isBatteryOkay(float temperature, float soc, float chargeRate) {
        return isTemperatureValid(temperature) && isSocValid(soc) && isChargeRateValid(chargeRate);
    }

    // for I/O
    @Override
    public void printBatteryStatus(float temperature, float soc, float chargeRate) {
        printTemperatureStatus(temperature);
        printSocStatus(soc);
        printChargeRateStatus(chargeRate);
    }

    public void printTemperatureStatus(float temperature){
        if (!isTemperatureValid(temperature)) {
            System.out.println("Temperature is out of range!");
        }
    }
    public void printSocStatus(float soc){
        if (!isSocValid(soc)) {
            System.out.println("State of charge is out of range!");
        }
    }
    public void printChargeRateStatus(float chargeRate){
        if (!isChargeRateValid(chargeRate)) {
            System.out.println("Charge Rate is out of range!");
        }
    }
}
