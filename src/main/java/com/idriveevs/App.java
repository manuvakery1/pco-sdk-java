package com.idriveevs;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.idriveevs.base.EvsAPIResource.EvsEncAlogrithm;
import com.idriveevs.client.IdriveEvsClient;
import com.idriveevs.model.EvsCompleteMultipartUploadRequest;
import com.idriveevs.model.EvsCompleteMultipartUploadResponse;
import com.idriveevs.model.EvsInitiateMultipartUploadRequest;
import com.idriveevs.model.EvsInitiateMultipartUploadResponse;
import com.idriveevs.model.EvsUploadPartRequest;
import com.idriveevs.model.EvsUploadPartResponse;
import com.idriveevs.model.EvsUserCredentials;
import com.idriveevs.transfer.EvsTransferManager;
import com.idriveevs.transfer.EvsUploadResponse;

/**
 * Hello world!
 *
 */
public class App {

	private final IdriveEvsClient evsClient;
	
	/**
	 * 
	 */
	public App(EvsUserCredentials userCredentials) {
		this.evsClient = new IdriveEvsClient(userCredentials);
	}
	
	public static void main(String[] args) {
        double[] numArray = { 9, 8, 7, 10, 6, 8, 7, 8, 9, 7 };
        double SD = calculateSD(numArray);

        System.out.format("Standard Deviation = %.6f", SD);
    }

    public static double calculateSD(double numArray[])
    {
        double sum = 0.0, standardDeviation = 0.0;

        for(double num : numArray) {
            sum += num;
        }

        double mean = sum/10;

        for(double num: numArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation/10);
    }
	
