package gs.hexagonaldemo.springhexagonaldemo.serviceadapters;

import gs.hexagonaldemo.springhexagonaldemo.models.User;
import gs.hexagonaldemo.springhexagonaldemo.ports.AddUserService;
import org.springframework.stereotype.Component;

@Component
public class AddUserServiceAdapter implements AddUserService {
    @Override
    public void addUser(User newUser) {

    }
}
