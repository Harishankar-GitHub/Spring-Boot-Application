package com.surveyapplication.controller;


import com.surveyapplication.Application;
import com.surveyapplication.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
// The above annotation is to create the mocks and inject it to the test context.
@SpringBootTest(classes = Application.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// The above annotation is to define the class which starts the application and assign a Random Port.
// Reason for assigning Random Port -> The tests might run on a different environment.
// If more than 1 applications are tested, some of them might fail due to the port error.
// Hence, configuring Random Port.
public class SurveyControllerIT {
// The name of JUnit Test File is ClassName+IT
// This is the naming convention for Integration Testing.

	@LocalServerPort	// Injects the HTTP port that got allocated at runtime.
	private int port;
	
	private final TestRestTemplate restTemplate = new TestRestTemplate();
    
    HttpHeaders headers = new HttpHeaders();
    
    private String createHttpAuthenticationHeaderValue(String userID, String password) {
    	String auth = userID + ":" + password;
    	byte[] encodedAuth = Base64.encode(auth.getBytes(Charset.forName("US-ASCII")));
    	String headerValue = "Basic " + new String(encodedAuth);
    	return headerValue;
	}

    @BeforeEach
	public void setupJSONAcceptType() {
    	System.out.println("Before every test method");
    	
    	headers.add("Authorization", createHttpAuthenticationHeaderValue("user1", "secret1"));
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }

	private String createUrlWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
	
	@Test
	public void testRetrieveSurveyQuestion() throws Exception {
		System.out.println("Port : " + port);
		String expected = "{\"id\":\"Question1\",\"description\":\"Largest Country in the World\",\"correctAnswer\":\"Russia\",\"options\":[\"India\",\"Russia\",\"United States\",\"China\"]}";
//		String expected = "{id:Question1,description:Largest Country in the World,correctAnswer:Russia,options:[India,Russia,United States,China]}";
		// JSONAssert doesn't expect double quotes.
		// So, we can give id : value instead of "id" : "value"
		// But it throws exception when we give without "".
		
		HttpEntity<String> entity= new HttpEntity<String>("DUMMY_DOESNT_MATTER", headers);
		
		ResponseEntity<String> response = restTemplate.exchange(createUrlWithPort("/surveys/Survey1/questions/Question1"), HttpMethod.GET, entity, String.class);

		System.out.println("Response : " + response.getBody());

		JSONAssert.assertEquals(expected, response.getBody(), false);
		// If we give true in above statement, the response must be exactly equal to expected value.
		// If we give false in above statement, the response must contain the expected value.
	}
	
	@Test
	public void testAddQuestion() {
		Question question1 = new Question("DOESNT MATTER", "Question1", "Russia", Arrays.asList("India", "Russia", "United States", "China"));

		HttpEntity<Question> entity = new HttpEntity<Question>(question1, headers);

		ResponseEntity<String> response = restTemplate.exchange(createUrlWithPort("/surveys/Survey1/questions"), HttpMethod.POST, entity, String.class);

		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
		
		System.out.println("Response : " + actual);
		
		assertTrue(actual.contains("/surveys/Survey1/questions/"));
	}
	
	@Test
    public void retrieveAllSurveyQuestions() throws Exception {
        ResponseEntity<List<Question>> response = restTemplate.exchange(
                createUrlWithPort("/surveys/Survey1/questions/"), HttpMethod.GET,
                new HttpEntity<String>("DUMMY_DOESNT_MATTER", headers),
                new ParameterizedTypeReference<List<Question>>() {});

        Question sampleQuestion = new Question("Question1",
                "Largest Country in the World", "Russia", Arrays.asList(
                        "India", "Russia", "United States", "China"));

        assertTrue(response.getBody().contains(sampleQuestion));
    }
}
