package com.projectname.basetest;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.aventstack.extentreports.Status;
import com.projectname.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import net.lingala.zip4j.core.ZipFile;

public class APIMapper_New extends BaseClass {

	public static File downloadedFilePath;

	public static Logger log = LogManager.getLogger(APIMapper_New.class.getName());
	private String js;
	ObjectMapper mapper = new ObjectMapper();
	public Response response;
	public Response validateResponse;

	protected String pathPostParameter, pathPutParameter, getPathParameter, deletePathParameter;

	/**
	 * @return Falcon Base URI
	 * @description: falconAPIBase() method is a base Falcon API
	 * @author: Naresh
	 */
	public String falconAPIBase() {

		if (BaseClass.environm.equalsIgnoreCase("qa")) {
			RestAssured.baseURI = "http://10.0.5.65:8083";
		}
		return RestAssured.baseURI;
	}

	/**
	 * @return Registration Base URI
	 * @description: Registration method is used to registered a new user in the
	 *               Falcon
	 * @author: Naresh
	 */
	public String UserManagement_Registration_Login_BaseURL() {

		if (BaseClass.environm.equalsIgnoreCase("qa")) {
			RestAssured.baseURI = "http://10.0.5.65:8001/api/index.php";
		}
		return RestAssured.baseURI;
	}

	/**
	 * @return User management Base URI
	 * @description: Login method is used to login an user in the Falcon application
	 * @author: Naresh
	 */
	public String UserManagement_BaseURL() {

		if (BaseClass.environm.equalsIgnoreCase("qa")) {
			RestAssured.baseURI = "http://10.0.5.65:8001/api/index.php";
		}
		return RestAssured.baseURI;
	}

	/**
	 * @return File management Base URI
	 * @description: File method is used to handle file upload and templates in the
	 *               Falcon application
	 * @author: Naresh
	 */
	public String FileManagement_BaseURL() {

		if (BaseClass.environm.equalsIgnoreCase("qa")) {
			RestAssured.baseURI = "http://10.0.5.65:8083";
		}
		return RestAssured.baseURI;
	}

	/**
	 * @return Conversion Base URI
	 * @description: Conversion API handles both Article workflow and Issue workflow
	 *               in the Falcon application
	 * @author: Naresh
	 */
	public String Conversion_BaseURL() {
		if (BaseClass.environm.equalsIgnoreCase("qa")) {
			RestAssured.baseURI = "http://10.0.5.65:8083";
		}
		return RestAssured.baseURI;
	}

	/**
	 * @return Post request
	 * @description: Post method - Without header and With form params with Json schema validation
	 * @param :
	 *            InputForm
	 * @param :
	 *            pathPostParameter
	 * @author: Naresh
	 */
	public Response getPOSTUsingFormDataWithResponseJsonSchemaValidation(Map<String, Object> inputForm,
			String pathPostParameter, String jsonfile) {
		this.pathPostParameter = pathPostParameter;
		validateResponse = (Response) given().params(inputForm).when().post(pathPostParameter).then().assertThat()
				.body(matchesJsonSchemaInClasspath(jsonfile));
		return response;
	}

	/**
	 * @return Post request
	 * @description: Post method - Without header and With form params
	 * @param :
	 *            InputForm
	 * @param :
	 *            pathPostParameter
	 * @author: Naresh
	 */
	public Response getPOSTUsingFormData(Map<String, Object> inputForm, String pathPostParameter) {
		this.pathPostParameter = pathPostParameter;
		response = given().params(inputForm).when().post(pathPostParameter);
		return response;
	}

	/**
	 * @return Post request
	 * @description: Post method - With header, With form params
	 * @param :
	 *            Header
	 * @param :
	 *            Input form
	 * @param :
	 *            pathPostParameter
	 * @author: Naresh
	 */
	public Response getPOSTUsingFormDataWithHeaders(Map<String, Object> header, Map<String, Object> inputForm,
			String pathPostParameter) {
		this.pathPostParameter = pathPostParameter;
		Response response = given().headers(header).params(inputForm).when().post(pathPostParameter);
		return response;
	}

