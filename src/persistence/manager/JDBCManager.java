package persistence.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCManager {

    private static JDBCManager INSTANCE = null;
    private static Connection connection;
    
    private JDBCManager(){    	
    	
    }
    
    public static JDBCManager getInstance(){  
    	if(INSTANCE == null) {
    		INSTANCE = new JDBCManager();
    	}
    	return INSTANCE;
    }	    
    
    public Connection openConection() throws ClassNotFoundException, SQLException, IOException {
    	
    	CfgManager cfgManager = CfgManager.getInstance();
    	
    	
    	Properties properties = cfgManager.getCfg();
    	
		//Class.forName("com.mysql.jdbc.Driver");		
	   	
		String driverPath = properties.getProperty("driverName");
		Class.forName(driverPath);
		
		String url = properties.getProperty("url");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		connection =  DriverManager.getConnection(url, user, password);
		
		return connection;
	}
	
    public void closeConnection() throws SQLException {
			connection.close();
	}
}
