package model;

import model.Staff;

public class PermenentStaff extends Staff {

    public PermenentStaff(String name, String password) {
        super(name, password);
        this.role =ROLE.DEVELOPER;
        this.payRate = 50.00;
        this.id = Staff.employeeid;
        Staff.employeeid++;
    }

    @Override
    public String toString() {
        return "Staff " +
                "name: '" + name + '\'' +
                ", id: " + id +
                ", password: '" + password + '\'' +
                ", role: " + role +
                ", payRate: $" + payRate ;
    }
}