	/**
	 * @return Post request
	 * @description: Post method - With header and With Body string (Text or Json or
	 *               XML or HTML)
	 * @param :
	 *            Header
	 * @param :
	 *            Body
	 * @param :
	 *            pathPostParameter
	 * @author: Naresh
	 */
	public Response getPOSTUsingFormAsBodyWithHeaders(Map<String, Object> header, String bodyData,
			String pathPostParameter) {
		this.pathPostParameter = pathPostParameter;
		Response response = given().headers(header).body(bodyData).when().post(pathPostParameter);
		return response;
	}

	/**
	 * @return Post request
	 * @description: Post method - With Body string (Text or Json or XML or HTML)
	 * @param :
	 *            Body
	 * @param :
	 *            pathPostParameter
	 * @author: Naresh
	 */
	public Response getPOSTUsingFormAsBody(String bodyData, String pathPostParameter) {
		this.pathPostParameter = pathPostParameter;
		Response response = given().body(bodyData).when().post(pathPostParameter);
		return response;
	}

	/**
	 * @return Post request with cookies
	 * @description: Post method - Without header, With form params and Cookie
	 * @param :
	 *            InputForm
	 * @param :
	 *            pathPostParameter
	 * @param :
	 *            cookies
	 * @author: Naresh
	 */
	public Response getPOSTUsingFormDataWithCookie(Cookies cookie, Map<String, Object> inputForm,
			String pathPostParameter) {
		this.pathPostParameter = pathPostParameter;
		response = given().cookies(cookie).params(inputForm).when().post(pathPostParameter);
		return response;
	}

	/**
	 * @return Post request
	 * @description: Post method - With header, With form params and Cookie
	 * @param :
	 *            Header
	 * @param :
	 *            Input form
	 * @param :
	 *            pathPostParameter
	 * @param :
	 *            cookies
	 * @author: Naresh
	 */
	public Response getPOSTUsingFormDataWithHeadersWithCookie(Cookies cookie, Map<String, Object> header,
			Map<String, Object> inputForm, String pathPostParameter) {
		this.pathPostParameter = pathPostParameter;
		Response response = given().cookies(cookie).headers(header).params(inputForm).when().post(pathPostParameter);
		return response;
	}

	/**
	 * @return Post request
	 * @description: Post method - With header, With Body string (Text or Json or
	 *               XML or HTML) and Cookie
	 * @param :
	 *            Header
	 * @param :
	 *            Body
	 * @param :
	 *            pathPostParameter
	 * @param :
	 *            cookies
	 * @author: Naresh
	 */
	public Response getPOSTUsingFormAsBodyWithHeadersWithCookie(Cookies cookie, Map<String, Object> header,
			String bodyData, String pathPostParameter) {
		this.pathPostParameter = pathPostParameter;
		Response response = given().cookies(cookie).headers(header).body(bodyData).when().post(pathPostParameter);
		return response;
	}

	/**
	 * @return Post request
	 * @description: Post method - With Body string (Text or Json or XML or HTML)
	 *               and Cookie
	 * @param :
	 *            Body
	 * @param :
	 *            pathPostParameter
	 * @param :
	 *            cookies
	 * @author: Naresh
	 */
	public Response getPOSTUsingFormAsBodyWithCookie(Cookies cookie, String bodyData, String pathPostParameter) {
		this.pathPostParameter = pathPostParameter;
		Response response = given().cookies(cookie).body(bodyData).when().post(pathPostParameter);
		return response;
	}

