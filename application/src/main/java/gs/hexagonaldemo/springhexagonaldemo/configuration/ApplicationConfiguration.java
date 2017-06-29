package gs.hexagonaldemo.springhexagonaldemo.configuration;

import gs.hexagonaldemo.springhexagonaldemo.ports.AddUserService;
import gs.hexagonaldemo.springhexagonaldemo.ports.GetUserService;
import gs.hexagonaldemo.springhexagonaldemo.ports.UserRepository;
import gs.hexagonaldemo.springhexagonaldemo.repositoryadapters.UserRepositoryAdapter;
import gs.hexagonaldemo.springhexagonaldemo.serviceadapters.AddUserServiceAdapter;
import gs.hexagonaldemo.springhexagonaldemo.serviceadapters.GetUserServiceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public AddUserService addUserService(UserRepository userRepository) {
        return new AddUserServiceAdapter(userRepository);
    }

    @Bean
    public GetUserService getUserService() {
        return new GetUserServiceAdapter();
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryAdapter();
    }
}
