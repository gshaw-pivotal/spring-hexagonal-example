package gs.hexagonaldemo.springhexagonaldemo.serviceadapters;

import gs.hexagonaldemo.springhexagonaldemo.models.User;
import gs.hexagonaldemo.springhexagonaldemo.ports.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddUserServiceAdapterTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AddUserServiceAdapter addUserServiceAdapter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenAValidUser_itCallsTheUserRepository() {
        User newUser = User.builder().name("A name").build();

        when(userRepository.addUser(newUser)).thenReturn(1);

        addUserServiceAdapter.addUser(newUser);

        verify(userRepository).addUser(newUser);
    }

    @Test
    public void givenAValidUser_itReturnsTheUserId() {
        int userId = 1;

        User newUser = User.builder().name("A name").build();

        when(userRepository.addUser(newUser)).thenReturn(userId);

        assertTrue(addUserServiceAdapter.addUser(newUser) == userId);

        verify(userRepository).addUser(newUser);
    }

    @Test
    public void givenAUserWithAnEmptyName_itDoesNotSaveTheUserToTheRepository() {
        User newUser = User.builder().name("").build();

        addUserServiceAdapter.addUser(newUser);

        verify(userRepository, never()).addUser(newUser);
    }

    @Test
    public void givenAUserWithAnEmptyName_itReturnsANegativeUserId() {
        User newUser = User.builder().name("").build();

        assertTrue(addUserServiceAdapter.addUser(newUser) < 0);

        verify(userRepository, never()).addUser(newUser);
    }

}