	/**
	 * @return Post request
	 * @description: Post method - used to perform file upload
	 * @param :
	 *            header
	 * @param :
	 *            input form
	 * @param :
	 *            pathPostParameter
	 * @param :
	 *            keyValueForFileUpload
	 * @param :
	 *            fileUpload
	 * @author: Naresh
	 */
	public Response getPOSTUsingFormDataWithHeadersUsingUploadFile(Map<String, Object> header,
			Map<String, Object> inputForm, String pathPostParameter, String keyValueForFileUpload, String fileUpload) {
		this.pathPostParameter = pathPostParameter;
		Response response = given().headers(header).multiPart(keyValueForFileUpload, new File(fileUpload))
				.params(inputForm).when().post(pathPostParameter);
		System.out.println("FIle upload resp is " + response.asString());
		return response;
	}

	/**
	 * @return Post request
	 * @description: Post method - used to perform file upload using cookie
	 * @param :
	 *            cookie
	 * @param :
	 *            header
	 * @param :
	 *            input form
	 * @param :
	 *            pathPostParameter
	 * @param :
	 *            keyValueForFileUpload
	 * @param :
	 *            fileUpload
	 * @author: Naresh
	 */
	public Response getPOSTUsingFormDataWithHeadersUsingUploadFileCookie(Cookies cookie, Map<String, Object> header,
			Map<String, Object> inputForm, String pathPostParameter, String keyValueForFileUpload, String fileUpload) {
		this.pathPostParameter = pathPostParameter;
		Response response = given().cookies(cookie).headers(header)
				.multiPart(keyValueForFileUpload, new File(fileUpload)).params(inputForm).when()
				.post(pathPostParameter);
		System.out.println("FIle upload resp with cookie " + response.asString());
		return response;
	}

	// With form header along with "header as file" - Post Method
	public Response getPOSTUsingFormDataWithHeadersUsingFileUpload(Map<String, Object> header,
			Map<String, Object> inputForm, String pathPostParameter, String fileUpload) {
		this.pathPostParameter = pathPostParameter;
		Response resp = given().headers(header).multiPart("files", new File(fileUpload)).formParam("files", fileUpload)
				.params(inputForm).when().post("/v1/files");
		System.out.println("FIle upload resp is " + resp.asString());
		return resp;
	}

	/**
	 * @return Get request
	 * @description: Get method - used to perform retrieve data
	 * @param :
	 *            header
	 * @param :
	 *            getPathParameters
	 * @author: Naresh
	 */
	public Response getGet(Map<String, Object> header, String getPathParameter) {
		this.getPathParameter = getPathParameter;
		Response response = given().headers(header).when().get(getPathParameter);
		return response;
	}

	/**
	 * @return Get request using cookie
	 * @description: Get method - used to perform retrieve data using cookie
	 * @param :
	 *            header
	 * @param :
	 *            cookie
	 * @param :
	 *            getPathParameters
	 * @author: Naresh
	 */
	public Response getGetWithCookie(Cookies cookie, Map<String, Object> header, String getPathParameter) {
		this.getPathParameter = getPathParameter;
		Response response = given().cookies(cookie).headers(header).when().get(getPathParameter);
		return response;
	}

	/**
	 * @return Get request using cookie
	 * @description: Get method - used to perform retrieve data using query
	 *               parameter and cookie with header
	 * @param :
	 *            header
	 * @param :
	 *            cookie
	 * @param :
	 *            query
	 * @param :
	 *            getPathParameters
	 * @author: Naresh
	 */
	public Response getGetWithCookie(Cookies cookie, Map<String, Object> header, Map<String, Object> query,
			String getPathParameter) {
		this.getPathParameter = getPathParameter;
		Response response = given().cookies(cookie).headers(header).queryParams(query).when().get(getPathParameter);
		return response;
	}

	/**
	 * @return Get request using cookie
	 * @description: Get method - used to perform retrieve data using query
	 *               parameter a cookie without header
	 * @param :
	 *            cookie
	 * @param :
	 *            query
	 * @param :
	 *            getPathParameters
	 * @author: Naresh
	 */
	public Response getGetWithCookieNoHeader(Cookies cookie, Map<String, Object> query, String getPathParameter) {
		this.getPathParameter = getPathParameter;
		Response response = given().cookies(cookie).queryParams(query).when().get(getPathParameter);
		return response;
	}

