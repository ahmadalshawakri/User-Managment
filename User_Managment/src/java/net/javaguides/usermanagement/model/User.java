package net.javaguides.usermanagement.model;

/**
 * User.java
 * This is a model class represents a User entity
 * @author Ramesh Fadatare
 *
 */
public class User {
	protected int id;
	protected String name;
	protected String email;
	protected String country;
        protected String initial_deposit;
        
	
	public User() {
	}
	
	public User(String name, String email, String country,String initial_deposit) {
		super();
		this.name = name;
		this.email = email;
		this.country = country;
                this.initial_deposit=initial_deposit;
                
	}

	public User(int id, String name, String email, String country,String initial_deposit) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.country = country;
                this.initial_deposit=initial_deposit;
                
	}

   
    public String getInitial_deposit() {
        return initial_deposit;
    }

    public void setInitial_deposit(String initial_deposit) {
        this.initial_deposit = initial_deposit;
    }

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
