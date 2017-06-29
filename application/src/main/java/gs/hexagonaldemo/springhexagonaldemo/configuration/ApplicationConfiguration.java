package gs.hexagonaldemo.springhexagonaldemo.configuration;

import gs.hexagonaldemo.springhexagonaldemo.ports.AddUserService;
import gs.hexagonaldemo.springhexagonaldemo.ports.GetUserService;
import gs.hexagonaldemo.springhexagonaldemo.serviceadapters.AddUserServiceAdapter;
import gs.hexagonaldemo.springhexagonaldemo.serviceadapters.GetUserServiceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public AddUserService addUserService() {
        return new AddUserServiceAdapter();
    }

    @Bean
    public GetUserService getUserService() {
        return new GetUserServiceAdapter();
    }
}
