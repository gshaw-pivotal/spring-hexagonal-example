package gs.hexagonaldemo.springhexagonaldemo;

import gs.hexagonaldemo.springhexagonaldemo.models.User;
import gs.hexagonaldemo.springhexagonaldemo.ports.AddUserService;
import gs.hexagonaldemo.springhexagonaldemo.ports.GetUserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doNothing;
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
        when(getUserServiceMock.getAllUsers()).thenReturn("");

        usersRestController.getUsers();

        verify(getUserServiceMock).getAllUsers();
    }

    @Test
    public void getUser_callsTheGetUserService() {
        int userId = 123;

        when(getUserServiceMock.getUser(userId)).thenReturn("");

        usersRestController.getUser(userId);

        verify(getUserServiceMock).getUser(userId);
    }

    @Test
    public void addUser_callsTheAddUserService() {
        User newUser = User.builder().userId(123).userName("Name").build();

        doNothing().when(addUserServiceMock).addUser(newUser);

        usersRestController.addUser(newUser);

        verify(addUserServiceMock).addUser(newUser);
    }
}