package vitals;

public interface BatteryManagementSystem {
    boolean isTemperatureValid(float temperature);
    boolean isSocValid(float soc);
    boolean isChargeRateValid(float chargeRate);
    boolean isBatteryOkay(float temperature, float soc, float chargeRate);
    boolean checkBatteryWithLog(float temperature, float soc, float chargeRate);
}
