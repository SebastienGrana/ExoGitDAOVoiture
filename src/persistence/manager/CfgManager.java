package persistence.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CfgManager implements ICfgManager {

	private static CfgManager INSTANCE = null;
	private static Properties properties;

	public static final String OTHER_CFG_FILE = System.getProperty("OTHER_CFG_FILE");
	public static final String EXTERNAL_CFG_FILE = System.getProperty("EXTERNAL_CFG_FILE");
	
	private CfgManager() throws IOException{    	
		properties = new Properties();
		InputStream inputStream = null;
		//System.out.println(this.getClass().getClassLoader().getResource("files/cfg.ini"));

		
		if(OTHER_CFG_FILE != null) {
			inputStream = this.getClass().getClassLoader().getResourceAsStream(OTHER_CFG_FILE);
			//System.out.println("IN OTHER_CFG_FILE");
			//System.out.println(OTHER_CFG_FILE);

		/*} else if(EXTERNAL_CFG_FILE != null) {
			inputStream = new FileInputStream(EXTERNAL_CFG_FILE);
			System.out.println("IN EXTERNAL_CFG_FILE");
*/
		}else {
			inputStream = this.getClass().getClassLoader().getResourceAsStream("files/cfg.ini");
			System.out.println("IN CFG_FILE");

		}

		properties.load(inputStream);
	}    


	@Override
	public Properties getCfg() {		
		return properties;
	}

	public static CfgManager getInstance() throws IOException{  
		if(INSTANCE == null) {
			INSTANCE = new CfgManager();
		}
		return INSTANCE;
	}	

}
