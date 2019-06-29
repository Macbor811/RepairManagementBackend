package pl.polsl.repairmanagementbackend.request;

import java.util.Optional;

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


    public static Optional<RequestStatus> fromString(String string){
        for (var value : RequestStatus.values()){
            if (string.equals(value.toString())){
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }

    public boolean hasEnded(){
        return this.equals(CANCELLED) || this.equals(FINISHED);
    }
}


