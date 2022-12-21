package vitals;

@FunctionalInterface
public interface ValueValidator {
    boolean isValueValid(float value);
}
