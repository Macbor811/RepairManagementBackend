package pl.polsl.repairmanagementbackend.activity;

import java.util.Optional;

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

    public static Optional<ActivityStatus> fromString(String string){
        for (var value : ActivityStatus.values()){
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


