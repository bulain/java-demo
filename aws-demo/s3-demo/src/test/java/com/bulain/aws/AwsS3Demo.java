package com.bulain.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class AwsS3Demo {
	private Logger logger = LoggerFactory.getLogger(getClass());

	private AmazonS3 s3;

	@BeforeEach
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
		
		// Upload a file to an Amazon S3 bucket (with meta).
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectKey, new File(filePath));
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.addUserMetadata("x-file-name", "abc.jpg");
		putObjectRequest.withMetadata(metadata);
		s3.putObject(putObjectRequest);

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

		// Delete multiple objects from an Amazon S3 bucket.
		String[] objectKeys = new String[]{};
		DeleteObjectsRequest dor = new DeleteObjectsRequest(bucketName).withKeys(objectKeys);
		DeleteObjectsResult deleteObjects = s3.deleteObjects(dor);
		logger.debug("deleteObjects: {]", deleteObjects);

	}

}
