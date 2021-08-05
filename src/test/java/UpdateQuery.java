import org.testng.Assert;

import java.sql.*;

public class UpdateQuery {
    public static void update() {

        try (
                Connection conn = SQLJDBCUtil.getConnection();
                Statement stmt = conn.createStatement();
        ) {
            String sql = "UPDATE studentsInfo " +
                    "SET firstName = 'tamari' WHERE id in (1004)";
            stmt.executeUpdate(sql);
            String query = "select *  from studentsInfo;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString("firstName"));
            }
          /*  rs.next();
                    Assert.assertTrue(rs.getString("firstName").contains("tamari"));
         */
        }

        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        update();

    }
}

