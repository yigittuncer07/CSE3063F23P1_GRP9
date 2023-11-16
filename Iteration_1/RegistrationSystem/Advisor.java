import java.util.ArrayList;
import java.util.Scanner;

class Advisor extends Lecturer {

    private ArrayList<ArrayList<Course>> drafts;

    public Advisor() {
    }

    public boolean registrationApproval(ArrayList<Course> course) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you approve the draft? (Type 'yes' or 'no'):");
        String userResponse = scanner.nextLine();
        scanner.close();

        if (userResponse.equalsIgnoreCase("yes")) {
            System.out.println("Draft approved!");
            return true;
        } else if (userResponse.equalsIgnoreCase("no")) {
            System.out.println("Draft is not approved.");
        } else {
            System.out.println("Invalid input. Please type 'yes' or 'no'.");
        }
        return false;
    }

    

    public void addDraft(ArrayList<Course> draft){
        this.drafts.add(draft);
    }

    public ArrayList<ArrayList<Course>> getDrafts() {
        return drafts;
    }

    public void setDrafts(ArrayList<ArrayList<Course>> drafts) {
        this.drafts = drafts;
    }

}
