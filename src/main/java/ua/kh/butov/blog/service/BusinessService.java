package ua.kh.butov.blog.service;

import java.util.List;
import java.util.Map;

import ua.kh.butov.blog.entity.Article;
import ua.kh.butov.blog.entity.Category;
import ua.kh.butov.blog.entity.Comment;
import ua.kh.butov.blog.exception.RedirectToValidUrlException;
import ua.kh.butov.blog.exception.ValidateException;
import ua.kh.butov.blog.form.CommentForm;
import ua.kh.butov.blog.model.Items;

public interface BusinessService {

	Map<Integer, Category> mapCategories();

	Items<Article> listArticles(int offset, int limit);

	Items<Article> listArticlesByCategory(String categoryUrl, int offset, int limit);

	/**
	 * @return null if entity not found
	 */
	Category findCategoryByUrl(String categoryUrl);

	Items<Article> listArticlesBySearchQuery(String searchQuery, int offset, int limit);

	/**
	 * @return null if entity not found by idArticle
	 */
	Article viewArticle(Long idArticle, String requestUrl) throws RedirectToValidUrlException;

	List<Comment> listComments(long idArticle, int offset, int limit);
	
	Comment createComment(CommentForm form) throws ValidateException;
}
