package gs.hexagonaldemo.springhexagonaldemo;

import gs.hexagonaldemo.springhexagonaldemo.models.User;
import gs.hexagonaldemo.springhexagonaldemo.ports.AddUserService;
import gs.hexagonaldemo.springhexagonaldemo.ports.GetUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UsersRestController {

    private GetUserService getUserService;

    private AddUserService addUserService;

    @Autowired
    public UsersRestController(GetUserService getUserService, AddUserService addUserService) {
        this.getUserService = getUserService;
        this.addUserService = addUserService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUsers() {
        return getUserService.getAllUsers();
    }

    @RequestMapping(value = "/users/{userid}", method = RequestMethod.GET)
    public String getUser(@PathVariable int userid) {
        return getUserService.getUser(userid);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity addUser(@Valid @RequestBody User newUser) {
        addUserService.addUser(newUser);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
