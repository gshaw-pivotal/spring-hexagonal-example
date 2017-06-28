package gs.hexagonaldemo.springhexagonaldemo.repositoryadapters;

import gs.hexagonaldemo.springhexagonaldemo.models.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class UserRepositoryAdapterTest {

    private UserRepositoryAdapter userRepositoryAdapter;

    @Before
    public void setup() {
        userRepositoryAdapter = new UserRepositoryAdapter();
    }

    @Test
    public void userRepository_givenAUser_itStoresTheUser() {
        User newUser = User.builder().name("A name").build();

        assertTrue(userRepositoryAdapter.getUsers().size() == 0);

        userRepositoryAdapter.addUser(newUser);

        assertTrue(userRepositoryAdapter.getUsers().size() == 1);
    }
}