package fr.ynov.dap.dap.data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class OutlookAccount {
	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "owner")
	private Token token;

	@ManyToOne
	private AppUser owner;
	
	private String tenantId;
	

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	
	public void addToken(Token token){
		this.token = token;
	    token.setOwner(this);
	}

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

	public Token getToken() {
		return token;
	}

	public void setToken(Token tokenResponse) {
		this.token = tokenResponse;
	}
	
	
}
