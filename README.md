# Quiz_App_Springboot
Multiple Choice Question based web quiz application using Spring Boot, Spring Data JPA, MySQL, Thymeleaf and Kafka

The Web based quiz application has two services, QuizApp (Publisher) and QuizAppSubscriber.

The Questions are created and published using the QuizApp (Publisher). The user can access the questions for answering using the QuizAppSubscriber.


QuizApp (Publisher):
Access the QuizApp service using the Swagger UI link: http://localhost:9191/swagger/index.html. QuizApp service app has end points to create, get and publish questions one at a time.

QuizAppSubscriber:
Access the QuizAppSubscriber service using the url: http://localhost:9192/. The Quiz questions can be accessed by entering the user name.


Prerequisties to run the Quiz App services:
1. Install Kafka and create two topics namely  "quiz-topic-pub" and  "quiz-topic-sub"
2. Install MYSQL and create two databases namely "quiz_db" and "quiz_ans_db".


Questions will be stored in "questions" table, Answer choices in "answers" table and user responses in "quiz_response" table in "quiz_db" database.