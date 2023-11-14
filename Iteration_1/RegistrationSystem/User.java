abstract class User {
    private String name;
    private String lastName;
    private String birthDate;
    private String address;
    private String ssn;
    private String email;
    private String password;

    public String getInfo(){
        return "Name: " + name + "\nLastname: " + lastName + "\nBirthdate: " + birthDate + "\nAddress: " + address + "\nSSN: " + ssn + "\nEmail: " + email;
    }
    
    // Getter methods
    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }

    public String getSsn() {
        return ssn;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}