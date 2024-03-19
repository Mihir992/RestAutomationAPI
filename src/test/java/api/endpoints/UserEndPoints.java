package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

public class UserEndPoints {
	
	public static Response createUser(User payload)
	{
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.queryParam("api_key","special-key")
			.body(payload)
			.log().all()
			//.parser("application/json",Parser.JSON)
			//.param("text/plain", Parser.JSON)
		.when()
			.post(Routes.post_url);
		return response;
	}

	public static Response readUser(String userName)
	{
		Response response=given()
			.pathParam("username", userName)
		.when()
			.get(Routes.get_url);
		
		return response;
	}
	
	public static Response updateUser(String userName,User payload)
	{
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
		.when()
			.put(Routes.update_url);
		
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		Response response=given()
			.pathParam("username", userName)
		.when()
			.delete(Routes.delete_url);
		
		return response;
	}
	
}
