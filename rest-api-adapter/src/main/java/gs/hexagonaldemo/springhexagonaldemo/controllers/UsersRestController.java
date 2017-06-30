package gs.hexagonaldemo.springhexagonaldemo.controllers;

import gs.hexagonaldemo.springhexagonaldemo.models.ErrorMessage;
import gs.hexagonaldemo.springhexagonaldemo.models.User;
import gs.hexagonaldemo.springhexagonaldemo.ports.AddUserService;
import gs.hexagonaldemo.springhexagonaldemo.ports.GetUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity getUser(@PathVariable int userid) {
        Optional<User> possibleUser = getUserService.getUser(userid);
        if (possibleUser.isPresent()) {
            return new ResponseEntity(possibleUser.get(), HttpStatus.OK);
        }
        return new ResponseEntity(
                ErrorMessage.builder().message("User with id " + userid + " was not found").build(),
                HttpStatus.NOT_FOUND
        );
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity addUser(@Valid @RequestBody User newUser) {
        int userId = addUserService.addUser(newUser);
        if (userId > 0) {
            return new ResponseEntity("{\"id\": " + userId + "}", HttpStatus.CREATED);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
