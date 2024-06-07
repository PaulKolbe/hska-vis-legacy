package hska.iwi.eShopMaster.model.businessLogic.manager.impl;


import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import hska.iwi.eShopMaster.client.CategoryClient;
import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryManagerImpl implements CategoryManager {

	private final CategoryClient categoryClient = Feign.builder()
			.encoder(new GsonEncoder())
			.decoder(new GsonDecoder())
			.target(CategoryClient.class, "http://" + System.getenv("CATEGORY_SERVICE_HOST") + ":"
					+ System.getenv("CATEGORY_SERVICE_PORT") + "/categories");

	public CategoryManagerImpl() {
	}

	public List<Category> getCategories() {
		return categoryClient.getCategories();
	}

	public Category getCategory(int id) {
		return categoryClient.getCategory((long) id);
	}

	public Category getCategoryByName(String name) {
		return null;
	}

	public void addCategory(String name) {
		Map<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("name", name);
		categoryClient.createCategory(requestBody);
	}

	public void delCategory(Category cat) {
		categoryClient.deleteCategory((long) cat.getId());
	}

	public void delCategoryById(int id) {
		categoryClient.deleteCategory((long) id);
	}
}
