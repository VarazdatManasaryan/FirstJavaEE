package com.digi.repository.impl;

import com.digi.model.User;
import com.digi.repository.UserRepository;
import com.digi.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

public class UserRepositoryImplJPA implements UserRepository {

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void save(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }

    @Override
    public User getByUsername(String email) {
        Session session = sessionFactory.openSession();
        NativeQuery<User> nativeQuery = session
                .createNativeQuery("select * from users where email = ?", User.class);

        nativeQuery.setParameter(1, email);
        return nativeQuery.uniqueResult();
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
    }

    @Override
    public void changePassword(String email, String password) {
        User user = getByUsername(email);
        user.setPassword(password);
        updateUser(user);
    }

    @Override
    public void deleteAccount(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
    }

    public User getById(int id) {
        Session session = sessionFactory.openSession();
        User user = session.find(User.class, id);
        return user;
    }
}
