package fr.ynov.dap.dap.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class GoogleAccount {

	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	@ManyToOne
	private AppUser owner;

	public void setId(int id) {
		this.id = id;
	}

	public void setOwner(AppUser owner) {
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public AppUser getOwner() {
		return owner;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
