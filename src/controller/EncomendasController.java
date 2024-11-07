package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Encomenda;
import view.ListagemEncomendas;

public class EncomendasController {

	ListagemEncomendas l1 = new ListagemEncomendas();

	public EncomendasController(Connection connection) {

		Statement statement = null;
		ResultSet resultSet = null;
		try {

			// Criar o Statement
			statement = connection.createStatement();

			// Executar e processar query para Encomenda
			String queryEncomenda = "SELECT * FROM Encomenda";
			resultSet = statement.executeQuery(queryEncomenda);
			System.out.println("Tabela Encomenda:");
			while (resultSet.next()) {

				int encId = resultSet.getInt("EncId");
				int clienteId = resultSet.getInt("ClienteId");
				String data = resultSet.getString("Data");
				double total = resultSet.getDouble("Total");
				Encomenda e = new Encomenda(encId, clienteId, data, total);
				l1.addLinha(e);
				System.out.println(
						"EncId: " + encId + ", ClienteId: " + clienteId + ", Data: " + data + ", Total: " + total);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
