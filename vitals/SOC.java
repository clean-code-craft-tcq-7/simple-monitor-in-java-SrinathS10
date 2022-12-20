package vitals;

class SOC extends BatteryProperty {
    public SOC(){
        this.setMaxValue(80f);
        this.setMinValue(20f);
        this.setWarningLimits(5);
        this.setPropertyName("SOC");
        this.enableWarning();
    }
}
