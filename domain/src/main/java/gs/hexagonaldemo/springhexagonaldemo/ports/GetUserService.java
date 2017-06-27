package gs.hexagonaldemo.springhexagonaldemo.ports;

import gs.hexagonaldemo.springhexagonaldemo.models.User;

import java.util.List;

public interface GetUserService {

    List<User> getAllUsers();

    String getUser(int userId);

}
