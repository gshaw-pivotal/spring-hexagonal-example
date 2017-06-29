package gs.hexagonaldemo.springhexagonaldemo.repositoryadapters;

import gs.hexagonaldemo.springhexagonaldemo.models.User;
import gs.hexagonaldemo.springhexagonaldemo.ports.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryAdapter implements UserRepository {

    private List<User> users = new ArrayList<>();

    private int nextAvailableId = 1;

    @Override
    public int addUser(User newUser) {
        newUser.setId(nextAvailableId);
        users.add(newUser);

        incrementNextAvailableId();

        return newUser.getId();
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public void deleteUser(int id) {
        for (int index = 0; index < users.size(); index++) {
            if (users.get(index).getId() == id) {
                users.remove(index);
                return ;
            }
        }
    }

    @Override
    public Optional<User> getUser(int userId) {
        return users.stream().filter(user ->user.getId() == userId).findFirst();
    }

    private void incrementNextAvailableId() {
        nextAvailableId++;
    }
}
