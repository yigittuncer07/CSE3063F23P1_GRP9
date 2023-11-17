public class Staff extends User{
    private String staffID;
    private String department;

    public Staff() {
    }

    public Staff(String staffID, String department) {
        this.staffID = staffID;
        this.department = department;
    }

    public String getStaffID() {
        return staffID;
    }
    
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
}
