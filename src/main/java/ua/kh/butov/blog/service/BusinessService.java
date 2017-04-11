package ua.kh.butov.blog.service;

import java.util.Map;

import ua.kh.butov.blog.entity.Category;

public interface BusinessService {

	Map<Integer, Category> mapCategories();
}
