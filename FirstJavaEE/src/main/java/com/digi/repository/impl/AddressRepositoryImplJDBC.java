package com.digi.repository.impl;

import com.digi.model.Address;
import com.digi.repository.AddressRepository;
import com.digi.util.MyDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressRepositoryImplJDBC implements AddressRepository {
    @Override
    public void saveAddress(Address address) {

        Address addressDB = getByUserId(address.getUserId());
        Connection connection = MyDataSource.getConnection();
        if (addressDB == null) {
            try {
                PreparedStatement preparedStatement = connection
                        .prepareStatement("insert into address values (?,?,?,?,?,?)");
                preparedStatement.setInt(1, address.getAddressId());
                preparedStatement.setString(2, address.getCountry());
                preparedStatement.setString(3, address.getCity());
                preparedStatement.setString(4, address.getStreet());
                preparedStatement.setString(5, address.getHome());
                preparedStatement.setInt(6, address.getUserId());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                PreparedStatement preparedStatement = connection
                        .prepareStatement("update address set country = ?, " +
                                "city = ?," +
                                "street = ?," +
                                "home = ?" +
                                "where user_id = ?");
                preparedStatement.setString(1,
                        address.getCountry().equals("") ? addressDB.getCountry() : address.getCountry());
                preparedStatement.setString(2,
                        address.getCity().equals("") ? addressDB.getCity() : address.getCity());
                preparedStatement.setString(3,
                        address.getStreet().equals("") ? addressDB.getStreet() : address.getStreet());
                preparedStatement.setString(4,
                        address.getHome().equals("") ? addressDB.getHome() : address.getHome());
                preparedStatement.setInt(5,
                        addressDB.getUserId());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Address getByUserId(int userId) {

        Connection connection = MyDataSource.getConnection();
        Address address = null;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from address where user_Id = ?");

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int addressId = resultSet.getInt("address_id");
                String country = resultSet.getString("country");
                String city = resultSet.getString("city");
                String street = resultSet.getString("street");
                String home = resultSet.getString("home");

                address = new Address(addressId, country, city, street, home, userId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return address;
    }

    @Override
    public void deleteAddress(Address address) {

        Connection connection = MyDataSource.getConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from address where user_id = ?");

            preparedStatement.setInt(1, address.getUserId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
