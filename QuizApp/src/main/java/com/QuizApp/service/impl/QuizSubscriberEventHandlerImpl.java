package com.QuizApp.service.impl;

import java.net.InetAddress;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.QuizApp.constant.ApplicationConstant;
import com.QuizApp.dto.QuizResponseDto;
import com.QuizApp.model.QuizResponse;
import com.QuizApp.model.request.QuizResponseRequestModel;
import com.QuizApp.repository.QuizRepository;
import com.QuizApp.service.QuizSubscriberEventHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class QuizSubscriberEventHandlerImpl implements QuizSubscriberEventHandler {
	
	@Autowired
	private QuizRepository quizRepository;
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
    private ModelMapper modelMapper = new ModelMapper();
	
	@Value("${message.processing.time}")
    private long processingTime;

    @KafkaListener(topics = ApplicationConstant.TOPIC_NAME2, groupId = ApplicationConstant.GROUP_ID2)
    public void consumer(String respStr) {
    	try{
	        String hostName = InetAddress.getLocalHost().getHostName();
	        System.out.println(String.format("%s consumed %s", hostName, respStr));
	        
	        QuizResponseRequestModel responseDto = objectMapper.readValue(respStr, QuizResponseRequestModel.class);
	        QuizResponseDto quizResponseDto = modelMapper.map(responseDto, QuizResponseDto.class);
	        //QuizResponse quizResponse = modelMapper.map(quizResponseDto, QuizResponse.class);
	        quizRepository.save(modelMapper.map(quizResponseDto, QuizResponse.class));
	        Thread.sleep(processingTime);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
