package manager;

import model.db.DBConnectionProvider;
import model.User;
import model.UserType;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserManager {
    private Connection connection;

    public UserManager(){
        connection = DBConnectionProvider.getInstance().getConnection();

    }

    public void addUser(User user)  {
        String sql = "INSERT INTO user(name,surname,email,password,type,picture_url) VALUES(?,?,?,?,?,?)";

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getUserType().name());
            statement.setString(6,user.getPictureUrl());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public List<User> getAllUsers() {
        Statement statement1 = null;
        List<User> users = new LinkedList<>();
        try {
            statement1 = connection.createStatement();
            ResultSet resultSet = statement1.executeQuery("SELECT * FROM user");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setUserType(UserType.valueOf(resultSet.getString("type")));
                user.setPictureUrl(resultSet.getString("picture_url"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;


    }

    public User getUserByEmailAndPassword(String email, String password){
        String sql = "SELECT * FROM user where email=? and password=?";

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setUserType(UserType.valueOf(resultSet.getString("type")));
                user.setPictureUrl(resultSet.getString("picture_url"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }
public void deleteUserById(int id)  {
    PreparedStatement preparedStatement = null;
    try {
        preparedStatement = connection.prepareStatement("DELETE FROM user where id=" + id);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }

}

    public User getUserById(int id)  {
        String sql = "SELECT * FROM user where id=?";

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setUserType(UserType.valueOf(resultSet.getString("type")));
                user.setPictureUrl(resultSet.getString("picture_url"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

}

