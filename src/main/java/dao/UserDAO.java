package dao;

import model.User;

/**
 * Created by Damian on 2016-08-17.
 */
public interface UserDAO {
    void save(User user);

    User getUserById(long id);

    User getUserByName(String userName);

    void delete(User user);
}
