package com.bulain.bos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;

@Component
public class BosInit {

	@Value("${bce.bos.endpoint}")
	private String endpoint;
	@Value("${bce.bos.accessKey}")
	private String accessKey;
	@Value("${bce.bos.secretKey}")
	private String secretKey;

	@Bean
	public BosClient bosClient() {
		BosClientConfiguration config = new BosClientConfiguration();
		config.setCredentials(new DefaultBceCredentials(accessKey, secretKey));
		config.setEndpoint(endpoint);
		return new BosClient(config);
	}

}
