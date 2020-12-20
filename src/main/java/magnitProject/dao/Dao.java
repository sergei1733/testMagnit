package magnitProject.dao;

import magnitProject.config.Config;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Dao {
    private static final String SET_INSERT = "INSERT INTO test (field) VALUES (?)";
    private static final String DELETE_DATA = "TRUNCATE TABLE test";
    private static final String GET_DATA = "SELECT * FROM test";
    private static Logger log = Logger.getLogger(Dao.class.getName());

    private Connection getConnection() throws SQLException {

        Connection con = DriverManager.getConnection(
                Config.getProperties(Config.DB_URL),
                Config.getProperties(Config.DB_LOGIN),
                Config.getProperties(Config.DB_PASSWORD));

        return con;

    }

    public void setData(Integer numberRecords) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement stmt1 = con.prepareStatement(DELETE_DATA);
             PreparedStatement stmt = con.prepareStatement(SET_INSERT)) {

            stmt1.executeUpdate();
            for (int i = 1; i <= numberRecords; i++) {
                stmt.setInt(1, i);
                stmt.executeUpdate();
            }
        } catch (SQLException ex) {
            log.info("Error writing data from database");
            throw ex;
        }
    }

    public List getData() throws Exception {
        Long field;
        List<Long> list = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_DATA)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                field = rs.getLong("field");
                list.add(field);
                System.out.println(field);
            }
        } catch (SQLException ex) {
            log.info("Error getting data from database");
            throw ex;
        }
        return list;
    }
}

