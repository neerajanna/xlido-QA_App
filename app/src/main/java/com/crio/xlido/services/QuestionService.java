package com.crio.xlido.services;

import com.crio.xlido.entities.Question;
import com.crio.xlido.repositories.EventRepository;
import com.crio.xlido.repositories.QuestionRepository;
import com.crio.xlido.repositories.UserRepository;

import java.util.*;

public class QuestionService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public QuestionService(
            QuestionRepository questionRepository,
            UserRepository userRepository,
            EventRepository eventRepository
    ) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    // ---------- ADD QUESTION ----------
    public Question addQuestion(long eventId, long userId, String text) {

        if (!userRepository.exists(userId)) {
            throw new RuntimeException(
                "ERROR: User with an id " + userId + " does not exist"
            );
        }

        if (!eventRepository.exists(eventId)) {
            throw new RuntimeException(
                "ERROR: Event with an id " + eventId + " does not exist"
            );
        }

        Question question = new Question(text, userId, eventId);
        return questionRepository.save(question);
    }

    // ---------- DELETE QUESTION ----------
    public void deleteQuestion(long questionId, long userId) {

        //User must exist FIRST
        if (!userRepository.exists(userId)) {
            throw new RuntimeException(
                "ERROR: User with an id " + userId + " does not exist"
            );
        }
    
        // Question must exist
        Question q = questionRepository.findById(questionId);
        if (q == null) {
            throw new RuntimeException(
                "ERROR: Question with an id " + questionId + " does not exist"
            );
        }
    
        //Ownership check
        if (q.getUserId() != userId) {
            throw new RuntimeException(
                "ERROR: User with an id " + userId +
                " is not an author of question with an id " + questionId
            );
        }
    
        // Delete
        questionRepository.delete(questionId);
    }

    // ---------- UPVOTE ----------
    public void upvote(long questionId, long userId) {

        // 1️⃣ Question must be checked FIRST
        Question q = questionRepository.findById(questionId);
        if (q == null) {
            throw new RuntimeException(
                "ERROR: Question with an id " + questionId + " does not exist"
            );
        }
    
        // 2️⃣ Then user
        if (!userRepository.exists(userId)) {
            throw new RuntimeException(
                "ERROR: User with an id " + userId + " does not exist"
            );
        }
    
        // 3️⃣ Duplicate handled here
        q.upvote(userId);
    }
    

    // ---------- REPLY ----------
    public void reply(long questionId, long userId, String text) {

        // 1️ Question first
        Question q = questionRepository.findById(questionId);
        if (q == null) {
            throw new RuntimeException(
                "ERROR: Question with an id " + questionId + " does not exist"
            );
        }
    
        // 2️ Then user
        if (!userRepository.exists(userId)) {
            throw new RuntimeException(
                "ERROR: User with an id " + userId + " does not exist"
            );
        }
    
        q.addReply(userId, text);
    }
    

    // ---------- LIST QUESTIONS ----------
    public List<Question> listQuestions(long eventId, String sortBy) {

        if (!eventRepository.exists(eventId)) {
            throw new RuntimeException(
                "ERROR: Event with an id " + eventId + " does not exist"
            );
        }
    
        List<Question> result = new ArrayList<>();
    
        for (Question q : questionRepository.findAll()) {
            if (q.getEventId() == eventId) {
                result.add(q);
            }
        }
    
        if (sortBy.equals("POPULAR")) {
            result.sort((a, b) -> {
                if (b.getVotes() != a.getVotes()) {
                    return b.getVotes() - a.getVotes();
                }
                return Long.compare(a.getId(), b.getId());
            });
        } else if (sortBy.equals("RECENT")) {
            result.sort((a, b) -> Long.compare(b.getId(), a.getId()));
        }
    
        return result;
    }
    
}
