package gs.hexagonaldemo.springhexagonaldemo.serviceadapters;

import gs.hexagonaldemo.springhexagonaldemo.models.User;
import gs.hexagonaldemo.springhexagonaldemo.ports.NameVerifierService;
import gs.hexagonaldemo.springhexagonaldemo.ports.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class AddUserServiceAdapterTest {

    @Mock
    private NameVerifierService nameVerifierService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AddUserServiceAdapter addUserServiceAdapter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenAUser_itCallsTheNameVerifier() {
        String nameOfUser = "A name";
        User newUser = User.builder().name(nameOfUser).build();

        when(nameVerifierService.verifyName(nameOfUser)).thenReturn(true);

        addUserServiceAdapter.addUser(newUser);

        verify(nameVerifierService).verifyName(nameOfUser);
    }

    @Test
    public void givenAUserWithAnInvalidName_itDoesNotCallTheUserRepository() {
        String nameOfUser = "bad 777 name";
        User newUser = User.builder().name(nameOfUser).build();

        when(nameVerifierService.verifyName(nameOfUser)).thenReturn(false);

        addUserServiceAdapter.addUser(newUser);

        verify(nameVerifierService).verifyName(nameOfUser);
        verify(userRepository, never()).addUser(newUser);
    }

    @Test
    public void givenAUserWithAnInvalidName_itReturnsANegativeUserId() {
        String nameOfUser = "bad 777 name";
        User newUser = User.builder().name(nameOfUser).build();

        when(nameVerifierService.verifyName(nameOfUser)).thenReturn(false);

        assertTrue(addUserServiceAdapter.addUser(newUser) < 0);

        verify(nameVerifierService).verifyName(nameOfUser);
        verify(userRepository, never()).addUser(newUser);
    }

    @Test
    public void givenAValidUser_itCallsTheUserRepository() {
        String nameOfUser = "A name";
        User newUser = User.builder().name(nameOfUser).build();

        when(nameVerifierService.verifyName(nameOfUser)).thenReturn(true);
        when(userRepository.addUser(newUser)).thenReturn(1);

        addUserServiceAdapter.addUser(newUser);

        verify(userRepository).addUser(newUser);
    }

    @Test
    public void givenAValidUser_itReturnsTheUserId() {
        int userId = 1;

        String nameOfUser = "A name";
        User newUser = User.builder().name(nameOfUser).build();

        when(nameVerifierService.verifyName(nameOfUser)).thenReturn(true);
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