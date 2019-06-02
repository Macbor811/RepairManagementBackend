package pl.polsl.repairmanagementbackend.employee;

public enum EmployeeUserRole {
    ADMIN("ADM"),
    MANAGER("MAN"),
    WORKER("WRK");

    private final String text;

    EmployeeUserRole(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
