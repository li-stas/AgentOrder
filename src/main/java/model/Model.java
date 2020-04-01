package model;

import entities.User;

import java.util.ArrayList;
import java.util.List;


public class Model {
    private static Model instance = new Model();

    private List<User> model;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        model = new ArrayList<>();
    }

    public void add(User user) {
        model.add(user);
    }

    public List<String> list() {
        List<String> list = new ArrayList<>();
        for (User user : model) {
            String name = user.getName();
            list.add(name);
        }
        return list;

/*
        return model.stream()
                .map(User::getName)
                .collect(Collectors.toList());
*/
    }
}
