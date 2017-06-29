package gs.hexagonaldemo.springhexagonaldemo.ports;

import gs.hexagonaldemo.springhexagonaldemo.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    int addUser(User newUser);

    List<User> getUsers();

    void deleteUser(int id);

    Optional<User> getUser(int userId);

}
