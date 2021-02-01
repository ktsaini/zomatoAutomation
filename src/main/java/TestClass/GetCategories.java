package TestClass;


import APICalls.Categories;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GetCategories {

//    @Test @BeforeMethod @BeforeClass

//
//
    @DataProvider(name="getCategoriesData")
    public Iterator<Object[]> getCategoriesData(){
        List inputData =new ArrayList();
        inputData.add(new Object[]{"/api/v2.1/categories"});
        inputData.add(new Object[]{"/api/v2.1/categories"});
        return inputData.iterator();
    }

        @Test(dataProvider = "getCategoriesData")
    public void getCategoriesPositiveCase(String endPointName){
        Categories categories=new Categories();
        Response response=categories.callGetCategoriesAPI(endPointName);
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.jsonPath().get("categories[1].categories.name"),"Dine-out");
        Assert.assertEquals(response.jsonPath().get("categories[0].categories.name"),"Delivery");
        Assert.assertEquals(response.jsonPath().getList("categories").size(),13);
    }

}
