package gs.hexagonaldemo.springhexagonaldemo.serviceadapters;

import gs.hexagonaldemo.springhexagonaldemo.models.User;
import gs.hexagonaldemo.springhexagonaldemo.ports.GetUserService;

import java.util.Arrays;
import java.util.List;

public class GetUserServiceAdapter implements GetUserService {
    @Override
    public List<User> getAllUsers() {
        User user1 = User.builder().id(1234).name("thename").build();
        User user2 = User.builder().id(4567).name("anothername").build();

        return Arrays.asList(user1, user2);

    }

    @Override
    public String getUser(int userId) {
        return "{\"id\": " + userId + ", \"name\": \"a_single_user\"}";
    }
}
