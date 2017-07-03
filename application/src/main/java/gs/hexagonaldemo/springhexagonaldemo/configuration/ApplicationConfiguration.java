package gs.hexagonaldemo.springhexagonaldemo.configuration;

import gs.hexagonaldemo.springhexagonaldemo.ports.AddUserService;
import gs.hexagonaldemo.springhexagonaldemo.ports.GetUserService;
import gs.hexagonaldemo.springhexagonaldemo.ports.NameVerifierService;
import gs.hexagonaldemo.springhexagonaldemo.ports.UserRepository;
import gs.hexagonaldemo.springhexagonaldemo.repositoryadapters.UserRepositoryAdapter;
import gs.hexagonaldemo.springhexagonaldemo.serviceadapters.AddUserServiceAdapter;
import gs.hexagonaldemo.springhexagonaldemo.serviceadapters.GetUserServiceAdapter;
import gs.hexagonaldemo.springhexagonaldemo.verifiers.NameVerifierAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public AddUserService addUserService(NameVerifierService nameVerifierService, UserRepository userRepository) {
        return new AddUserServiceAdapter(nameVerifierService, userRepository);
    }

    @Bean
    public GetUserService getUserService(UserRepository userRepository) {
        return new GetUserServiceAdapter(userRepository);
    }

    @Bean
    public NameVerifierService nameVerifierService() {
        return new NameVerifierAdapter();
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryAdapter();
    }
}
