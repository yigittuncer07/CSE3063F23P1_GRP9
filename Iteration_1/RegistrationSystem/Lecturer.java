public class Lecturer extends Staff {
    
    private String Proffesion;

    public Lecturer(){

    }
    
    public Lecturer(String Proffesion){
        this.Proffesion=Proffesion;

    }

	public String getProffesion() {
		return Proffesion;
	}
    
	public void setProffesion(String proffesion) {
		Proffesion = proffesion;
	}
}