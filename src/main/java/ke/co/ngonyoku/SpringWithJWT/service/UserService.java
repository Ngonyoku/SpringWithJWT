package ke.co.ngonyoku.SpringWithJWT.service;

import ke.co.ngonyoku.SpringWithJWT.domain.Role;
import ke.co.ngonyoku.SpringWithJWT.domain.User;

import java.util.List;

/*
 * This interface provides what is to be expected of the service class implementation
 * */
public interface UserService {
    User saveUser(User user); //Saves user to database

    Role saveRole(Role role);  //Saves roles to the database

    void addRoleToUser(String username, String roleName);  //Attaches a role to the user with the username provided

    User getUser(String username);  //Returns a user with the username provided

    Long deleteUserByUsername(String username);

    /*
     * NB: In the real world, DON'T dare to return a List of User
     * Instead, return the data in pages
     * This is so as NOT to strain the backend
     * */
    List<User> getUsers();  //Returns a list of users
}
