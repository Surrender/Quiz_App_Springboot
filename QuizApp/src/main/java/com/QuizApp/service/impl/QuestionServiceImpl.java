package com.QuizApp.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.QuizApp.constant.ApplicationConstant;
import com.QuizApp.dto.AnswerDto;
import com.QuizApp.dto.QuestionDto;
import com.QuizApp.dto.QuestionDto1;
import com.QuizApp.model.Question;
import com.QuizApp.repository.QuestionRepository;
import com.QuizApp.service.QuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private ModelMapper modelMapper = new ModelMapper();
    
    private QuestionDto1 questionDto1 = new QuestionDto1();
    
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public QuestionDto createQuestion(QuestionDto question) {

        if(questionRepository.findByQuestionTitle(question.getQuestionTitle()) != null)
            throw new RuntimeException("Question already exists in Database");
        
        for(AnswerDto answer: question.getAnswers()){
            answer.setQuestion(question);
        }

        Question questionEntity = modelMapper.map(question, Question.class);
        QuestionDto returnValue = modelMapper.map(questionRepository.save(questionEntity), QuestionDto.class);

        return returnValue;
    }

    @Override
    public Set<QuestionDto> getAllQuestions() {
        Set<QuestionDto> returnSet = new HashSet<>();
        for(Question question: questionRepository.findAll()){
            QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);
            returnSet.add(questionDto);
        }
        return returnSet;
    }
    
    @Override
    public QuestionDto getQuestionById(long id) {

        return modelMapper.map(questionRepository.findById(id), QuestionDto.class);
    }
    
    @Override
    @Transactional
    public void publishQuestionById(QuestionDto questionDto){
    	int id = (int)questionDto.getId();
    	questionDto1.setQuesId(id);
    	questionDto1.setTitle(questionDto.getQuestionTitle()); 
    	questionDto1.setChoice(-1);
    	List<AnswerDto> answers = questionDto.getAnswers();
    	questionDto1.setOptionA(answers.get(0).getAnswerTitle());
    	questionDto1.setOptionB(answers.get(1).getAnswerTitle());
    	questionDto1.setOptionC(answers.get(2).getAnswerTitle());
    	questionDto1.setOptionD(answers.get(3).getAnswerTitle());
    	
    	System.out.println(questionDto.getCorrectAnswer());
    	if(answers.get(0).getAnswerTitle().equals(questionDto.getCorrectAnswer()))
    	{
    		questionDto1.setAnswer(1);
    		System.out.println("Match with Option A");
    	}
    	else if(answers.get(1).getAnswerTitle().equals(questionDto.getCorrectAnswer()))
    	{
    		questionDto1.setAnswer(2);
    		System.out.println("Match with Option B");
    	}
    	else if(answers.get(2).getAnswerTitle().equals(questionDto.getCorrectAnswer()))
    	{
    		questionDto1.setAnswer(3);
    		System.out.println("Match with Option C");
    	}
    	else if(answers.get(3).getAnswerTitle().equals(questionDto.getCorrectAnswer()))
    	{
    		questionDto1.setAnswer(4);
    		System.out.println("Match with Option D");
    	}
    	else 
    		System.out.println("Mismatch in correct answer and options");  
    	    	
    	raiseEvent(questionDto1);
    }
    
    private void raiseEvent(QuestionDto1 dto){
        try{
        	String jsonStr = objectMapper.writeValueAsString(dto);
        	//System.out.println(jsonStr);
            this.kafkaTemplate.send(ApplicationConstant.TOPIC_NAME1, jsonStr);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
