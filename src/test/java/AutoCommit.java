import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.asserts.SoftAssert;

import java.sql.*;

public class AutoCommit {

    public static void executeWithAutoCommitFalse()  {
        String sqlUpdate = "INSERT INTO studentsInfo(id, firstName, lastName,phone)" +
                " VALUES ('1004', 'mariami', 'tetradze', '598828282')";

        try (
                Connection conn = SQLJDBCUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {
                conn.setAutoCommit(false);
            int rowAffected = pstmt.executeUpdate();
            SoftAssert softAssertion= new SoftAssert();
            String query = "select *  from studentsInfo;";
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery(query);
            while (rs.next()) {
                softAssertion.assertTrue(rs.getString("firstName").contains("mariami"));
            }
            conn.commit();
            softAssertion.assertEquals(rowAffected, 1);
            softAssertion.assertAll();

        } catch (
                SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        executeWithAutoCommitFalse();
    }

}
