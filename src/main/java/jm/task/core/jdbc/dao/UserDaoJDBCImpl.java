package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection connection = Util.getConnection();
        String sql = "CREATE TABLE IF NOT EXISTS USERS(ID BIGINT(40) NOT NULL AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(40), LASTNAME VARCHAR(40), AGE TINYINT(40))";
        try {
            PreparedStatement  preparedStatement= connection.prepareStatement(sql);
            preparedStatement.executeUpdate(sql);
            preparedStatement.close();
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void dropUsersTable() {
        Connection connection = Util.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("Drop table if exists USERS");
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = Util.getConnection();
        try  {
          String sql  = "INSERT INTO USERS(name, lastname, age) VALUES(?,?,?)";
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        Connection connection = Util.getConnection();
        String sql = "DELETE FROM USERS where id";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate(sql);
            System.out.println("User удален");
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public List<User> getAllUsers() {
        Connection connection = Util.getConnection();
        List<User> userList = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age from USERS";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet res = preparedStatement.executeQuery(sql)) {
            while (res.next()) {
                String name = res.getString(2);
                String lastName = res.getString(3);
                byte age = res.getByte(4);
                User u = new User(name, lastName, age);
                userList.add(u);
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        Connection connection = Util.getConnection();
        String sql = "DELETE FROM USERS";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate(sql);
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось очистить");

        }
    }
}
