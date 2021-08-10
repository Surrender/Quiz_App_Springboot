package com.QuizAppSubscriber.service;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.QuizAppSubscriber.constant.ApplicationConstant;
import com.QuizAppSubscriber.dto.QuizResponseDto;
import com.QuizAppSubscriber.model.Question;
import com.QuizAppSubscriber.model.QuestionForm;
import com.QuizAppSubscriber.model.Result;
import com.QuizAppSubscriber.repository.QuestionRepo;
import com.QuizAppSubscriber.repository.ResultRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class QuizService {
	
	@Autowired
	Question question;
	@Autowired
	QuestionForm qForm;
	@Autowired
	QuestionRepo qRepo;
	@Autowired
	Result result;
	@Autowired
	ResultRepo rRepo;
	
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private QuizResponseDto responseDto = new QuizResponseDto();
	
	public QuestionForm getQuestions() {
		List<Question> allQues = qRepo.findAll();
		List<Question> qList = new ArrayList<Question>();
		
        System.out.println("Total questions " + allQues.size());
		
		//Random random = new Random();
		for(int i=0; i<allQues.size(); i++) {
			//int rand = random.nextInt(allQues.size());
			qList.add(allQues.get(i));
			//allQues.remove(rand);
		}

        System.out.println("Total quiz questions " + qList.size());
		qForm.setQuestions(qList);
		
		return qForm;
	}
	
	public int getResult(QuestionForm qForm) {
		int correct = 0;
		
		for(Question q: qForm.getQuestions())
			if(q.getAnswer() == q.getChoice())
				correct++;
		
		return correct;
	}
	
	public void saveScore(Result result) {
		Result saveResult = new Result();
		saveResult.setUsername(result.getUsername());
		saveResult.setTotalCorrect(result.getTotalCorrect());
		rRepo.save(saveResult);
	}
	
	public List<Result> getTopScore() {
		List<Result> sList = rRepo.findAll(Sort.by(Sort.Direction.DESC, "totalCorrect"));
		
		return sList;
	}
	

    @Transactional
	public void raiseEvent(QuestionForm qForm, Result result){
        try{
        	for(Question q: qForm.getQuestions())
        	{
        		responseDto.setQuesId(q.getQuesId());
        		responseDto.setUsername(result.getUsername());
        		//responseDto.setTitle(q.getTitle());
        		Question question = qRepo.findById(q.getQuesId())
        				.orElseThrow(() -> new Exception("Question not found with id: " + q.getQuesId()));
        		System.out.println(q.getChoice());
        		if(q.getChoice() == 1)
        		{
        			responseDto.setAnswer(question.getOptionA());
        			System.out.println(question.getOptionA());
        		}
        		else if(q.getChoice() == 2)
        		{
        		responseDto.setAnswer(question.getOptionB());
        			System.out.println(question.getOptionB());
        		}
        		else if(q.getChoice() == 3)
        		{
        			responseDto.setAnswer(question.getOptionC());
        			System.out.println(question.getOptionC());
        		}
        		else
        		{
        			responseDto.setAnswer(question.getOptionD());
        			System.out.println(question.getOptionD());
        		}
	        	
            	String jsonStr = objectMapper.writeValueAsString(responseDto);
	        	//System.out.println(jsonStr);
	            this.kafkaTemplate.send(ApplicationConstant.TOPIC_NAME2, jsonStr);
        		
        	}
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
