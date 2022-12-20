package vitals;

public class BMSImplementation implements BatteryManagementSystem {
    BatteryProperty temperature, soc, chargeRate;

    public BMSImplementation() {
        this.temperature = new Temperature();
        this.soc = new SOC();
        this.chargeRate = new ChargeRate();
    }

    @Override
    public boolean isTemperatureValid(float temperatureVal) {
        return !this.temperature.isBreached(temperatureVal);
    }

    @Override
    public boolean isSocValid(float socVAl) {
        return !this.soc.isBreached(socVAl);
    }

    @Override
    public boolean isChargeRateValid(float chargeRateVal) {
        return !this.chargeRate.isMaxValueBreached(chargeRateVal);
    }

    @Override
    public boolean isBatteryOkay(float temperatureVal, float socVal, float chargeRateVal) {
        return isTemperatureValid(temperatureVal) && isSocValid(socVal) && isChargeRateValid(chargeRateVal);
    }

    // for I/O
    public void printPropertyErrorStatus(ValueValidator validator, float value, BatteryProperty property, String msgType) {
        if(validator.isValueValid(value)){
           System.out.println(Message.getMessage(property,msgType));
        }
    }

    // for I/O
    public void printPropertyWarningStatus(ValueValidator validator, float value, BatteryProperty property, String msgType) {
        if(property.enableWarning  && validator.isValueValid(value)){
            System.out.println(Message.getMessage(property,msgType));
        }
    }

    @Override
    public void printBatteryStatus(float temperatureVal, float socVal, float chargeRateVal) {
        printPropertyErrorStatus(this.temperature::isMaxValueBreached,temperatureVal, this.temperature,Message.highBreach);
        printPropertyErrorStatus(this.temperature::isMinValueBreached,temperatureVal, this.temperature,Message.lowBreach);
        printPropertyErrorStatus(this.soc::isMaxValueBreached,socVal,this.soc,Message.highBreach);
        printPropertyErrorStatus(this.soc::isMinValueBreached,socVal,this.soc,Message.lowBreach);
        printPropertyErrorStatus(this.chargeRate::isMaxValueBreached,chargeRateVal,this.chargeRate,Message.highBreach);

        // Warning messages
        printPropertyWarningStatus(this.temperature::isValueAtMaximumWarning,temperatureVal,this.temperature,Message.highWarn);
        printPropertyWarningStatus(this.temperature::isValueAtMinimumWarning,temperatureVal,this.temperature,Message.lowWarn);
        printPropertyWarningStatus(this.soc::isValueAtMaximumWarning,socVal,this.soc,Message.highWarn);
        printPropertyWarningStatus(this.soc::isValueAtMinimumWarning,socVal,this.soc,Message.lowWarn);
        printPropertyWarningStatus(this.chargeRate::isValueAtMaximumWarning,chargeRateVal,this.chargeRate,Message.highWarn);
    }
}
