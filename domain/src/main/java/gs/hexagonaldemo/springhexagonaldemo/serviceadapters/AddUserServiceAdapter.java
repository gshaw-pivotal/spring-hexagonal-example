package gs.hexagonaldemo.springhexagonaldemo.serviceadapters;

import gs.hexagonaldemo.springhexagonaldemo.models.User;
import gs.hexagonaldemo.springhexagonaldemo.ports.AddUserService;
import gs.hexagonaldemo.springhexagonaldemo.ports.NameVerifierService;
import gs.hexagonaldemo.springhexagonaldemo.ports.UserRepository;

public class AddUserServiceAdapter implements AddUserService {

    private NameVerifierService nameVerifierService;

    private UserRepository userRepository;

    public AddUserServiceAdapter(NameVerifierService nameVerifierService,
                                 UserRepository userRepository) {
        this.nameVerifierService = nameVerifierService;
        this.userRepository = userRepository;
    }

    @Override
    public int addUser(User newUser) {
        if (newUser.getName().length() != 0 && nameVerifierService.verifyName(newUser.getName())) {
            return userRepository.addUser(newUser);
        }

        return -1;
    }
}
