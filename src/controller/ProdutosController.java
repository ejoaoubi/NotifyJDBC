package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Produto;
import view.ListagemProdutos;

public class ProdutosController {

	ListagemProdutos lp = new ListagemProdutos();
	
	public ProdutosController(Connection connection) {
		
		
        Statement statement = null;
		ResultSet resultSet = null;
		try {

            // Criar o Statement
            statement = connection.createStatement();

            // Executar e processar query para Produto
            String queryProduto = "SELECT * FROM Produto";
            resultSet = statement.executeQuery(queryProduto);
            System.out.println("\nTabela Produto:");
            while (resultSet.next()) {
                int produtoId = resultSet.getInt("ProdutoId");
                String designacao = resultSet.getString("Designacao");
                double preco = resultSet.getDouble("Preco");
                int qtdStock = resultSet.getInt("QtdStock");
                int qtdEncomendado = resultSet.getInt("QtdEncomendado");
                
                Produto p = new Produto(produtoId, designacao, preco, qtdStock, qtdEncomendado);
                lp.addLinha(p);

                System.out.println("ProdutoId: " + produtoId + ", Designacao: " + designacao + ", Preço: " + preco + ", QtdStock: " + qtdStock + ", QtdEncomendado: " + qtdEncomendado);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } 
		
			
	}
	

}