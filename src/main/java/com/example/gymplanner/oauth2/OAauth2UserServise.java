package com.example.gymplanner.oauth2;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class OAauth2UserServise extends DefaultOAuth2UserService {
	@Override
	public UserOAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		return new UserOAuth2User(super.loadUser(userRequest));
	}
	

}
