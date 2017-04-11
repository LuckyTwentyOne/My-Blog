package ua.kh.butov.blog.service;

import java.util.Map;

import ua.kh.butov.blog.entity.Article;
import ua.kh.butov.blog.entity.Category;
import ua.kh.butov.blog.model.Items;

public interface BusinessService {

	Map<Integer, Category> mapCategories();

	Items<Article> listArticles(int offset, int limit);

	Items<Article> listArticlesByCategory(String categoryUrl, int offset, int limit);

	Category findCategoryByUrl(String categoryUrl);
}
