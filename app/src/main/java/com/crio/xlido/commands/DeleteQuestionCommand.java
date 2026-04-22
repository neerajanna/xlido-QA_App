package com.crio.xlido.commands;

import java.util.List;
import com.crio.xlido.services.QuestionService;

public class DeleteQuestionCommand implements ICommand {

    private final QuestionService questionService;

    public DeleteQuestionCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

   
    @Override
public void invoke(List<String> tokens) {
    try {
        long questionId = Long.parseLong(tokens.get(1));
        long userId = Long.parseLong(tokens.get(2));

        questionService.deleteQuestion(questionId, userId);
        System.out.println("QUESTION_DELETED " + questionId);

    } catch (RuntimeException e) {
        System.out.println(e.getMessage());
    }
}
}
