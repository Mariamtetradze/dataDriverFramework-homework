import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Procedure {
    public static void getLastName(int id)throws SQLException {
        //
        String query = "{ call GetLastNameById(?) }";
        ResultSet rs;

        try (Connection conn = SQLJDBCUtil.getConnection();
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(
                        rs.getString("lastName"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException {
        getLastName(1001);
    }
}
