package utilidade;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * A utility class that reads/saves SMTP settings from/to a properties file.
 * @author www.codejava.net
 *
 */
public class ConfigUtil {
	final private File configFile = new File("smtp.properties"); 
        final private File mailFile = new File("mail.properties"); 
	private Properties configProps;
	private Properties mailProps;
        
	public Properties loadConfigProperties() throws IOException {
		Properties defaultProps = new Properties();
		// sets default properties
		defaultProps.setProperty("mail.smtp.host", "mailserver.banpara.com");
		defaultProps.setProperty("mail.smtp.port", "587");
		defaultProps.setProperty("mail.user", "arrecadacao@banparanet.com.br");
		defaultProps.setProperty("mail.password", "");
		defaultProps.setProperty("mail.smtp.starttls.enable", "false");
		defaultProps.setProperty("mail.smtp.auth", "false");
		
		configProps = new Properties(defaultProps);
		
		// loads properties from file
		if (configFile.exists()) {
			InputStream inputStream = new FileInputStream(configFile);
			configProps.load(inputStream);
			inputStream.close();
		}
		
		return configProps;
	}
	
         public Properties loadMailProperties() throws IOException {
		Properties defaultProps = new Properties();
		// sets default properties
		defaultProps.setProperty("mail.message", "Segue o relatorio de contratos vencidos em anexo.");
		defaultProps.setProperty("mail.addressee", "arrecadacao@banparanet.com.br");
		
		mailProps = new Properties(defaultProps);
		
		// loads properties from file
		if (mailFile.exists()) {
			InputStream inputStream = new FileInputStream(mailFile);
			mailProps.load(inputStream);
			inputStream.close();
		}
		
		return mailProps;
	}
        
	public void saveConfigProperties(String host, String port, String user, String pass) throws IOException {
		configProps.setProperty("mail.smtp.host", host);
		configProps.setProperty("mail.smtp.port", port);
		configProps.setProperty("mail.user", user);
		configProps.setProperty("mail.password", pass);
		configProps.setProperty("mail.smtp.starttls.enable", "false");
		configProps.setProperty("mail.smtp.auth", "false");
		
		OutputStream outputStream = new FileOutputStream(configFile);
		configProps.store(outputStream, "Configurações do SMTP");
		outputStream.close();
	}
        
        public void saveMailProperties(String message, String addressee) throws IOException {
		mailProps.setProperty("mail.message", message);
		mailProps.setProperty("mail.addressee", addressee);
		
		OutputStream outputStream = new FileOutputStream(mailFile);
		mailProps.store(outputStream, "Configurações da Mensagem");
		outputStream.close();
	}	
}