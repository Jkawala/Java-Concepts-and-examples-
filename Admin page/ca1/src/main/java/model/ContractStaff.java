package model;

import model.Staff;

public class ContractStaff extends Staff {

    public ContractStaff(String name, String password) {
        super(name, password);

        //this.role = Role.DEVELOPER;
        this.role = ROLE.DEVELOPER;
        this.payRate = 75.00;
        this.id = Staff.employeeid;
        Staff.employeeid++;
    }

    @Override
    public String toString() {
        return "Contractor " +
                "name='" + name + '\'' +
                ", id=" + id +
                ", password: dave'" + password + '\'' +
                ", role=" + role +
                ", payRate: $" + payRate;
    }

}
