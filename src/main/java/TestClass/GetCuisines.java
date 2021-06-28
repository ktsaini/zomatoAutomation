package TestClass;

import APICalls.Collecions;
import APICalls.Cuisines;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.*;

public class GetCuisines {


    @DataProvider(name = "getCuisinesData")
    public Iterator<Object[]> getCuisinesData() {
        List inputData = new ArrayList();
        List expectedCityIds = new ArrayList();
        expectedCityIds.add(1035);
        expectedCityIds.add(152);
        expectedCityIds.add(1);

        List expectedCName = new ArrayList();
        expectedCName.add("Afghan");
        expectedCName.add("African");
        expectedCName.add("American");

        // inputData.add(new Object[]{"/api/v2.1/cuisines", 1035, "Afghan", 1});
        // inputData.add(new Object[]{"/api/v2.1/cuisines", 152, "African", 1});
        inputData.add(new Object[]{"/api/v2.1/cuisines", expectedCityIds, expectedCName, 1});

        return inputData.iterator();
    }

    @Test(dataProvider = "getCuisinesData")
    public void getCuisinesPositiveCase(String endPointName, List c_id, List cname, int cityId) {
        Cuisines cuisines = new Cuisines();
        Response response = cuisines.callGetCuisinesAPI(endPointName, cityId);
        //esponse response = cuisines.callGetCuisinesAPI(endPointName,cname;

        Assert.assertEquals(response.statusCode(), 200);
        //Assert.assertEquals(response.jsonPath().get("cuisines[0].cuisine.cuisine_id"), c_id);
        //   Assert.assertEquals(response.jsonPath().get("cuisines[0].cuisine.cuisine_name"), cname);

        for (int i = 0; i < 3; i++) {
            Assert.assertEquals(response.jsonPath().get("cuisines[" + i + "].cuisine.cuisine_id"), c_id.get(i));
            Assert.assertEquals(response.jsonPath().get("cuisines[" + i + "].cuisine.cuisine_name"), cname.get(i));
        }
    }


    public static void main(String[] args) {
        Scanner scanner= new  Scanner(System.in);
        Map<Integer,Integer> myMap=new HashMap<Integer, Integer>();
        int n = scanner.nextInt();
        for(int i=0;i<n;i++){
            Integer temp= scanner.nextInt();
            if(myMap.containsKey(temp)){
                System.out.println(temp +" Is Repeated");
            }
            else
            {
                myMap.put(temp,1);
            }
        }
    }



    //You are given an array with n number of naturpal number where one of number is repeated find the repeated Number
    //2 4 1 3 4
    //Map<Integer,Integer> myMap =new Hashmap<>();
    //for(int i=0;i<n;i++){

    //cin>>temp;
    //if(myMap.containsKey(temp)){}
    //5
    // 4 1 3 2 4
//}
    //}




}