	/**
	 * @return Get request using cookie
	 * @description: Get method - used to perform retrieve data using query
	 *               parameter without header and query parameter
	 * @param :
	 *            cookie
	 * @param :
	 *            query
	 * @param :
	 *            getPathParameters
	 * @author: Naresh
	 */
	public Response getGetNoHeader(Map<String, Object> query, String getPathParameter) {
		this.getPathParameter = getPathParameter;
		Response response = given().queryParams(query).when().get(getPathParameter);
		return response;
	}

	/**
	 * @return Get request
	 * @description: Get method - used to perform retrieve data using query
	 *               parameter and header
	 * @param :
	 *            cookie
	 * @param :
	 *            query
	 * @param :
	 *            getPathParameters
	 * @author: Naresh
	 */
	public Response getGetWithHeader(Map<String, Object> header, Map<String, Object> query, String getPathParameter) {
		this.getPathParameter = getPathParameter;
		Response response = given().headers(header).queryParams(query).when().get(getPathParameter);
		return response;
	}

	/**
	 * @return Put request
	 * @description: Put method - Without header and With form params
	 * @param :
	 *            InputForm
	 * @param :
	 *            pathPutParameter
	 * @author: Naresh
	 */
	public Response getPUTUsingFormData(Map<String, Object> inputForm, String pathPutParameter) {
		this.pathPutParameter = pathPutParameter;
		response = given().params(inputForm).when().put(pathPutParameter);
		return response;
	}

	/**
	 * @return Put request
	 * @description: Put method - With header, With form params
	 * @param :
	 *            Header
	 * @param :
	 *            Input form
	 * @param :
	 *            pathPutParameter
	 * @author: Naresh
	 */
	public Response getPUTUsingFormDataWithHeaders(Map<String, Object> header, Map<String, Object> inputForm,
			String pathPutParameter) {
		this.pathPutParameter = pathPutParameter;
		Response response = given().headers(header).params(inputForm).when().put(pathPutParameter);
		return response;
	}

	/**
	 * @return Put request
	 * @description: Put method - With header and With Body string (Text or Json or
	 *               XML or HTML)
	 * @param :
	 *            Header
	 * @param :
	 *            Body
	 * @param :
	 *            pathPutParameter
	 * @author: Naresh
	 */
	public Response getPUTUsingFormAsBodyWithHeaders(Map<String, Object> header, String bodyData,
			String pathPutParameter) {
		this.pathPutParameter = pathPutParameter;
		Response response = given().headers(header).body(bodyData).when().put(pathPutParameter);
		return response;
	}

	/**
	 * @return Put request
	 * @description: Put method - With Body string (Text or Json or XML or HTML)
	 * @param :
	 *            Body
	 * @param :
	 *            pathPutParameter
	 * @author: Naresh
	 */
	public Response getPUTUsingFormAsBody(String bodyData, String pathPutParameter) {
		this.pathPutParameter = pathPutParameter;
		Response response = given().body(bodyData).when().put(pathPutParameter);
		return response;
	}

	/**
	 * @return Put request with cookies
	 * @description: Put method - Without header, With form params and Cookie
	 * @param :
	 *            InputForm
	 * @param :
	 *            pathPutParameter
	 * @param :
	 *            cookies
	 * @author: Naresh
	 */
	public Response getPUTUsingFormDataWithCookie(Cookies cookie, Map<String, Object> inputForm,
			String pathPutParameter) {
		this.pathPutParameter = pathPutParameter;
		response = given().cookies(cookie).params(inputForm).when().put(pathPutParameter);
		return response;
	}

