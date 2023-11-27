public class Lecturer extends Staff {
    
    private String profession;
    
    public Lecturer(){

    }
    public Lecturer(String profession){
        this.profession=profession;
    }

	public String getProfession() {
		return profession;
	}
    
	public void setProfession(String proffesion) {
		profession = proffesion;
	}
}