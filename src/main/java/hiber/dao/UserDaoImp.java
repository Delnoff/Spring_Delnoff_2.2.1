package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> readUsers(String model, int series) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("select p from User p " +
                                                                                "where p.car.model = :one " +
                                                                                "and p.car.series = :two")
                .setParameter("one", model)
                .setParameter("two", series);
        return query.getResultList();
    }

}
