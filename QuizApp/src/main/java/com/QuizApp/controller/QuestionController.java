package com.QuizApp.controller;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.QuizApp.config.SwaggerConfig;
import com.QuizApp.dto.QuestionDto;
import com.QuizApp.model.request.QuestionRequestModel;
import com.QuizApp.model.response.QuestionResponseModel;
import com.QuizApp.service.impl.QuestionServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/questions") // http:localhost:8080/questions
@Tag(name = SwaggerConfig.QUIZ_APP_CONTROLLER)
public class QuestionController {
    
    @Autowired
    QuestionServiceImpl questionService;

    private ModelMapper modelMapper = new ModelMapper();
    
    @CrossOrigin
    @PostMapping(value = "/create")
	@Operation(summary = "Create a quiz question", description = "Create a quiz question")
    public QuestionResponseModel createQuestion(@RequestBody QuestionRequestModel question){
        QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);
        return modelMapper.map(questionService.createQuestion(questionDto), QuestionResponseModel.class);
    }

    @CrossOrigin
    @GetMapping(path = "/get/{id}")
	@Operation(summary = "Get question by id", description = "Get question by id")
    public QuestionResponseModel getQuestionById(@PathVariable long id){    	    	
        return modelMapper.map(questionService.getQuestionById(id), QuestionResponseModel.class);
    }
    
    @CrossOrigin
    @GetMapping(path = "/publish/{id}")
	@Operation(summary = "Publish question by id", description = "Publish question by id")
    public void publishQuestionById(@PathVariable long id){ 
    	questionService.publishQuestionById(questionService.getQuestionById(id));
    }
    
    @CrossOrigin
    @GetMapping(value = "/")
    @Operation(summary = "Get all quiz questions", description = "Get all quiz questions")
    public Set<QuestionResponseModel> getAllQuestions(){
        Set<QuestionResponseModel> returnValue = new HashSet<>();
        for(QuestionDto questionDto: questionService.getAllQuestions()){
            QuestionResponseModel questionResponseModel= modelMapper.map(questionDto, QuestionResponseModel.class);
            returnValue.add(questionResponseModel);
        }
        return returnValue;
    }
    
}
