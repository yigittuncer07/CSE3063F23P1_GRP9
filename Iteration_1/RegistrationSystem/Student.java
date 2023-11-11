import java.util.ArrayList;

class Student extends User{
    private Transcript transcript;
    private String studentId;
    private Advisor Advisor;
    private ArrayList<Course> registeredCourses;

    public void printTranscriptInformation(Transcript transcript){
        transcript.toString();
    }

    public boolean registerToCourse(Advisor advisor, String stdID){
        return false;
    }
}
