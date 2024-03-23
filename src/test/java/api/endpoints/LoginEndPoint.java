package api.endpoints;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginEndPoint {
	
	public static Response postLogin()
	{
	  String requestBody = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";
        
      Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(requestBody)
			.log().all()
			.when()
			.post(Routes.loginpost_url);
		
		return response;
	}
}
