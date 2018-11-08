package fr.ynov.dap.dap;


/**
 * The Class Config.
 */
public class Config {
	
	/** The client secret dir. */
	private String clientSecretDir;
	
	/** The application name. */
	private String applicationName;
	
	/** The tokens directory path. */
	private String tokensDirectoryPath;
	
	/** The credentials file path. */
	private String credentialsFilePath;
	
	private String redirectUrl;
	
	
	
	/**
	 * Instantiates a new config.
	 */
	/**
	 * 
	 */
	public Config() {
		this.clientSecretDir = "google/client";
		this.applicationName = "Hoc Dap";
		this.tokensDirectoryPath = "tokens";
		this.credentialsFilePath = "C:/google/credentials.json";
		this.redirectUrl = "/oAuth2Callback";
	}

	public void setClientSecretDir(String clientSecretDir) {
		this.clientSecretDir = clientSecretDir;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public void setTokensDirectoryPath(String tokensDirectoryPath) {
		this.tokensDirectoryPath = tokensDirectoryPath;
	}

	public void setCredentialsFilePath(String credentialsFilePath) {
		this.credentialsFilePath = credentialsFilePath;
	}

	/**
	 * Gets the client secret dir.
	 *
	 * @return the client secret dir
	 */
	public String getClientSecretDir() {
		return clientSecretDir;
	}

	/**
	 * Gets the application name.
	 *
	 * @return the application name
	 */
	public String getApplicationName() {
		return applicationName;
	}
	
	/**
	 * Gets the tokens directory path.
	 *
	 * @return the tokens directory path
	 */
	public String getTokensDirectoryPath() {
		return tokensDirectoryPath;
	}

	/**
	 * Gets the credentials file path.
	 *
	 * @return the credentials file path
	 */
	public String getCredentialsFilePath() {
		return credentialsFilePath;
	}
	
	/**
	 * Gets the o auth 2 callback url.
	 *
	 * @return the o auth 2 callback url
	 */
	public String getRedirectUrl() { 
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	
}
