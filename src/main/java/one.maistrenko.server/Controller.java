package one.maistrenko.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/v1/user")
public class Controller {
    private final UserService userService;

    public Controller(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "hello")
    public @ResponseBody String hello(){
        return "Hello";
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public @ResponseBody User updateUser(@RequestBody User user){
        return  userService.updateUser(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id:\\d+}")
    public void removeUser(@PathVariable long id){
        userService.removeUser(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id:\\d+}")
    public @ResponseBody User getUserById(@PathVariable long id){
        return userService.getUserById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Map<Long, User> showAllUsers(){
        return userService.showAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "user")
    public @ResponseBody String infoForUser() {
        return "You are a user!";
    }

    @RolesAllowed("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.GET, value = "admin")
    public  @ResponseBody String infoForAdmin(){
        return "You are an admin!";
    }

    @RequestMapping(method = RequestMethod.GET, value = "none")
    public @ResponseBody String infoForNone(){
        return "You are a guest";
    }
}
