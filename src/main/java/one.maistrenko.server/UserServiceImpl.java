package one.maistrenko.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service("user-service")
public class UserServiceImpl implements UserService {
    private final Map<Long, User> users = new HashMap<>();
    private long id;

    private long generateId(){
       return ++id;
    }

    @Override
    public User createUser(User user) {
        User helpUser = User.builder()
                .userid(generateId())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
        users.put(id, helpUser);
        user.setUserid(id);
        log.info("User {{}} was created", user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        User helpUser = users.get(user.getUserid());
        if(null == helpUser){
            log.warn("Update user with id {{}} was failed: user not exists", user.getUserid());
            throw new RuntimeException("There is no user with id =" + user.getUserid());
        }
        helpUser.setUsername(user.getUsername());
        helpUser.setPassword(user.getPassword());
        helpUser.setRole(user.getRole());
        log.info("User {{}} was updated", user);
        return user;
    }

    @Override
    public void removeUser(long userid) {
        if (users.containsKey(userid)){
            users.remove(userid);
            log.info("user with id {{}} successfully removed", userid);
        } else{
            log.warn("Removing user with id {{}} was failed : user not exists", userid);
            throw new RuntimeException("There is no user with id=" + userid);
        }
    }

    @Override
    public User getUserById(long userId) {
        User helpuser = users.get(userId);
        if(null == helpuser){
            log.warn("Get user with id {{}} was failed: user not exists", userId);
            throw new RuntimeException("User with id = "+ userId + " not exists");
        }
        return User.builder()
                .userid(userId)
                .username(helpuser.getUsername())
                .password(helpuser.getPassword())
                .role(helpuser.getRole())
                .build();
    }

    @Override
    public Map<Long, User> showAllUsers() {
        for (User i: users.values()) {
            System.out.println(i);
        }
        return users;
    }
}
