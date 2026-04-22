package com.crio.xlido.repositories;

import com.crio.xlido.entities.Question;
import java.util.*;

public class QuestionRepository {

    private final Map<Long, Question> store = new HashMap<>();

    public Question save(Question q) {
        store.put(q.getId(), q);   // ✅ store original
        return q;
    }

    public Question findById(long id) {
        return store.get(id);
    }

    public boolean exists(long id) {
        return store.containsKey(id);
    }

    public void delete(long id) {
        store.remove(id);
    }

    public List<Question> findAll() {
        return new ArrayList<>(store.values());
    }
}
