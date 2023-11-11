import java.util.ArrayList;

class Student extends User{
    private Transcript transcript;
    private String studentId;
    private Advisor advisor;
    private ArrayList<Course> registeredCourses;

    public void printTranscriptInformation(Transcript transcript){
        transcript.toString();
    }

    public boolean registerToCourse(Advisor advisor, String stdID){
        return false;
    }

        // Getter methods
        public Transcript getTranscript() {
            return transcript;
        }
    
        public String getStudentId() {
            return studentId;
        }
    
        public Advisor getAdvisor() {
            return advisor;
        }
    
        public ArrayList<Course> getRegisteredCourses() {
            return registeredCourses;
        }
    
        // Setter methods
        public void setTranscript(Transcript transcript) {
            this.transcript = transcript;
        }
    
        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }
    
        public void setAdvisor(Advisor advisor) {
            this.advisor = advisor;
        }
    
        public void setRegisteredCourses(ArrayList<Course> registeredCourses) {
            this.registeredCourses = registeredCourses;
        }
}