	public static void main1(String[] args) throws IOException, NoSuchAlgorithmException {
//		EvsUserCredentials userCredentials = new EvsUserCredentials();
//		userCredentials.setUserId("imbadmin@test.com");
//		userCredentials.setPassword("123456");
//		EvsTransferManager manager = new EvsTransferManager(userCredentials);
//		final File file1 = new File("/home/manu/Desktop/te.mp4");
//		System.out.println("file exists===>"+file1.exists());
//		EvsUploadResponse uploadParallel = manager.uploadFile(file1, "home");
//		while (!uploadParallel.isDone() && !uploadParallel.isCancelled()) {
//			System.out.println("upload percentage =>"+uploadParallel.getProgress().getProgressInPercentage());
//			System.out.println("uplaoded bytes ==>"+uploadParallel.getProgress().getTransferedBytes());
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		System.out.println("3"+uploadParallel.getProgress().getTransferedBytes());
//		System.out.println("4"+uploadParallel.getResponse());
//		manager.shutDown();
//		System.out.println("shutting down the manager");
		
		System.out.println(fibo(25));
	}
	
	
	static boolean  hasPairWithSum(int[] numbers, int sum){
		int low = 0;
		int high = numbers.length -1;
		while (low < high) {
			int _sum = numbers[low] + numbers[high];
			if(_sum == sum){
				return true;
			}
			if(sum > _sum){
				low++;
			}else{
				high--;
			}
		}
		return false;
	}
	
	
	static List<Map.Entry<Integer, Integer>>  hasPairWithSum1(int[] numbers, int sum){
		List<Map.Entry<Integer, Integer>> result = new ArrayList<Map.Entry<Integer,Integer>>();
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < numbers.length; i++) {
			int comp = sum - numbers[i];
			if(set.contains(numbers[i])){
				Entry<Integer, Integer> arg0 = new AbstractMap.SimpleEntry(comp,numbers[i]);
				result.add(arg0 );
			}
			set.add(comp);
		}
		return result;
	}
	
	
	void test(File file){


		final String encKey = "myenckey07";
		final EvsEncAlogrithm alg = EvsEncAlogrithm.AES;
		final boolean encrypt = true;

		System.out.println("Start Time - " + (new Date()));
		/*
		 * newFixedThreadPool = Executors.newFixedThreadPool(1);
		 * List<Future<EvsUploadPartResponse>> list = new
		 * ArrayList<Future<EvsUploadPartResponse>>();
		 */
		
		
		EvsInitiateMultipartUploadRequest initiateRequest = new EvsInitiateMultipartUploadRequest();
		initiateRequest.setFileCheckSum("fileCheckSum");
		initiateRequest.setFileEvsPath("home/");
		initiateRequest.setFileName(file.getName());
		initiateRequest.setFileSize(9999l);
		initiateRequest.setEncryptionEnabled(encrypt);
		initiateRequest.setEncAlgorithm(alg);
		initiateRequest.setEncKey(encKey);
		EvsInitiateMultipartUploadResponse initiateMultipartFileUpload = evsClient
				.initiateMultipartFileUpload(initiateRequest);
		System.out.println(initiateMultipartFileUpload);
		
//		EvsAbortMultipartUploadRequest abortMultipartUploadRequest = new EvsAbortMultipartUploadRequest();
//		abortMultipartUploadRequest.setUploadId(initiateMultipartFileUpload.getUploadId());
//		EvsAbortMultipartUploadResponse abortMultipartFileUpload = evsClient.abortMultipartFileUpload(abortMultipartUploadRequest);
//		System.out.println(abortMultipartFileUpload);
		long contentLength = file.length();
		System.out.println("File Content Length is - " + contentLength);
		long partSize = 1024 * 1024 * 10;
		long filePosition = 0;

		// InputStream fileInputStream = new FileInputStream(file);
		Long vStartTime = System.currentTimeMillis();
		System.out.println(vStartTime);

		for (int i = 1; filePosition < contentLength; i++) {
			partSize = Math.min(partSize, (contentLength - filePosition));
			final EvsUploadPartRequest objUploadPart = new EvsUploadPartRequest();
			objUploadPart.setFile(file);
			objUploadPart.setPartSize(partSize);
			objUploadPart.setOffsetPosition(filePosition);
			objUploadPart.setPartNo(i);
			objUploadPart.setFileName(file.getName());
			objUploadPart.setUploadId(initiateMultipartFileUpload.getUploadId());
			EvsUploadPartResponse uploadFilePart = null;
			if (encrypt) {
				uploadFilePart = evsClient.uploadFilePart(objUploadPart, encKey, alg);
			} else {
				uploadFilePart = evsClient.uploadFilePart(objUploadPart);
			}
			filePosition += partSize;
		}
		
//		EvsListPartsRequest listPartsRequest = new EvsListPartsRequest();
//		listPartsRequest.setUploadId(initiateMultipartFileUpload.getUploadId());
//		EvsListPartsResponse listParts = evsClient.listParts(listPartsRequest);
//		System.out.println(listParts);

		EvsCompleteMultipartUploadRequest objCompleteReq = new EvsCompleteMultipartUploadRequest();
		objCompleteReq.setUploadId(initiateMultipartFileUpload.getUploadId());
		EvsCompleteMultipartUploadResponse completeMultipartFileUpload = evsClient
				.completeMultipartFileUpload(objCompleteReq);
		System.out.println(completeMultipartFileUpload);

		

		/*
		 * EvsListMultipartUploadsRequest obj = new
		 * EvsListMultipartUploadsRequest(); obj.setUserId("manu");
		 * obj.setPassword("pwd"); EvsListMultipartUploadsResponse
		 * listAllMulitpartUploads = evsClient.listAllMulitpartUploads(obj);
		 * System.out.println(listAllMulitpartUploads);
		 */
		System.out.println("End Time - " + (new Date()));
	
	}
	
	
	public static int fibo(int num)
	{
		System.out.println(num);
	    if (num == 0)
	    {
	        return 0;
	    }
	    else if (num == 1)
	    {
	        return 1;
	    }
	    else
	    {
	        return(fibo(num - 1) + fibo(num - 2));
	    }
	}
}
