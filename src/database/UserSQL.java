package database;

import java.sql.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class UserSQL {

    private final String dbName = System.getenv("yetas_db_name");
    private final String dbUser = System.getenv("yetas_db_user");
    private final String dbPassword = System.getenv("yetas_db_password");

    public void create(UserModel user) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, dbUser, dbPassword);
            //here sonoo is database name, root is username and password
            final var stmt = con.createStatement();
            final var rs = stmt.executeQuery("insert into user values (1, 'yilmaz', 'mustafa', 'yilmaz@mail.be', '1234', '5678', true, 'STUDENT')");

            // TOOD: sonra yapılacak .. chef ile beraber

            con.close();


        } catch (SQLException | ClassNotFoundException exception) {
            System.out.println(exception);
        }
    }

    public List<UserModel> findAll() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, dbUser, dbPassword);
            //here sonoo is database name, root is username and password
            final var stmt = con.createStatement();
            final var rs = stmt.executeQuery("select * from user");

            final List<UserModel> results = mapUserModel(rs);

            con.close();

            return results;

        } catch (SQLException | ClassNotFoundException exception) {
            System.out.println(exception);
            return Collections.EMPTY_LIST;
        }
    }

    private List<UserModel> mapUserModel(ResultSet rs) throws SQLException {
        final var userModelList = new LinkedList<UserModel>();
        while (rs.next()) {
            final var userModel = new UserModel();

            userModel.setId(rs.getInt("id"));
            userModel.setFirstName(rs.getString("first_name"));
            userModel.setLastName(rs.getString("last_name"));
            userModel.setEmail(rs.getString("email"));
            userModel.setPassword(rs.getString("password"));
            userModel.setAuthenticated(rs.getBoolean("authenticated"));
            userModel.setActivation(rs.getString("activation"));
            userModel.setRole(rs.getString("role"));

            userModelList.add(userModel);
        }
        return userModelList;
    }
}
