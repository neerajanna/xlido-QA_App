package com.crio.xlido.commands;

import java.util.List;
import com.crio.xlido.entities.Question;
import com.crio.xlido.services.QuestionService;

public class ListQuestionsCommand implements ICommand {

    private final QuestionService questionService;

    public ListQuestionsCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

   
    @Override
public void invoke(List<String> tokens) {
    try {
        long eventId = Long.parseLong(tokens.get(1));
        String sortBy = tokens.get(2);

        List<Question> questions =
                questionService.listQuestions(eventId, sortBy);

        for (int i = 0; i < questions.size(); i++) {
            System.out.println(questions.get(i));
            
                System.out.println(); // blank line between questions
            
        }

    } catch (RuntimeException e) {
        System.out.println(e.getMessage());
    }
}

}
