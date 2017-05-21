package za.co.entelect.domain.command.code;

public enum Code {
    DO_NOTHING(0),
    FIRESHOT(1);

    private final int value;
    private Code(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
