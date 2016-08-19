package dao;

import model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Damian on 2016-08-17.
 */
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public User getUserById(long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        if (user == null) {
            return new User();
        }
        return user;
    }

    @Override
    public User getUserByName(String userName) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("name", userName));
        List<User> users = criteria.list();

        if (!users.isEmpty()) {
            User object = users.get(0);
            return object;
        }
        return new User();
    }

    @Override
    public void delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }
}
