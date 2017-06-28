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
    public void userRepository_givenAUserIdToDelete_itDeletesTheUser() {
        User user = User.builder().name("A name").build();
        userRepositoryAdapter.addUser(user);
        List<User> users = userRepositoryAdapter.getUsers();
        int userId = users.get(0).getId();

        userRepositoryAdapter.deleteUser(userId);

        assertTrue(userRepositoryAdapter.getUsers().size() == 0);
    }

    @Test
    public void userRepository_givenAUserIdToDelete_itDeletesTheCorrectUserWhenThereAreMultipleUsersStored() {
        User user1 = User.builder().name("A name 1").build();
        User user2 = User.builder().name("A name 2").build();
        User user3 = User.builder().name("A name 3").build();
        userRepositoryAdapter.addUser(user1);
        userRepositoryAdapter.addUser(user2);
        userRepositoryAdapter.addUser(user3);

        List<User> users = userRepositoryAdapter.getUsers();
        int idOfUserToDelete = users.get(0).getId();

        userRepositoryAdapter.deleteUser(idOfUserToDelete);

        users = userRepositoryAdapter.getUsers();

        assertTrue(users.size() == 2);

        assertTrue(users.get(0).getId() != idOfUserToDelete);
        assertTrue(users.get(1).getId() != idOfUserToDelete);
    }

    @Test
    public void userRepository_givenAUserToAdd_theAddedUserIsGivenAnId() {
        User newUser = User.builder().name("A name").build();

        userRepositoryAdapter.addUser(newUser);

        List<User> users = userRepositoryAdapter.getUsers();

        assertTrue(users.get(0).getId() != 0);
    }
}