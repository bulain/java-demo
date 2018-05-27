package com.bulain.bos;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.model.AppendObjectResponse;
import com.baidubce.services.bos.model.ObjectMetadata;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BosApplication.class)
public class BosUpload {

	@Autowired
	private BosClient bosClient;
	@Value("${bce.bos.bucket}")
	private String bucket;

	@Test
	public void testUpload() throws IOException {
		ClassPathResource resource = new ClassPathResource("logback.xml");
		File file = resource.getFile();
		InputStream is = new FileInputStream(file);
		byte[] bytes = IOUtils.toByteArray(new FileInputStream(file));
		String str = IOUtils.toString(new FileInputStream(file));

		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType("application/xml");
		
		// 以文件形式上传Object
		AppendObjectResponse appendObjectFromFileResponse = bosClient.appendObject(bucket, "File", file, metadata);
		// 以数据流形式上传Object
		AppendObjectResponse appendObjectResponseFromInputStream = bosClient.appendObject(bucket, "InputStream", is, metadata);
		// 以二进制串上传Object
		AppendObjectResponse appendObjectResponseFromByte = bosClient.appendObject(bucket, "Bytes", bytes, metadata);
		// 以字符串上传Object
		AppendObjectResponse appendObjectResponseFromString = bosClient.appendObject(bucket, "String", str, metadata);

		assertNotNull(appendObjectFromFileResponse);
		assertNotNull(appendObjectResponseFromInputStream);
		assertNotNull(appendObjectResponseFromByte);
		assertNotNull(appendObjectResponseFromString);
		
	}

}
