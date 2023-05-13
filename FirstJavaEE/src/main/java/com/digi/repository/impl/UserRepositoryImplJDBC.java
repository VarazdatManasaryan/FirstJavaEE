package com.digi.repository.impl;

import com.digi.enums.Status;
import com.digi.model.User;
import com.digi.repository.UserRepository;
import com.digi.util.MyDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImplJDBC implements UserRepository {

    @Override
    public void save(User user) {

        Connection connection = MyDataSource.getConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into users values (?,?,?,?,?,?,?,?)");

            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setString(7, user.getVerifyCode());
            preparedStatement.setString(8, user.getStatus().toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getByUsername(String email) {

        Connection connection = MyDataSource.getConnection();
        User user = null;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from users where email = ?");

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = resultSet.getInt("age");
                String password = resultSet.getString("password");
                String verificationCode = resultSet.getString("verification_code");
                String status = resultSet.getString("status");
                Status newStatus = status.equals("INACTIVE") ? Status.INACTIVE : Status.ACTIVE;
                user = new User(id, name, surname, age, email, password, verificationCode, newStatus);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void updateUser(User user) {

        Connection connection = MyDataSource.getConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update users set name = ?, " +
                            "surname = ?, " +
                            "age = ?, " +
                            "email = ?, " +
                            "password = ?, " +
                            "verification_code = ?, " +
                            "status = ? " +
                            "where id = ?");

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getVerifyCode());
            preparedStatement.setString(7, user.getStatus().toString());
            preparedStatement.setInt(8, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changePassword(String email, String password) {

        Connection connection = MyDataSource.getConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update users set password = ? where email = ?");

            preparedStatement.setString(1, password);
            preparedStatement.setString(2, email);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAccount(User user) {

        Connection connection = MyDataSource.getConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from users where id = ?");

            preparedStatement.setInt(1, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
