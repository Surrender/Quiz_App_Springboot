package com.QuizAppSubscriber.service.impl;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.QuizAppSubscriber.constant.ApplicationConstant;
import com.QuizAppSubscriber.model.Question;
import com.QuizAppSubscriber.repository.QuestionRepo;
import com.QuizAppSubscriber.service.QuizAppEventHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class QuizAppEventHandlerImpl implements QuizAppEventHandler {
	
	private static final ObjectMapper objectMapper = new ObjectMapper();

    //private ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	private QuestionRepo questionRepository;
	
	@Value("${message.processing.time}")
    private long processingTime;

    @KafkaListener(topics = ApplicationConstant.TOPIC_NAME1, groupId = ApplicationConstant.GROUP_ID1)
    public void consumer(String quesStr) {
    	try{
	        String hostName = InetAddress.getLocalHost().getHostName();
	        System.out.println(String.format("%s consumed %s", hostName, quesStr));

	        Question question = objectMapper.readValue(quesStr, Question.class);
	        questionRepository.save(question);
	        Thread.sleep(processingTime);	        
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
