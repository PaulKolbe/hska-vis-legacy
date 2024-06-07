package hska.iwi.eShopMaster.client;

import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import hska.iwi.eShopMaster.model.database.dataobjects.Product;

import java.util.List;
import java.util.Map;

public interface ProductClient {

    @RequestLine("GET /search")
    List<Product> search(@QueryMap Map<String, Object> queryMap);

    @RequestLine("GET")
    List<Product> getProducts();

    @RequestLine("GET /{id}")
    Product getProduct(@Param("id") Long id);

    @RequestLine("DELETE /{id}")
    void deleteProduct(@Param("id") Long id);

    @RequestLine("POST")
    @Headers("Content-Type: application/json")
    Product createProduct(Map<String, Object> requestBody);
}
