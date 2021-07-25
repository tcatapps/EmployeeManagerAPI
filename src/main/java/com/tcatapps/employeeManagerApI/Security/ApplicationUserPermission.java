package com.tcatapps.employeeManagerApI.Security;

public enum ApplicationUserPermission {
    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_WRITE("employee:wright");

    public final String permissions;

    ApplicationUserPermission(String permissions) {
        this.permissions = permissions;
    }

    public String getPermissions() {
        return permissions;
    }
}
