package gs.hexagonaldemo.springhexagonaldemo.serviceadapters;

import gs.hexagonaldemo.springhexagonaldemo.models.User;
import gs.hexagonaldemo.springhexagonaldemo.ports.GetUserService;
import gs.hexagonaldemo.springhexagonaldemo.ports.UserRepository;

import java.util.List;
import java.util.Optional;

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
    public Optional<User> getUser(int userId) {
        return userRepository.getUser(userId);
    }
}
