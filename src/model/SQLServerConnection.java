package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SQLServerConnection {
	// Singleton para criar apenas uma ligacao à BD
	private static SQLServerConnection singletonConnection;

	private SQLServerConnection(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;

		try {
			// Carregar o driver, devido a compatibilidade
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// Estabelecer a conexão
			this.connection = DriverManager.getConnection(url, user, password);
			System.out.println("Conexão estabelecida com sucesso!" + connection);

		} catch (ClassNotFoundException e) {
			System.out.println("Driver JDBC não encontrado!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Erro ao conectar com o SQL Server!");
			e.printStackTrace();
		}
	}

	public static synchronized SQLServerConnection getConnection(String url, String user, String password) {
		if (singletonConnection == null) {
			singletonConnection = new SQLServerConnection(url, user, password);
		}
		return singletonConnection;
	}

	private String url;
	private String user;
	private String password;

	private Connection connection = null;

	public Connection getConnection() {
		return connection;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Encomenda getData(Connection connection) {

		// Estabelecer a ligação à base de dados e carregar os dados
		try {
			// Consulta SQL para obter todas as encomendas
			String sql = "SELECT EncId, ClienteId, Data, Total FROM Encomenda";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			// Processar o resultado da consulta
			while (resultSet.next()) {
				int encId = resultSet.getInt("EncId");
				int clienteId = resultSet.getInt("ClienteId");
				Date data = resultSet.getDate("Data");
				double total = resultSet.getDouble("Total");

				// Adicionar a linha ao modelo da tabela
				return new Encomenda(encId, clienteId, data.toString(), total);
			}
		} catch (SQLException e) {
			JFrame frame = new JFrame("Erro");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(800, 400);
			frame.setVisible(true);
			JOptionPane.showMessageDialog(frame, "Erro ao carregar dados da base de dados: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
		return null;

	}

}
