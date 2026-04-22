package com.crio.xlido.commands;

import java.util.List;
import com.crio.xlido.services.QuestionService;

public class ReplyQuestionCommand implements ICommand {

    private final QuestionService questionService;

    public ReplyQuestionCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

  
  
    @Override
    public void invoke(List<String> tokens) {
        try {
            String reply = tokens.get(1);                 // reply_content
        long questionId = Long.parseLong(tokens.get(2)); // question_id
        long userId = Long.parseLong(tokens.get(3));     // user_id

        questionService.reply(questionId, userId, reply);
        System.out.println("REPLY_ADDED");
    
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
    

}
