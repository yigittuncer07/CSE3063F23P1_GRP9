public class StudentAffairsStaff extends Staff {
    
    private String workingField;

    public StudentAffairsStaff(){

    }
    public StudentAffairsStaff(String workingField){
        this.workingField=workingField;

    }

    public String getWorkingField() {
        return workingField;
    }

    public void setWorkingField(String workingField) {
        this.workingField = workingField;
    }

}
