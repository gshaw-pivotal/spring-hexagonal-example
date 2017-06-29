package gs.hexagonaldemo.springhexagonaldemo.serviceadapters;

import gs.hexagonaldemo.springhexagonaldemo.models.User;
import gs.hexagonaldemo.springhexagonaldemo.ports.GetUserService;
import gs.hexagonaldemo.springhexagonaldemo.ports.UserRepository;

import java.util.Arrays;
import java.util.List;

public class GetUserServiceAdapter implements GetUserService {

    private UserRepository userRepository;

    public GetUserServiceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getUsers();
    }

    @Override
    public String getUser(int userId) {
        return "{\"id\": " + userId + ", \"name\": \"a_single_user\"}";
    }
}
