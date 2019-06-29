package pl.polsl.repairmanagementbackend.employee;


import java.util.Optional;

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

    public static Optional<EmployeeUserRole> fromString(String string){
        for (var value : EmployeeUserRole.values()){
            if (string.equals(value.toString())){
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }
}
