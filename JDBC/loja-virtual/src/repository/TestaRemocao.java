package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {

    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        Connection connection = connectionFactory.getConnection();

        PreparedStatement stmt = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID > ?");

        stmt.setInt(1, 2);

        stmt.execute();

        Integer linhasModificadas = stmt.getUpdateCount();

        System.out.println(linhasModificadas + " Linhas afetadas");

    }

}
