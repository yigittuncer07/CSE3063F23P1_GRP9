import java.util.ArrayList;

class Advisor extends Lecturer{

    private ArrayList<Course> registrations;
    
    public Advisor(){

    }
    public void registrationApproval(ArrayList<Course> course){
  
    }

	public ArrayList<Course> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(ArrayList<Course> registrations) {
		this.registrations = registrations;
	}
}
