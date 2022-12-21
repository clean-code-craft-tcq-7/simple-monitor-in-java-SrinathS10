package vitals;

public class Temperature extends BatteryProperty {
    public Temperature(){
        this.setMaxValue(45f);
        this.setMinValue(0f);
        this.setWarningLimits(5);
        this.setPropertyName("TEMPERATURE");
        this.enableWarning();
    }
}
