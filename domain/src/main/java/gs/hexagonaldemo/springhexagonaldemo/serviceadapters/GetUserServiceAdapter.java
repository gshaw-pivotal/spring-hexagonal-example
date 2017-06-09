package gs.hexagonaldemo.springhexagonaldemo.serviceadapters;

import gs.hexagonaldemo.springhexagonaldemo.ports.GetUserService;
import org.springframework.stereotype.Component;

@Component
public class GetUserServiceAdapter implements GetUserService {
    @Override
    public String getAllUsers() {
        return "[{\"id\": 1234, \"name\": \"thename\"}, {\"id\": 4567, \"name\": \"anothername\"}]";
    }

    @Override
    public String getUser(int userId) {
        return "{\"id\": " + userId + ", \"name\": \"a_single_user\"}";
    }
}
