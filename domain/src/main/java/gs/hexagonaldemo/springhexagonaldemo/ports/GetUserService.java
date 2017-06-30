package gs.hexagonaldemo.springhexagonaldemo.ports;

import gs.hexagonaldemo.springhexagonaldemo.models.User;

import java.util.List;
import java.util.Optional;

public interface GetUserService {

    List<User> getAllUsers();

    Optional<User> getUser(int userId);

}
