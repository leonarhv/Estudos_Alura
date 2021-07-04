package repository;

import java.sql.*;

public class TestaListagem {

    public static void main(String[] args) throws SQLException {
        Connection con = new ConnectionFactory().getConnection();

        Statement stmt = con.createStatement();

        stmt.execute("select * from PRODUTO");

        ResultSet resultSet = stmt.getResultSet();

        while(resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String nome = resultSet.getString("nome");
            String descricao = resultSet.getString("descricao");
            System.out.println(id + " - " + nome + " - " + descricao);
        }

        con.close();
    }

}
