package com.digi.repository.impl;

import com.digi.model.Address;
import com.digi.repository.AddressRepository;
import com.digi.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

public class AddressRepositoryImplJPA implements AddressRepository {

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void saveAddress(Address address) {
        Session session = sessionFactory.openSession();
        Address addressDB = getByUserId(address.getUserId());
        Transaction transaction = session.beginTransaction();
        if (addressDB == null) {
            session.save(address);
        } else {
            address.setAddressId(addressDB.getAddressId());
            session.update(address);
        }
        transaction.commit();
    }

    @Override
    public Address getByUserId(int userId) {
        Session session = sessionFactory.openSession();
        NativeQuery<Address> nativeQuery = session
                .createNativeQuery("select * from address where user_id = ?", Address.class);

        nativeQuery.setParameter(1, userId);
        return nativeQuery.uniqueResult();
    }

    @Override
    public void deleteAddress(Address address) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(address);
        transaction.commit();
    }
}