	/**
	 * @return Put request
	 * @description: Put method - With header, With form params and Cookie
	 * @param :
	 *            Header
	 * @param :
	 *            Input form
	 * @param :
	 *            pathPutParameter
	 * @param :
	 *            cookies
	 * @author: Naresh
	 */
	public Response getPUTUsingFormDataWithHeadersWithCookie(Cookies cookie, Map<String, Object> header,
			Map<String, Object> inputForm, String pathPutParameter) {
		this.pathPutParameter = pathPutParameter;
		Response response = given().cookies(cookie).headers(header).params(inputForm).when().put(pathPutParameter);
		return response;
	}

	/**
	 * @return Put request
	 * @description: Put method - With header, With Body string (Text or Json or XML
	 *               or HTML) and Cookie
	 * @param :
	 *            Header
	 * @param :
	 *            Body
	 * @param :
	 *            pathPutParameter
	 * @param :
	 *            cookies
	 * @author: Naresh
	 */
	public Response getPUTUsingFormAsBodyWithHeadersWithCookie(Cookies cookie, Map<String, Object> header,
			String bodyData, String pathPutParameter) {
		this.pathPutParameter = pathPutParameter;
		Response response = given().cookies(cookie).headers(header).body(bodyData).when().put(pathPutParameter);
		return response;
	}

	/**
	 * @return Put request
	 * @description: Put method - With Body string (Text or Json or XML or HTML) and
	 *               Cookie
	 * @param :
	 *            Body
	 * @param :
	 *            pathPutParameter
	 * @param :
	 *            cookies
	 * @author: Naresh
	 */
	public Response getPUTUsingFormAsBodyWithCookie(Cookies cookie, String bodyData, String pathPutParameter) {
		this.pathPutParameter = pathPutParameter;
		Response response = given().cookies(cookie).body(bodyData).when().put(pathPutParameter);
		return response;
	}

	/**
	 * @return Put request
	 * @description: Put method - used to perform file upload
	 * @param :
	 *            header
	 * @param :
	 *            input form
	 * @param :
	 *            pathPutParameter
	 * @param :
	 *            keyValueForFileUpload
	 * @param :
	 *            fileUpload
	 * @author: Naresh
	 */
	public Response getPUTUsingFormDataWithHeadersUsingUploadFile(Map<String, Object> header,
			Map<String, Object> inputForm, String pathPutParameter, String keyValueForFileUpload, String fileUpload) {
		this.pathPutParameter = pathPutParameter;
		Response response = given().headers(header).multiPart(keyValueForFileUpload, new File(fileUpload))
				.params(inputForm).when().put(pathPutParameter);
		System.out.println("FIle upload resp is " + response.asString());
		return response;
	}

	/**
	 * @return Put request
	 * @description: Put method - used to perform file upload using cookie
	 * @param :
	 *            cookie
	 * @param :
	 *            header
	 * @param :
	 *            input form
	 * @param :
	 *            pathPutParameter
	 * @param :
	 *            keyValueForFileUpload
	 * @param :
	 *            fileUpload
	 * @author: Naresh
	 */
	public Response getPUTUsingFormDataWithHeadersUsingUploadFileCookie(Cookies cookie, Map<String, Object> header,
			Map<String, Object> inputForm, String pathPutParameter, String keyValueForFileUpload, String fileUpload) {
		this.pathPutParameter = pathPutParameter;
		Response response = given().cookies(cookie).headers(header)
				.multiPart(keyValueForFileUpload, new File(fileUpload)).params(inputForm).when().put(pathPutParameter);
		System.out.println("FIle upload resp with cookie " + response.asString());
		return response;
	}

	/**
	 * @return Delete request
	 * @description: Delete method - used to perform retrieve data
	 * @param :
	 *            header
	 * @param :
	 *            deletePathParameters
	 * @author: Naresh
	 */
	public Response deleteDelete(Map<String, Object> header, String deletePathParameter) {
		this.deletePathParameter = deletePathParameter;
		Response response = given().headers(header).when().delete(deletePathParameter);
		return response;
	}

