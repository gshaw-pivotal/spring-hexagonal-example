package gs.hexagonaldemo.springhexagonaldemo;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import gs.hexagonaldemo.springhexagonaldemo.helper.JsonValidator;
import gs.hexagonaldemo.springhexagonaldemo.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringHexagonalDemoApplication.class,
                webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserContractTest {

    private String rootURL = "http://localhost:";

    @Value("${local.server.port}")
    private String port;

    private TestRestTemplate restTemplate;

    private JsonValidator validator = new JsonValidator();

    @Before
    public void setup() {
        restTemplate = new TestRestTemplate();
    }

    @Test
    public void GET_users_returnsAListOfUsers() throws IOException, ProcessingException {
        ResponseEntity<String> response = restTemplate.getForEntity(buildURL() + "/users", String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        String responseData = response.getBody();

        assertTrue(validateJson(buildURL("/json/GetUsers.json"), responseData));
    }

    @Test
    public void GET_users_givenAnUserID_returnsASingleUser() throws IOException, ProcessingException {
        ResponseEntity<String> response = restTemplate.getForEntity(buildURL() + "/users/9999", String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        String responseData = response.getBody();

        assertTrue(validateJson(buildURL("/json/GetUser.json"), responseData));
    }

    @Test
    public void POST_users_addAValidUser_returnsCreated() throws MalformedURLException {
        User newUser = User.builder().userId(123).userName("Name").build();
        ResponseEntity<String> response = restTemplate.postForEntity(buildURL() + "/users", newUser, String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
    }

    @Test
    public void POST_users_givenNotAUser_returnsBadRequest() throws MalformedURLException {
        ResponseEntity<String> response = restTemplate.postForEntity(buildURL() + "/users", "something that is not a user", String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.UNSUPPORTED_MEDIA_TYPE));
    }

    private boolean validateJson(URL schemaSpec, String responseData) throws IOException, ProcessingException {
        return validator.validateJson(schemaSpec, responseData);
    }

    private URL buildURL() throws MalformedURLException {
        return new URL(rootURL + port);
    }

    private URL buildURL(String path) throws MalformedURLException {
        return new URL(rootURL + port + path);
    }
}
