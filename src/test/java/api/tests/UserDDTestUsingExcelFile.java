package api.tests;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utils.ExcelUtils;
import io.restassured.response.Response;

public class UserDDTestUsingExcelFile {
	
	User userPayload;
	public Logger logger; // for logs

  
	@Test(priority=1, dataProvider="AllUserData", dataProviderClass=ExcelUtils.class )
	public void testGetUser()
	{
		logger.info("********** Reading User Info ***************");
		
		Response response=UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("**********User info  is displayed ***************");
		
	}
}
