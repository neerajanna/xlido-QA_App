package com.crio.xlido.commands;

import java.util.List;
import com.crio.xlido.services.QuestionService;

public class UpvoteQuestionCommand implements ICommand {

    private final QuestionService questionService;

    public UpvoteQuestionCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

    
    @Override
    public void invoke(List<String> tokens) {
        try {
            long questionId = Long.parseLong(tokens.get(1)); //  question_id
        long userId = Long.parseLong(tokens.get(2));     //  user_id

        questionService.upvote(questionId, userId);
        System.out.println("QUESTION_UPVOTED " + questionId);
    
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    }
    


