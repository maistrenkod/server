package one.maistrenko.server;

import java.util.Map;

public interface UserService {
    User createUser(User user);

    User updateUser(User user);

    void removeUser(long userid);

    User getUserById(long userId);

    Map<Long, User> showAllUsers();
}