	/**
	 * @return Delete request using cookie
	 * @description: Delete method - used to perform retrieve data using cookie
	 * @param :
	 *            header
	 * @param :
	 *            cookie
	 * @param :
	 *            deletePathParameters
	 * @author: Naresh
	 */
	public Response deleteDeleteWithCookie(Cookies cookie, Map<String, Object> header, String deletePathParameter) {
		this.deletePathParameter = deletePathParameter;
		Response response = given().cookies(cookie).headers(header).when().delete(deletePathParameter);
		return response;
	}

	/**
	 * @return Delete request using cookie
	 * @description: Delete method - used to perform retrieve data using query
	 *               parameter and cookie with header
	 * @param :
	 *            header
	 * @param :
	 *            cookie
	 * @param :
	 *            query
	 * @param :
	 *            deletePathParameters
	 * @author: Naresh
	 */
	public Response deleteDeleteWithCookie(Cookies cookie, Map<String, Object> header, Map<String, Object> query,
			String deletePathParameter) {
		this.deletePathParameter = deletePathParameter;
		Response response = given().cookies(cookie).headers(header).queryParams(query).when()
				.delete(deletePathParameter);
		return response;
	}

	/**
	 * @return Delete request using cookie
	 * @description: Delete method - used to perform retrieve data using query
	 *               parameter a cookie without header
	 * @param :
	 *            cookie
	 * @param :
	 *            query
	 * @param :
	 *            deletePathParameters
	 * @author: Naresh
	 */
	public Response deleteDeleteWithCookieNoHeader(Cookies cookie, Map<String, Object> query,
			String deletePathParameter) {
		this.deletePathParameter = deletePathParameter;
		Response response = given().cookies(cookie).queryParams(query).when().delete(deletePathParameter);
		return response;
	}

	/**
	 * @return Delete request using cookie
	 * @description: Delete method - used to perform retrieve data using query
	 *               parameter without header and query parameter
	 * @param :
	 *            cookie
	 * @param :
	 *            query
	 * @param :
	 *            deletePathParameters
	 * @author: Naresh
	 */
	public Response deleteDeleteNoHeader(Map<String, Object> query, String deletePathParameter) {
		this.deletePathParameter = deletePathParameter;
		Response response = given().queryParams(query).when().delete(deletePathParameter);
		return response;
	}

	/**
	 * @return Delete request
	 * @description: Delete method - used to perform retrieve data using query
	 *               parameter and header
	 * @param :
	 *            cookie
	 * @param :
	 *            query
	 * @param :
	 *            deletePathParameters
	 * @author: Naresh
	 */
	public Response deleteDeleteWithHeader(Map<String, Object> header, Map<String, Object> query,
			String deletePathParameter) {
		this.deletePathParameter = deletePathParameter;
		Response response = given().headers(header).queryParams(query).when().delete(deletePathParameter);
		return response;
	}

	// Get Method for download
	public byte[] getFileForDownload(Map<String, String> header, String getPathParameter) {
		this.getPathParameter = getPathParameter;
		byte[] fileSize = given().headers(header).when().get(getPathParameter).asByteArray();
		return fileSize;
	}

