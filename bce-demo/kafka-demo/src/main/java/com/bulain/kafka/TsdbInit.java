package com.bulain.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.tsdb.TsdbClient;

@Component
public class TsdbInit {

	@Autowired
	private TsdbSetting properties;

	@Bean
	public TsdbClient tsdbClient() {

		// 创建配置
		BceClientConfiguration config = new BceClientConfiguration().withCredentials(
		        new DefaultBceCredentials(properties.getAccessKey(), properties.getSecretKey())).withEndpoint(
		        properties.getEndpoint());

		// 初始化一个TsdbClient
		return new TsdbClient(config);
	}
}
