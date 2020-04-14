package application;

import javafx.scene.control.Button;

public class Person {
	private String firstName = null;
	private String middleName = null;
	private String lastName = null;
	private Button button;
	
	public Person() {
		
	}
	
	public Person(String firstName, String middleName, String lastName, Button button) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.button = button;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}
}
