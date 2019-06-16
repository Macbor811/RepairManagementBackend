package pl.polsl.repairmanagementbackend.request;

public enum RequestStatus {

    OPEN("OPN"),
    IN_PROGRESS("PRO"),
    CANCELLED("CAN"),
    FINISHED("FIN");

    private final String text;

    RequestStatus(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}


