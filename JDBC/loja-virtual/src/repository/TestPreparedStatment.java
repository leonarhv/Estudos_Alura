package repository;

import java.sql.*;

public class TestPreparedStatment {

    public static void main(String[] args) throws SQLException {
        Connection con = new ConnectionFactory().getConnection();

        con.setAutoCommit(false);

        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

            addProduct(stmt, "MOUSE", "MOUSE GAMER LOGITECH");
            addProduct(stmt, "Teclado", "TECLADO GAMER Logitech");

            con.commit();

            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ROLLBACK EXECUTADO");
            con.rollback();
            con.close();
        }
    }

    private static void addProduct(PreparedStatement stmt, String produto, String descricao) throws SQLException {

        stmt.setString(1, produto);
        stmt.setString(2, descricao);

        if (produto.equals("MOUSE")) {
            throw new RuntimeException("Não foi possivel adicionar o produto");
        }

        stmt.execute();

        ResultSet rs = stmt.getGeneratedKeys();
        while(rs.next()) {
            int id = rs.getInt(1);
            System.out.println("ID gerado: " + id);
        }

        rs.close();
    }

}
