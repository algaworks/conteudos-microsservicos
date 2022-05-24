package com.algaworks.example.auth.post.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties("aw.user-api")
@Component
public class UserClientProperties {
	private String url;
	private String encodedCredentials;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEncodedCredentials() {
		return encodedCredentials;
	}

	public void setEncodedCredentials(String encodedCredentials) {
		this.encodedCredentials = encodedCredentials;
	}
}
