package gs.hexagonaldemo.springhexagonaldemo.repositoryadapters;

import gs.hexagonaldemo.springhexagonaldemo.models.User;
import gs.hexagonaldemo.springhexagonaldemo.ports.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryAdapter implements UserRepository {

    private List<User> users = new ArrayList<>();

    @Override
    public void addUser(User newUser) {
        users.add(newUser);
    }

    @Override
    public List<User> getUsers() {
        return users;
    }
}
