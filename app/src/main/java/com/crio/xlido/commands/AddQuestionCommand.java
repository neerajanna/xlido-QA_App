package com.crio.xlido.commands;

import java.util.List;
import com.crio.xlido.entities.Question;
import com.crio.xlido.services.QuestionService;

public class AddQuestionCommand implements ICommand {

    private final QuestionService questionService;

    public AddQuestionCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

    
    
    @Override
    public void invoke(List<String> tokens) {
        try {
        String content = tokens.get(1);              // content
        long userId = Long.parseLong(tokens.get(2)); // user_id
        long eventId = Long.parseLong(tokens.get(3));// event_id

        Question question =
                questionService.addQuestion(eventId, userId, content);

        System.out.println("Question ID: " + question.getId());
    
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
    

    
}

