package APICalls;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Categories {
    public Response callGetCategoriesAPI(String endPoint){
        RestAssured.baseURI ="https://developers.zomato.com";
        RequestSpecification request = RestAssured.given();
        request.header("Accept","application/json");
        request.header("user-key","37e12c494f70f04bff6b665d638afa9b");
        Response responseByAPI = request.get(endPoint);
        System.out.println(responseByAPI.prettyPrint());
        return responseByAPI;




        //Java API Calls RestAssured Libraries
        //Create Request Specification class object
        //Generate request


    }
}
