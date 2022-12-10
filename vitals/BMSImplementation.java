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
}
