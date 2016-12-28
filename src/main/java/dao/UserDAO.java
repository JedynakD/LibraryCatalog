package dao;

import model.User;

public interface UserDAO {
    void save(User user);

    User getUserById(long id);

    User getUserByName(String userName);

    void delete(User user);
}
