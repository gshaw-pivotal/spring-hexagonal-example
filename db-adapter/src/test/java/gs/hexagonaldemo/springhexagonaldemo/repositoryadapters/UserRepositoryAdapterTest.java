package gs.hexagonaldemo.springhexagonaldemo.repositoryadapters;

import gs.hexagonaldemo.springhexagonaldemo.models.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class UserRepositoryAdapterTest {

    private UserRepositoryAdapter userRepositoryAdapter;

    @Before
    public void setup() {
        userRepositoryAdapter = new UserRepositoryAdapter();
    }

    @Test
    public void userRepository_givenAUserToAdd_itStoresTheUser() {
        User newUser = User.builder().name("A name").build();

        assertTrue(userRepositoryAdapter.getUsers().size() == 0);

        userRepositoryAdapter.addUser(newUser);

        assertTrue(userRepositoryAdapter.getUsers().size() == 1);
    }

    @Test
    public void userRepository_givenAUserToAdd_theAddedUserIsGivenAnId() {
        User newUser = User.builder().name("A name").build();

        userRepositoryAdapter.addUser(newUser);

        List<User> users = userRepositoryAdapter.getUsers();

        assertTrue(users.get(0).getId() != 0);
    }
}