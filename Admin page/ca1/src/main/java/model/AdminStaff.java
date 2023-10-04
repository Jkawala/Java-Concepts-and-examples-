package model;

import model.Staff;

public class AdminStaff extends Staff {


    public AdminStaff(String name, String password) {
        super(name, password);
        this.role = ROLE.ADMIN;
        this.payRate = 100.00;
        this.id = employeeid;
        employeeid++;
    }

    @Override
    public String toString() {
        return "Admin " +
                "name='" + name + '\'' +
                ", password: '" + password + '\'' +
                ", id=" + id +
                ", role=" + role +
                ", payRate=" + payRate;
    }
}
