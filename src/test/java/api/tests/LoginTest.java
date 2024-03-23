package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.LoginEndPoint;
import io.restassured.response.Response;

public class LoginTest {

	//Login loginuserPayload;
	public Logger logger; // for logs
			
	@Test(priority=1)
	public void testPostLogin()
	{
		//logs
		logger= LogManager.getLogger(this.getClass());
		
		logger.debug("debugging.....");

		logger.info("********** Logged into the system  ***************");
		Response response=LoginEndPoint.postLogin(); 
		System.out.println("Response Body: " + response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("**********User is logged into successfully  ***************");
	}	
}