	public String formattedJson(String unformattedJson) {
		try {
			JsonParser jsonParser = new JsonFactory().createJsonParser(unformattedJson);

			Object json = mapper.readValue(jsonParser, Object.class);
			js = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return js;
	}

	public Map<String, String> getAllResponseId(String idName, String IdValue) {
		Map<String, String> pushIds = null;
		try {
			pushIds = new HashMap<String, String>();
			pushIds.put(idName, IdValue);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pushIds;
	}

	/**
	 * @return POST request
	 * @description: Post method - used to perform retrieve data using query
	 *               parameter and header
	 * @param :
	 *            cookie
	 * @param :
	 *            query
	 * @param :
	 *            postPathParameters
	 * @author: Chandru
	 */
	public Response getPOSTWithCookieWithHeaderAndQuery(Cookies cookie, Map<String, Object> header,
			Map<String, Object> query, String pathPostParameter) {
		this.pathPostParameter = pathPostParameter;
		Response response = given().cookies(cookie).headers(header).queryParams(query).when().post(pathPostParameter);
		return response;
	}

	/**
	 * @return POST request
	 * @description: Post method - used to perform retrieve data using header and
	 *               path parameter
	 * @param :
	 *            cookie
	 * @param :
	 *            header
	 * @param :
	 *            postPathParameters
	 * @author: Chandru
	 */
	public Response getPOSTWithCookieWithHeaderAndPath(Cookies cookie, Map<String, Object> header,
			String pathPostParameter) {
		this.pathPostParameter = pathPostParameter;
		Response response = given().cookies(cookie).headers(header).when().post(pathPostParameter);
		return response;
	}

	/**
	 * @return Html/pdf file path
	 * @description: Download and save file in local
	 * @author: Chandru
	 * @param Response
	 *            ,destination foldername ,filetype (pdf/html)
	 */
	public static String downloadAndSaveFile(Response downloadAPI, String folderName, String fileType)
			throws IOException {
		String finalPath = "";
		try {
			String userDir = System.getProperty("user.dir");
			downloadedFilePath = new File(userDir + "/DownloadedFiles/" + folderName + "/outpackage.zip");
			System.out.println(downloadedFilePath.getPath());
			FileUtils.copyInputStreamToFile(downloadAPI.asInputStream(), downloadedFilePath);
			finalPath = getFilePath(new File(extractZipFiles(downloadedFilePath.getAbsolutePath())), fileType);
			test.log(Status.PASS, "Downloaded file path is" + finalPath);
			return finalPath;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			test.log(Status.FAIL, "Problem in downloadAndSaveFile " + e.getMessage());
			test.log(Status.FAIL, "check Jobid " + e.getMessage());

			e.printStackTrace();
		}
		return finalPath;

	}

	/**
	 * @return filepath based on filetype
	 * @description: get the specific file path
	 * @author: Chandru
	 * @param filedirectory,filetype
	 */
	public static String getFilePath(File subDirPath, String filetype) throws Exception {
		String getfilePath = null;
		try {

			for (File currentFile : subDirPath.listFiles()) {
				if (currentFile.isFile()) {
					if (FilenameUtils.getExtension(currentFile.getName()).equalsIgnoreCase(filetype)) {
						getfilePath = currentFile.getAbsolutePath();
					}

				} else if (currentFile.isDirectory()) {

					if (currentFile.getName().contains(filetype) || currentFile.getName().equalsIgnoreCase(filetype)) {
						getfilePath = currentFile.getAbsolutePath();
						break;
					} else {
						getFilePath(currentFile.getAbsoluteFile(), filetype);
					}

				}

			}
		} catch (Exception e) {
			test.log(Status.FAIL, "Problem in getFilePath " + e.getMessage());
			e.printStackTrace();
		}
		return getfilePath;

	}

	/**
	 * @return extract and returns the base path
	 * @description: extract the file
	 * @author: Chandru
	 * @param zipfilepath
	 */
	public static String extractZipFiles(String zipPath) throws Exception {
		String path = null;
		File zipFilePath;
		try {
			zipFilePath = new File(zipPath);
			ZipFile zipfile = new ZipFile(zipFilePath.getParent() + "/" + zipFilePath.getName());
			zipfile.extractAll(zipFilePath.getParent() + "\\" + FilenameUtils.removeExtension(zipFilePath.getName()));
			path = zipFilePath.getParent() + "\\" + FilenameUtils.removeExtension(zipFilePath.getName());
			test.log(Status.PASS, "Zip has been extracted" + path);
			zipFilePath.delete();
			test.log(Status.INFO, "old zip is deleted");
			return path;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			test.log(Status.FAIL, "Problem in extractZipFiles " + e.getMessage());
			e.getMessage();
		}
		return path;

	}

}
