package com.crio.xlido.repositories;

import java.util.HashMap;
import java.util.Map;
import com.crio.xlido.entities.User;

public class UserRepository {

    private final Map<Long, User> users = new HashMap<>();

public User save(User user) {
    users.put(user.getId(), user);
    return user;
}

public boolean exists(long id) {
    return users.containsKey(id);
}

public User findById(long id) {
    return users.get(id);
}

}
