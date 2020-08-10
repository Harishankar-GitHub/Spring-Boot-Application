package com.surveyapplication.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.surveyapplication.model.Question;
import com.surveyapplication.model.Survey;
import com.surveyapplication.service.SurveyService;


@RestController
public class SurveyController
{
	@Autowired
	private SurveyService surveyService;
	
	// Get all questions from a survey
	// http://localhost:8080/surveys/Survey1/questions
	@GetMapping("/surveys/{surveyId}/questions")
	public List<Question> retrieveQuestionsForSurvey(@PathVariable String surveyId)
	{
		return surveyService.retrieveQuestions(surveyId);
	}
	
	// Add a question to a survey
	// http://localhost:8080/surveys/Survey1/questions
	@PostMapping("/surveys/{surveyId}/questions")
	public ResponseEntity<Void> addQuestionToSurvey(@PathVariable String surveyId, @RequestBody Question newQuestion)
	{
		Question questionThatWasAdded = surveyService.addQuestion(surveyId, newQuestion);
		
		if (questionThatWasAdded == null)
		{
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(questionThatWasAdded.getId()).toUri();
        // In the above line, we are returning the URI if the request is success.
        // The request which comes in -> http://localhost:8080/surveys/Survey1/questions
        // We append /{id} to it and return.
        // So it becomes -> http://localhost:8080/surveys/Survey1/questions/{id}
        // /{id} is just a placeholder, it could be anything.

        return ResponseEntity.created(location).build();
	}
	
	// Get a question from a survey
	// http://localhost:8080/surveys/Survey1/questions/Question2
	@GetMapping("/surveys/{surveyId}/questions/{questionId}")
	public Question retrieveDetailsForAQuestion(@PathVariable String surveyId, @PathVariable String questionId)
	{
		return surveyService.retrieveQuestion(surveyId, questionId);
	}
	
	// Get all surveys
	// http://localhost:8080/surveys
	@GetMapping("/surveys")
	public List<Survey> getAllSurveys()
	{
		return surveyService.retrieveAllSurveys();
	}
}
