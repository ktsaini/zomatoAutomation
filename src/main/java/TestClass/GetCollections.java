package TestClass;

import APICalls.Cities;
import APICalls.Collecions;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GetCollections {

        @DataProvider(name = "getCollectionsData")
        public Iterator<Object[]> getCollectionsData() {
            List inputData = new ArrayList();
            List urlList= new ArrayList();
            urlList.add("http://www.zoma.to/c-1/306701");
            urlList.add("http://www.zoma.to/c-1/1");
            urlList.add("http://www.zoma.to/c-1/274852");
            urlList.add("http://www.zoma.to/c-1/3");

            inputData.add(new Object[]{"/api/v2.1/collections", 42,"http://www.zoma.to/c-1", 1,urlList});
            return inputData.iterator();
        }

        @Test(dataProvider = "getCollectionsData")
        public void getCollectionsPositiveCase(String endPointName, int size, String url,int cityId,List expectedList) {
            Collecions collections = new Collecions();
            Response response = collections.callGetCollectionsAPI(endPointName,cityId);
            // another way to assert int actualSize=response.jsonPath().getList("collection").size();
            Assert.assertEquals(response.statusCode(), 200);
            Assert.assertEquals(response.jsonPath().getList("collections").size(),size);
            Assert.assertEquals(response.jsonPath().get("share_url"),url);
            for(int i=0;i<4;i++){
                Assert.assertEquals(response.jsonPath().get("collections["+i+"].collection.share_url"),expectedList.get(i));
            }
        }



        @DataProvider(name = "getNegData")
        public Iterator<Object[]> getNegData(){
            List inputData = new ArrayList();
            inputData.add(new Object[]{"/api/v2.1/collections","http://www.zoma.to/c--1", -1});
           // inputData.add(new Object[]{"/api/v2.1/collections","Bad request", 0});
            //inputData.add(new Object[]{"/api/v2.1/collections","http://www.zoma.to/c--1", "a"});
            return inputData.iterator();

        }

        @Test(dataProvider= "getNegData")
    public void getCollectionsNegCase(String endpoint,String url, Object cityId){
            Collecions collecions =new Collecions();
            Response response = collecions.callGetCollectionsAPI(endpoint,cityId);
            Assert.assertEquals(response.jsonPath().get("share_url"),url);
            Assert.assertNull(response.jsonPath().get("collections"));
            Assert.assertEquals(response.jsonPath().get("message"),"Bad Request");



        }




}
