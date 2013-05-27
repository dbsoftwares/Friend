public class Friend {
    // strings to store friends info
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;

    // constructor with parameters to initialize class fields
    public Friend(String firstName, String lastName, String phoneNumber, String address) {
  this.firstName = firstName;
	this.lastName = lastName;
	this.phoneNumber = phoneNumber;
	this.address = address;
    }

    // method to return full name
    public String getFullName() {
	return this.firstName + " " + this.lastName;
    }
    
    
    // override toString() method from object class to represent object as string
    @Override
    public String toString() {
	return this.firstName + ":" + this.lastName + ":" + this.phoneNumber
		+ ":" + this.address;
    }

    
}
