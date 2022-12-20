package vitals;

class ChargeRate extends BatteryProperty {
    public ChargeRate(){
        this.setMaxValue(0.8f);
        this.setPropertyName("CHARGE_RATE");
        this.setWarningLimits(5);
        this.enableWarning();
    }
}
