package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import hska.iwi.eShopMaster.client.ProductClient;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.database.dataobjects.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductManagerImpl implements ProductManager {

	private final ProductClient productClient = Feign.builder()
			.encoder(new GsonEncoder())
			.decoder(new GsonDecoder())
			.target(ProductClient.class, "http://" + System.getenv("PRODUCT_SERVICE_HOST") + ":"
					+ System.getenv("PRODUCT_SERVICE_PORT") + "/products");

	public ProductManagerImpl() {
	}

	public List<Product> getProducts() {
		return productClient.getProducts();
	}

	public List<Product> getProductsForSearchValues(String searchDescription,
													Double searchMinPrice, Double searchMaxPrice) {

		Map<String, Object> queryMap = new HashMap<String, Object>();
		if (searchDescription != null) {
			queryMap.put("searchString", searchDescription);
		}

		if (searchMinPrice != null) {
			queryMap.put("minPrice", searchMinPrice);
		}

		if (searchMaxPrice != null) {
			queryMap.put("maxPrice", searchMaxPrice);
		}

		return productClient.search(queryMap);
	}

	public Product getProductById(int id) {
		return productClient.getProduct((long) id);
	}

	public Product getProductByName(String name) {
		return null;
	}

	public int addProduct(String name, double price, int categoryId, String categoryName, String details) {
		Map<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("name", name);
		requestBody.put("price", price);
		requestBody.put("categoryId", categoryId);
		requestBody.put("categoryName", categoryName);
		requestBody.put("details", details);
		Product product = productClient.createProduct(requestBody);

		return product.getId();
	}

	public void deleteProductById(int id) {
		productClient.deleteProduct((long) id);
	}

	public boolean deleteProductsByCategoryId(int categoryId) {
		return false;
	}

}