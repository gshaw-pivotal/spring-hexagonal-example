package gs.hexagonaldemo.springhexagonaldemo.serviceadapters;

import gs.hexagonaldemo.springhexagonaldemo.models.User;
import gs.hexagonaldemo.springhexagonaldemo.ports.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetUserServiceAdapterTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetUserServiceAdapter getUserServiceAdapter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllSavedUsers_callsTheUserRepositoryTo() {
        when(userRepository.getUsers()).thenReturn(Collections.emptyList());

        getUserServiceAdapter.getAllUsers();

        verify(userRepository).getUsers();
    }

    @Test
    public void getAllSavedUsers_whenThereAreNoSavedUsers_returnsAnEmptyListOfUsers() {
        when(userRepository.getUsers()).thenReturn(Collections.emptyList());

        List<User> returnedUsers = getUserServiceAdapter.getAllUsers();

        assertTrue(returnedUsers.size() == 0);

        verify(userRepository).getUsers();
    }

    @Test
    public void getAllUsers_whenThereAreSavedUsers_returnsAPopulatedListOfUsers() {
        User user1 = User.builder().id(1).name("A name 1").build();
        User user2 = User.builder().id(2).name("A name 2").build();
        User user3 = User.builder().id(3).name("A name 3").build();

        List<User> savedUsers = Arrays.asList(user1, user2, user3);
        when(userRepository.getUsers()).thenReturn(savedUsers);

        List<User> returnedUsers = getUserServiceAdapter.getAllUsers();

        assertTrue(returnedUsers.size() == 3);
        assertEquals(returnedUsers, savedUsers);

        verify(userRepository).getUsers();
    }
}
