package gs.hexagonaldemo.springhexagonaldemo.serviceadapters;

import gs.hexagonaldemo.springhexagonaldemo.models.User;
import gs.hexagonaldemo.springhexagonaldemo.ports.AddUserService;
import gs.hexagonaldemo.springhexagonaldemo.ports.UserRepository;

public class AddUserServiceAdapter implements AddUserService {

    private UserRepository userRepository;

    public AddUserServiceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public int addUser(User newUser) {
        if (newUser.getName().length() != 0) {
            return userRepository.addUser(newUser);
        }

        return -1;
    }
}
