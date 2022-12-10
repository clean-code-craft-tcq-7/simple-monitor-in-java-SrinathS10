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
    public void printEntityStatus(ValueValidator validator,float value, String msg){
        if(!validator.isValueValid(value)){
            System.out.println(msg);
        }
    }

    @Override
    public void printBatteryStatus(float temperature, float soc, float chargeRate) {
        printEntityStatus(this::isTemperatureValid,temperature,"Temperature is out of range!");
        printEntityStatus(this::isSocValid,soc,"State of charge is out of range!");
        printEntityStatus(this::isChargeRateValid,chargeRate,"Charge Rate is out of range!");
    }
}
