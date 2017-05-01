package ua.kh.butov.blog.service;

import ua.kh.butov.blog.model.SocialAccount;

public interface SocialService {

	SocialAccount getSocialAccount(String authToken);
}
