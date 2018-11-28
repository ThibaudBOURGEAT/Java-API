package fr.ynov.dap.dap;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

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

	private static final String CONFIG_FILE_PATH = 
			System.getProperty("user.home") + "\\config.properties";

	/**
	 * Instantiates a new config.
	 */
	/**
	 * @throws IOException 
	 * 
	 */
	public Config() throws IOException {
		InputStreamReader configStream = new InputStreamReader(
				new FileInputStream(CONFIG_FILE_PATH),
				Charset.forName("UTF-8"));

		this.clientSecretDir = "google/client";
		this.applicationName = "Hoc Dap";
		this.tokensDirectoryPath = "tokens";
		this.credentialsFilePath = System.getProperty("user.home") + "\\credentials.json";
		this.redirectUrlGoogle = "/oAuth2Callback";
		
		if (null != configStream) {
			Properties configProps = new Properties();
			try {
				configProps.load(configStream);
				this.clientSecretDir = configProps.getProperty("client_secret_dir");
				this.applicationName = configProps.getProperty("application_name");
				this.tokensDirectoryPath = configProps.getProperty("tokens_directory_path");
				this.credentialsFilePath = System.getProperty("user.home") + 
						configProps.getProperty("credentials_file_path");
				this.redirectUrlGoogle = configProps.getProperty("redirect_url");
			} finally {
				configStream.close();
			}
		}
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
		return redirectUrlGoogle;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrlGoogle = redirectUrl;
	}

}
