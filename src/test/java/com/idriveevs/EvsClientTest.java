/**
 *
 */
package com.idriveevs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.codehaus.jackson.map.annotate.JsonValueInstantiator;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.idriveevs.client.IdriveEvsClient;
import com.idriveevs.exception.EvsServerException;
import com.idriveevs.model.EvsAbortMultipartUploadRequest;
import com.idriveevs.model.EvsInitiateMultipartUploadRequest;
import com.idriveevs.model.EvsInitiateMultipartUploadResponse;
import com.idriveevs.model.EvsUserCredentials;

/**
 * @author manu
 * @version 1.0
 * @since 1.0
 */
public class EvsClientTest {

	private static IdriveEvsClient idriveEvsClient;
	
	
	@BeforeClass
	public static void setUpClass() {
		EvsUserCredentials evsUserCredentials = new EvsUserCredentials();
		evsUserCredentials.setUserId("test");
		evsUserCredentials.setPassword("passsword");
		idriveEvsClient = new IdriveEvsClient(evsUserCredentials );
	}

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void testNullPointerException() {
		exception.expect(NullPointerException.class);
		EvsInitiateMultipartUploadResponse initiateMultipartFileUpload = idriveEvsClient
				.initiateMultipartFileUpload(null);
		initiateMultipartFileUpload.getUploadId();
	}
	
	
	@Test
	public void testIllegalArgumentException() {
		exception.expect(IllegalArgumentException.class);
		EvsInitiateMultipartUploadRequest req = new EvsInitiateMultipartUploadRequest();
		EvsInitiateMultipartUploadResponse initiateMultipartFileUpload = idriveEvsClient
				.initiateMultipartFileUpload(req );
		initiateMultipartFileUpload.getUploadId();
	}
	

	
	@Test
	public void testEvsInitiateMultipartWithEmptyFileNameRequest() {
		exception.expect(EvsServerException.class);
		exception.expectMessage("File name cannot be empty");
		EvsInitiateMultipartUploadRequest req = buildEvsInitiateMultipartUploadRequest();
		req.setFileName(null);
		EvsInitiateMultipartUploadResponse initiateMultipartFileUpload = idriveEvsClient
				.initiateMultipartFileUpload(req );
		assertNotNull(initiateMultipartFileUpload);
	}
	
	@Test
	public void testEvsInitiateMultipartWithEmptyFileEvsPathRequest() {
		exception.expect(EvsServerException.class);
		exception.expectMessage("path name cannot be empty");
		EvsInitiateMultipartUploadRequest req = buildEvsInitiateMultipartUploadRequest();
		req.setFileEvsPath(null);
		EvsInitiateMultipartUploadResponse initiateMultipartFileUpload = idriveEvsClient
				.initiateMultipartFileUpload(req );
		assertNotNull(initiateMultipartFileUpload);
	}
	
	@Test
	public void testEvsInitiateMultipartWithEmptyFileSizeRequest() {
		exception.expect(EvsServerException.class);
		exception.expectMessage("file size cannot be empty");
		EvsInitiateMultipartUploadRequest req = buildEvsInitiateMultipartUploadRequest();
		req.setFileSize(null);
		EvsInitiateMultipartUploadResponse initiateMultipartFileUpload = idriveEvsClient
				.initiateMultipartFileUpload(req );
		assertNotNull(initiateMultipartFileUpload);
	}
	
	@Test
	public void testEvsInitiateMultipartRequest() {
		EvsInitiateMultipartUploadRequest req = buildEvsInitiateMultipartUploadRequest();
		EvsInitiateMultipartUploadResponse initiateMultipartFileUpload = idriveEvsClient
				.initiateMultipartFileUpload(req );
		assertNotNull(initiateMultipartFileUpload);
		assertNotNull(initiateMultipartFileUpload.getUploadId());
		assertEquals(initiateMultipartFileUpload.getFileName(), req.getFileName());
	}
	
	
	
	@Test
	public void testAbortMultipartRequestWithEmptyUploadId(){
		exception.expect(EvsServerException.class);
		exception.expectMessage("Upload Id name cannot be empty");
		EvsAbortMultipartUploadRequest request = buildEvsAbortMultipartUploadRequest();
		request.setUploadId(null);
		idriveEvsClient.abortMultipartFileUpload(request );
	}
	
	
	@Test
	public void testAbortMultipartRequest(){
		EvsAbortMultipartUploadRequest request = buildEvsAbortMultipartUploadRequest();
		exception.expect(EvsServerException.class);
		exception.expectMessage("Invalid upload id "+request.getUploadId());
		idriveEvsClient.abortMultipartFileUpload(request );
	}
	
	private EvsInitiateMultipartUploadRequest buildEvsInitiateMultipartUploadRequest(){
		EvsInitiateMultipartUploadRequest req = new EvsInitiateMultipartUploadRequest();
		req.setFileName("test.png");
		req.setFileEvsPath("testpath");
		req.setFileCheckSum("test");
		req.setFileSize(1323l);
		return req;
	}
	
	private EvsAbortMultipartUploadRequest buildEvsAbortMultipartUploadRequest(){
		EvsAbortMultipartUploadRequest req = new EvsAbortMultipartUploadRequest();
		req.setUploadId("test");
		return req;
	}

}
