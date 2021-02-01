package TestClass;

import APICalls.Cities;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GetCities {
    @DataProvider(name = "getCitiesData")
    public Iterator<Object[]> getCitiesData() {
        List inputData = new ArrayList();
        inputData.add(new Object[]{"/api/v2.1/cities", "Mumbai", 3});
        inputData.add(new Object[]{"/api/v2.1/cities", "Kolkata", 2});
        inputData.add(new Object[]{"/api/v2.1/cities", "Delhi NCR", 1});
        return inputData.iterator();
    }

    @Test(dataProvider = "getCitiesData")
    public void getCitiesPositiveCase(String endPointName, String expectedCityName, int cityId) {
        Cities cities = new Cities();
        Response response = cities.callGetCitiesAPI(endPointName, cityId);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().get("location_suggestions[0].id"), cityId);
        Assert.assertEquals(response.jsonPath().get("location_suggestions[0].name"), expectedCityName);
    }

    @DataProvider(name ="getNegCitiesData")
    public  Iterator<Object[]> getNegCitiesData(){
        List inputData = new ArrayList();
        inputData.add(new Object[]{"/api/v2.1/cities","a"});
        inputData.add(new Object[]{"/api/v2.1/cities",-1});
        inputData.add(new Object[]{"/api/v2.1/cities",0.1});
        return inputData.iterator();
    }

    @Test(dataProvider = "getNegCitiesData")
    public void  getCitiesNegativeCase(String endPointName, Object cityId){
        Cities cities=new Cities();
        Response response =cities.callGetCitiesAPI(endPointName,cityId);
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.jsonPath().getList("location_suggestions").size(),0);
    }

}
