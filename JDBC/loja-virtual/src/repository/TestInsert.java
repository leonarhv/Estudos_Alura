package repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestInsert {

    public static void main(String[] args) throws SQLException {
        Connection con = new ConnectionFactory().getConnection();

        Statement stmt = con.createStatement();
        stmt.execute("INSERT INTO PRODUTO (nome, descricao) VALUES ('MOUSE', 'MOUSE GAMER HYPERX')", Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = stmt.getGeneratedKeys();
        while(rs.next()) {
            int id = rs.getInt(1);
            System.out.println("ID gerado: " + id);
        }

    }

}
