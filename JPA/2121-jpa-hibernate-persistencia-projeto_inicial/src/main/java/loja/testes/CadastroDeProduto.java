package loja.testes;

import loja.Produto;
import loja.dao.CategoriaDao;
import loja.dao.ProdutoDao;
import loja.modelo.Categoria;
import loja.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {
    public static void main(String[] args) {
        cadastrarProduto();

        EntityManager entityManager = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(entityManager);
        BigDecimal preco = produtoDao.buscarPrecoDoProdutoPeloNome("Iphone");
        System.out.println(preco);

        List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("Celulares");

        todos.forEach(produto -> System.out.println(produto.getNome()));

    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("Celulares");

        Produto celular = new Produto("Iphone", "Celular da Apple", new BigDecimal("800"), celulares);

        EntityManager entityManager = JPAUtil.getEntityManager();

        ProdutoDao produtoDao = new ProdutoDao(entityManager);
        CategoriaDao categoriaDao = new CategoriaDao(entityManager);

        entityManager.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
