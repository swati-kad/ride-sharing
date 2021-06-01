package Model;
/**
 * @author Swati
 *
 */
public class Vehicle {
	
	private String owner;
	private String name;
	private String registrationNum;

	public Vehicle() {
	}

	public Vehicle(String owner, String name, String registrationNum) {
		super();
		this.owner = owner;
		this.name = name;
		this.registrationNum = registrationNum;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegistrationNum() {
		return registrationNum;
	}

	public void setRegistrationNum(String registrationNum) {
		this.registrationNum = registrationNum;
	}

	@Override
	public String toString() {
		return "Vehicle [owner=" + owner + ", name=" + name + ", registrationNum=" + registrationNum + "]";
	}
}
