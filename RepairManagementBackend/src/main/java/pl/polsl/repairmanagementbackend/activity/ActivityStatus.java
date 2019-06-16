package pl.polsl.repairmanagementbackend.activity;

public enum ActivityStatus {

    OPEN("OPN"),
    IN_PROGRESS("PRO"),
    CANCELLED("CAN"),
    FINISHED("FIN");

    private final String text;

    ActivityStatus(final String text) {
        this.text = text;
    }

    @Override
    public final String toString() {
        return text;
    }

}


