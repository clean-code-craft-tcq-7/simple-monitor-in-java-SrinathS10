package vitals;

public class BatteryProperty {
    float minValue;
    float maxValue;
    float minWarnValue;
    float maxWarnValue;
    boolean enableWarning;
    String propertyName;

    public void setPropertyName(String propertyName){
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }

    public void setMaxWarnValue(float maxWarnValue) {
        this.maxWarnValue = maxWarnValue;
    }

    public void setMinWarnValue(float minWarnValue) {
        this.minWarnValue = minWarnValue;
    }

    public float getMaxValue() {
        return maxValue;
    }

    public float getMinValue() {
        return minValue;
    }

    public float getMaxWarnValue() {
        return maxWarnValue;
    }

    public float getMinWarnValue() {
        return minWarnValue;
    }

    public void setWarningLimits(float limit) {
        float limitVal = (limit/100) * maxValue;
        this.setMaxWarnValue(maxValue - limitVal);
        this.setMinWarnValue(minValue + limitVal);
    }

    public void enableWarning() {
        this.enableWarning = true;
    }

    public void disableWarning() {
        this.enableWarning = false;
    }

    public boolean isMaxValueBreached(float value){
        return value > this.maxValue;
    }

    public boolean isMinValueBreached(float value) {
        return value < this.minValue;
    }

    public boolean isBreached(float value) {
        return isMinValueBreached(value) || isMaxValueBreached(value);
    }

    public boolean isValueAtMinimumWarning(float value) {
        return value>this.minValue && value<=this.minWarnValue;
    }

    public boolean isValueAtMaximumWarning(float value) {
        return value>this.maxWarnValue && value<=this.maxValue;
    }

    public boolean isValueAtWarning(float value){
        return isValueAtMaximumWarning(value) || isValueAtMinimumWarning(value);
    }
}
