package gs.hexagonaldemo.springhexagonaldemo.controllers;

import gs.hexagonaldemo.springhexagonaldemo.controllers.UsersRestController;
import gs.hexagonaldemo.springhexagonaldemo.models.User;
import gs.hexagonaldemo.springhexagonaldemo.ports.AddUserService;
import gs.hexagonaldemo.springhexagonaldemo.ports.GetUserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UsersRestControllerTest {

    @Mock
    private GetUserService getUserServiceMock;

    @Mock
    private AddUserService addUserServiceMock;

    @InjectMocks
    private UsersRestController usersRestController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllUsers_callsTheGetUserService() {
        when(getUserServiceMock.getAllUsers()).thenReturn(Collections.emptyList());

        usersRestController.getUsers();

        verify(getUserServiceMock).getAllUsers();
    }

    @Test
    public void getUser_callsTheGetUserService() {
        int userId = 123;

        when(getUserServiceMock.getUser(userId)).thenReturn(Optional.empty());

        usersRestController.getUser(userId);

        verify(getUserServiceMock).getUser(userId);
    }

    @Test
    public void getUser_givenAUserThatExists_returnsThatUser() {
        int userId = 123;
        User userToRetrieve = User.builder().id(userId).name("User name").build();

        when(getUserServiceMock.getUser(userId)).thenReturn(Optional.of(userToRetrieve));

        ResponseEntity responseEntity = usersRestController.getUser(userId);
        User returnedUser = ((User)responseEntity.getBody());

        assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
        assertTrue(returnedUser.equals(userToRetrieve));

        verify(getUserServiceMock).getUser(userId);
    }

    @Test
    public void getUser_givenAUserThatDoesNotExist_returnsABadRequest() {
        int userId = 123;

        when(getUserServiceMock.getUser(userId)).thenReturn(Optional.empty());

        ResponseEntity responseEntity = usersRestController.getUser(userId);

        assertTrue(responseEntity.getStatusCode() == HttpStatus.NOT_FOUND);

        verify(getUserServiceMock).getUser(userId);
    }

    @Test
    public void getUser_givenAUserThatDoesNotExist_returnsAnErrorMessage() {
        int userId = 123;

        when(getUserServiceMock.getUser(userId)).thenReturn(Optional.empty());

        ResponseEntity responseEntity = usersRestController.getUser(userId);

        assertTrue(responseEntity.getBody().toString().contains("User with id " + userId + " was not found"));

        verify(getUserServiceMock).getUser(userId);
    }

    @Test
    public void addUser_callsTheAddUserService() {
        User newUser = User.builder().name("Name").build();

        when(addUserServiceMock.addUser(newUser)).thenReturn(1);

        usersRestController.addUser(newUser);

        verify(addUserServiceMock).addUser(newUser);
    }

    @Test
    public void addUser_givenAValidUserThatIsSaved_returnsACreatedResponseWithTheUserId() {
        User newUser = User.builder().name("Name").build();

        when(addUserServiceMock.addUser(newUser)).thenReturn(1);

        ResponseEntity responseEntity = usersRestController.addUser(newUser);

        assertTrue(responseEntity.getStatusCode() == HttpStatus.CREATED);
        assertTrue(responseEntity.getBody().toString().equals("{\"id\": 1}"));

        verify(addUserServiceMock).addUser(newUser);
    }

    @Test
    public void addUser_givenAUserThatIsNotSaved_returnsABadRequestResponse() {
        User newUser = User.builder().name("").build();

        when(addUserServiceMock.addUser(newUser)).thenReturn(-1);

        ResponseEntity responseEntity = usersRestController.addUser(newUser);

        assertTrue(responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST);

        verify(addUserServiceMock).addUser(newUser);
    }
}