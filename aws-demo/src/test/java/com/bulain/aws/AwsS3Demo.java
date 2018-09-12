package com.bulain.aws;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CopyObjectResult;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;

public class AwsS3Demo {
	private Logger logger = LoggerFactory.getLogger(getClass());

	private AmazonS3 s3;

	@Before
	public void setUp() {
		s3 = AmazonS3ClientBuilder.defaultClient();
	}

	@Test
	public void testS3() {

		// Upload a file to an Amazon S3 bucket.
		String bucketName = null;
		String objectKey = null;
		String filePath = null;
		PutObjectResult putObject = s3.putObject(bucketName, objectKey, new File(filePath));
		logger.debug("putObject: {}", putObject);

		// List objects within an Amazon S3 bucket.
		ListObjectsV2Result listObjects = s3.listObjectsV2(bucketName);
		logger.debug("listObjectsV2: {}", listObjects);
		
		// Get an object within an Amazon S3 bucket.
		S3Object object = s3.getObject(bucketName, objectKey);
		logger.debug("getObject: {}", object);
		
		// Copy an object from one Amazon S3 bucket to another.
		String fromBucket = null;
		String toBucket = null;
		String fromKey = null;
		String toKey = null;
		CopyObjectResult copyObject = s3.copyObject(fromBucket, fromKey, toBucket, toKey);
		logger.debug("copyObject: {}", copyObject);
		
		// Delete an object from an Amazon S3 bucket.
		s3.deleteObject(bucketName, objectKey);
		logger.debug("deleteObject");
		
	}

}
