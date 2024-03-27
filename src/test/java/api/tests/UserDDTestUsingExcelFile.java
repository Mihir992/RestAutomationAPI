package api.tests;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import api.payload.User;
import api.utils.ExcelUtils;
import io.restassured.response.Response;

public class UserDDTestUsingExcelFile {
	
	User userPayload;
	public Logger logger; 
  
	@Test(dataProvider = "AllUserData", dataProviderClass = ExcelUtils.class)
    public void testGetUser(String uname, String password) {
		logger= LogManager.getLogger(this.getClass());
		logger.debug("debugging.....");
		logger.info("********** Reading User Info ***************");
		logger.info("Username: " + uname);
        logger.info("Password: " + password);
        logger.info("********** User info is displayed ***************");
    }

	@Test
    public void testPostUser() {
		logger= LogManager.getLogger(this.getClass());
		logger.debug("debugging.....");
		logger.info("********** Writing User Info ***************");
		
	    // Hardcoded values for new username and password
	    String newUserName = "test5@gmail.com";
	    String newPassword = "test5@123";

	    // Call the data provider method with the new username and password
	    ExcelUtils.writeDataToExcel(newUserName, newPassword);
	    
	    logger.info("Username: " + newUserName);
	    logger.info("Password: " + newPassword);
	    logger.info("********** User info is created ***************");
    }

}
