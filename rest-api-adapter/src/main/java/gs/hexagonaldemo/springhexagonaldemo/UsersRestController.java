package gs.hexagonaldemo.springhexagonaldemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersRestController {

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers() {
        return "[{\"id\": 1234, \"name\": \"thename\"}, {\"id\": 4567, \"name\": \"anothername\"}]";
    }

    @RequestMapping(value = "/users/{userid}", method = RequestMethod.GET)
    public String getUser(@PathVariable String userid) {
        return "{\"id\": " + userid + ", \"name\": \"a_single_user\"}";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity addUser() {
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
