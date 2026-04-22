package com.crio.xlido.commands;

import java.util.List;
import com.crio.xlido.entities.User;
import com.crio.xlido.services.UserService;

public class CreateUserCommand implements ICommand {

    private final UserService userService;

    public CreateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void invoke(List<String> tokens) {
        try {
            User user = userService.createUser(tokens.get(1), tokens.get(2));
            System.out.println("User ID: " + user.getId());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
