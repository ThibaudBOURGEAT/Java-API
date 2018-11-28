package fr.ynov.dap.dap.data;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class Token.
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Token {
	
	/** The token type. */
	@JsonProperty("token_type")
	private String tokenType;
	
	/** The scope. */
	private String scope;
	
	/** The expires in. */
	@JsonProperty("expires_in")
	private int expiresIn;
	
	/** The access token. */
	@JsonProperty("access_token")
	@Column(length = 5000)
	private String accessToken;
	
	/** The refresh token. */
	@JsonProperty("refresh_token")
	@Column(length = 5000)
	private String refreshToken;
	
	/** The id token. */
	@JsonProperty("id_token")
	@Column(length = 5000)
	private String idToken;
	
	/** The error. */
	private String error;
	
	/** The error description. */
	@JsonProperty("error_description")
	private String errorDescription;
	
	/** The error codes. */
	@JsonProperty("error_codes")
	private int[] errorCodes;
	
	/** The expiration time. */
	private Date expirationTime;

	/** The id. */
	@Id
	@GeneratedValue
	private int id;
	
	/** The owner. */
	@OneToOne
	private OutlookAccount owner;
	
	/**
	 * Sets the owner.
	 *
	 * @param owner the new owner
	 */
	public void setOwner(OutlookAccount owner) {
		this.owner = owner;
	}
	
	/**
	 * Gets the owner.
	 *
	 * @return the owner
	 */
	public OutlookAccount getOwner() {
		return owner;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the token type.
	 *
	 * @return the token type
	 */
	public String getTokenType() {
		return tokenType;
	}

	/**
	 * Sets the token type.
	 *
	 * @param tokenType the new token type
	 */
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	/**
	 * Gets the scope.
	 *
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * Sets the scope.
	 *
	 * @param scope the new scope
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 * Gets the expires in.
	 *
	 * @return the expires in
	 */
	public int getExpiresIn() {
		return expiresIn;
	}

	/**
	 * Sets the expires in.
	 *
	 * @param expiresIn the new expires in
	 */
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
		Calendar now = Calendar.getInstance();
		now.add(Calendar.SECOND, expiresIn);
		this.expirationTime = now.getTime();
	}

	/**
	 * Gets the access token.
	 *
	 * @return the access token
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * Sets the access token.
	 *
	 * @param accessToken the new access token
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * Gets the refresh token.
	 *
	 * @return the refresh token
	 */
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * Sets the refresh token.
	 *
	 * @param refreshToken the new refresh token
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	/**
	 * Gets the id token.
	 *
	 * @return the id token
	 */
	public String getIdToken() {
		return idToken;
	}

	/**
	 * Sets the id token.
	 *
	 * @param idToken the new id token
	 */
	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}

	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * Sets the error.
	 *
	 * @param error the new error
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * Gets the error description.
	 *
	 * @return the error description
	 */
	public String getErrorDescription() {
		return errorDescription;
	}

	/**
	 * Sets the error description.
	 *
	 * @param errorDescription the new error description
	 */
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	/**
	 * Gets the error codes.
	 *
	 * @return the error codes
	 */
	public int[] getErrorCodes() {
		return errorCodes;
	}

	/**
	 * Sets the error codes.
	 *
	 * @param errorCodes the new error codes
	 */
	public void setErrorCodes(int[] errorCodes) {
		this.errorCodes = errorCodes;
	}

	/**
	 * Gets the expiration time.
	 *
	 * @return the expiration time
	 */
	public Date getExpirationTime() {
		return expirationTime;
	}
}