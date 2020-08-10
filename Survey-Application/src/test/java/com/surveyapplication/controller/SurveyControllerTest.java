package com.surveyapplication.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.surveyapplication.model.Question;
import com.surveyapplication.service.SurveyService;

@RunWith(SpringRunner.class)
// The above annotation is to Initialize and Launch Spring Boot Application
@WebMvcTest(value = SurveyController.class)
// By using the above annotation, we launch the application only with the specified class. Rest of the classes or end points will not be mapped.
@WithMockUser
// The above annotation is added because, we need to disable Spring Security in Unit Test.
// We disable Spring Security here because, in Unit Test, we test a particular functionality. 
// We need not test Spring Security here.
// So, we use @WithMockUser to by pass the Spring Security.
public class SurveyControllerTest
// The name of JUnit Test File is ClassName+Test
// This is the naming convention for Unit Testing.
{
	@Autowired
	private MockMvc mockMvc;
	// MockMvc is provided by @WebMvcTest annotation
	
	@MockBean
	private SurveyService surveyService;
	
	@Test
	public void retrieveDetailsForQuestion() throws Exception
	{
		Question mockQuestion = new Question("Question1",
				"Largest Country in the World", "Russia", Arrays.asList(
						"India", "Russia", "United States", "China"));
		
		Mockito.when(surveyService.retrieveQuestion(Mockito.anyString(),
				Mockito.anyString())).thenReturn(mockQuestion);
		
		// /surveys/Survey1/questions/Question1		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/surveys/Survey1/questions/Question1")
				.accept(MediaType.APPLICATION_JSON);
		
		// mockMvc.perform(requestBuilder)
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		String expected = "{\"id\":\"Question1\",\"description\":\"Largest Country in the World\",\"correctAnswer\":\"Russia\",\"options\":[\"India\",\"Russia\",\"United States\",\"China\"]}";
		
		JSONAssert.assertEquals(expected, mvcResult.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void createSurveyQuestion() throws Exception
	{
		Question mockQuestion = new Question("1", "Smallest Number", "1", Arrays.asList("1", "2", "3", "4"));
		
		String questionJson = "{\"description\":\"Smallest Number\",\"correctAnswer\":\"1\",\"options\":[\"1\",\"2\",\"3\",\"4\"]}";
		
		Mockito.when(surveyService.addQuestion(Mockito.anyString(), Mockito.any(Question.class))).thenReturn(mockQuestion);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/surveys/Survey1/questions")
				.accept(MediaType.APPLICATION_JSON).content(questionJson).contentType(MediaType.APPLICATION_JSON);
		
		// mockMvc.perform(requestBuilder)
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		
		System.out.println("Response : " + response.getStatus());
		
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
		assertEquals("http://localhost/surveys/Survey1/questions/1", response
				.getHeader(HttpHeaders.LOCATION));
	}
}