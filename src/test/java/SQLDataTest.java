import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.Test;

import java.sql.*;

import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Selenide.*;

public class SQLDataTest {
    public SQLDataTest() {
        startMaximized=true;
    }

    @Test
    public void getDBValues() throws SQLException {
        open("https://demoqa.com/automation-practice-form");
        SelenideElement firstNameInput = $("#firstName");
        SelenideElement lastNameInput = $("#lastName");
        SelenideElement numberInput = $("#userNumber");

        Connection conn = SQLJDBCUtil.getConnection();
        String query = "select *  from studentsInfo;";
        Statement stmt = conn.createStatement();
        ResultSet rs= stmt.executeQuery(query);

       /* int rows = rs.getRow();
        rs.next();
        System.out.println(rs.getString(2));
        ResultSetMetaData rsmd = rs.getMetaData();
        int cols = rsmd.getColumnCount();
*/
        while (rs.next()) {
            firstNameInput.setValue(rs.getString("firstName"));
            lastNameInput.setValue(rs.getString("lastName"));
            numberInput.setValue(rs.getString("phone"));
        }

        }

    }
