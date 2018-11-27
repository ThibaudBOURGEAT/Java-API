package fr.ynov.dap.dap.data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class AppUser {

	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
	private List<GoogleAccount> googleAccount;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
	private List<OutlookAccount> microsoftAccount;
	
	
	public void addGoogleAccount(GoogleAccount account){
	    account.setOwner(this);
	    this.getGoogleAccounts().add(account);
	}


	public List<GoogleAccount> getGoogleAccounts() {
		return googleAccount;
	}

	public void addMicrosoftAccount(OutlookAccount account){
	    account.setOwner(this);
	    this.getOutlookAccount().add(account);
	}

	public List<OutlookAccount> getOutlookAccount() {
		return microsoftAccount;
	}

	
}
