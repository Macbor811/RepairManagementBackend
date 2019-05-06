package pl.polsl.repairmanagementbackend;

public enum UserRole {
    ADMIN("ADM"),
    MANAGER("MAN"),
    WORKER("WRK");

    private final String text;

    UserRole(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
