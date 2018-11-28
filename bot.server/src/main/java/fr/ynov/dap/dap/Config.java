package fr.ynov.dap.dap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import fr.ynov.dap.dap.google.GoogleAccountService;

/**
 * The Class Config.
 */
@PropertySource("classpath:config.properties")
public class Config {
	
	@Autowired
	private Environment env;
	
	/** The client secret dir. */
	private String clientSecretDir;
	
	/** The application name. */
	private String applicationName;
	
	/** The tokens directory path. */
	private String tokensDirectoryPath;
	
	/** The credentials file path. */
	private String credentialsFilePath;
	
	private String redirectUrlGoogle;
	
	private String appId;
	
	private String appPassword;
	
	private String redirectUrlMicrosoft;
	
	private final Logger LOG = LogManager.getLogger(GoogleAccountService.class);
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
		this.credentialsFilePath = System.getProperty("user.home") + "\\credentials.json";
		this.redirectUrlGoogle = "/oAuth2Callback";
		this.appId = "4e938524-eb98-4082-b295-5383fb8ff52c";
		this.appPassword = "fhCWE12~kuogzTZWH151^%$";
		this.redirectUrlMicrosoft = "http://localhost:8080/authorize";
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
	
	public String getAppId() {
		return appId;
	}

	public String getAppPassword() {
		return appPassword;
	}

	public String getRedirectUrlMicrosoft() {
		return redirectUrlMicrosoft;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public void setAppPassword(String appPassword) {
		this.appPassword = appPassword;
	}

	public void setRedirectUrlMicrosoft(String redirectUrlMicrosoft) {
		this.redirectUrlMicrosoft = redirectUrlMicrosoft;
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
		return redirectUrlGoogle;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrlGoogle = redirectUrl;
	}
	
}
