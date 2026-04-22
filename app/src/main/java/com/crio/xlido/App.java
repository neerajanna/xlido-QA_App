package com.crio.xlido;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.crio.xlido.commands.*;
import com.crio.xlido.entities.Event;
import com.crio.xlido.entities.Question;
import com.crio.xlido.entities.User;
import com.crio.xlido.repositories.EventRepository;
import com.crio.xlido.repositories.QuestionRepository;
import com.crio.xlido.repositories.UserRepository;
import com.crio.xlido.services.EventService;
import com.crio.xlido.services.QuestionService;
import com.crio.xlido.services.UserService;

public class App {

    // ---------------- REPOSITORIES ----------------
    private final UserRepository userRepository = new UserRepository();
    private final EventRepository eventRepository = new EventRepository();
    private final QuestionRepository questionRepository = new QuestionRepository();

    // ---------------- SERVICES ----------------
    private final UserService userService = new UserService(userRepository);
    private final EventService eventService =
            new EventService(eventRepository, userRepository);
            QuestionService questionService1 =
            new QuestionService(
                questionRepository,
                userRepository,
                eventRepository
            );

            private final QuestionService questionService =
            new QuestionService(questionRepository, userRepository, eventRepository);      

    // ---------------- COMMAND INVOKER ----------------
    private final CommandInvoker commandInvoker = new CommandInvoker();

    // ---------------- CONSTRUCTOR ----------------
    // This is where ALL commands are registered
    public App() {
        User.resetCounter();
        Event.resetCounter();
        Question.resetCounter();
        // CREATE_USER <userName>
        commandInvoker.register(
                "CREATE_USER",
                new CreateUserCommand(userService)
        );

        // CREATE_EVENT <eventName> <userId>
        commandInvoker.register(
                "CREATE_EVENT",
                new CreateEventCommand(eventService)
        );

        // DELETE_EVENT <eventId>
        commandInvoker.register(
                "DELETE_EVENT",
                new DeleteEventCommand(eventService)
        );

        // ADD_QUESTION <eventId> <questionText>
        commandInvoker.register(
                "ADD_QUESTION",
                new AddQuestionCommand(questionService)
        );

        // DELETE_QUESTION <questionId>
        commandInvoker.register(
                "DELETE_QUESTION",
                new DeleteQuestionCommand(questionService)
        );

        // UPVOTE_QUESTION <questionId>
        commandInvoker.register(
                "UPVOTE_QUESTION",
                new UpvoteQuestionCommand(questionService)
        );

        // REPLY_QUESTION <questionId> <replyText>
        commandInvoker.register(
                "REPLY_QUESTION",
                new ReplyQuestionCommand(questionService)
        );

        // LIST_QUESTIONS <eventId>
        commandInvoker.register(
                "LIST_QUESTIONS",
                new ListQuestionsCommand(questionService)
        );
    }

    // ---------------- MAIN METHOD ----------------
    public static void main(String[] args) {

        // Used when running with input file
        if (args.length == 1) {
            try {
                List<String> commands =
                        Files.readAllLines(Paths.get(args[0].split("=")[1]));
                new App().run(commands);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        // Default empty run
        new App().run(new ArrayList<>());
    }

    // ---------------- RUN METHOD ----------------
    // This method is used by AppTest
    public void run(List<String> commands) {

        for (String line : commands) {

            // Ignore empty lines
            if (line == null || line.isEmpty()) {
                continue;
            }

            // Split command by comma
            List<String> tokens = Arrays.asList(line.split(","));

            try {
                // Delegate execution to CommandInvoker
                commandInvoker.invokeCommand(tokens);
            } catch (RuntimeException e) {
                // Print exact error message (important for tests)
                System.out.println(e.getMessage());
            }
        }
    }
}
