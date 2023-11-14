import java.util.ArrayList;
import java.util.Scanner;


class Advisor extends Lecturer{

    private ArrayList<Course> registrations;
    
    public Advisor(){

    }
	
    public void registrationApproval(ArrayList<Course> course){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you approve the draft? (Type 'yes' or 'no'):");
        String userResponse = scanner.nextLine();

        if (userResponse.equalsIgnoreCase("yes")) {
            System.out.println("Draft approved!");
        } else if (userResponse.equalsIgnoreCase("no")) {
            System.out.println("Draft is not approved.");
        } else {
            System.out.println("Invalid input. Please type 'yes' or 'no'.");
        }

        scanner.close();
    }

	public ArrayList<Course> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(ArrayList<Course> registrations) {
		this.registrations = registrations;
	}
}
