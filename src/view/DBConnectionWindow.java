package view;

import javax.swing.*;

import controller.EncomendasController;
import controller.ProdutosController;
import model.SQLServerConnection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DBConnectionWindow {

	public DBConnectionWindow() {

		// Criação da janela
		JFrame frame = new JFrame("Dados de Conexão à Base de Dados");
		frame.setSize(800, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Painel para os campos de input
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 2));

		// Valores Pre-definidos
		String url = "jdbc:sqlserver://192.168.100.14:1433;databaseName=SGBD_PL1_10;trustServerCertificate=true;";
		String user = "User_SGBD_PL1_10";
		String password = "diubi:2024!SGBD!PL1_10";

		// Campos de texto
		JTextField urlField = new JTextField(url);
		JTextField userField = new JTextField(user);
		JPasswordField passwordField = new JPasswordField(password);

		// Labels
		JLabel urlLabel = new JLabel("Connection String:");
		JLabel userLabel = new JLabel("User:");
		JLabel passwordLabel = new JLabel("Password:");

		// Botões
		JButton connectButton = new JButton("Conectar");
		JButton cancelButton = new JButton("Cancelar");

		// Adicionar os componentes ao painel
		panel.add(urlLabel);
		panel.add(urlField);
		panel.add(userLabel);
		panel.add(userField);
		panel.add(passwordLabel);
		panel.add(passwordField);
		panel.add(connectButton);
		panel.add(cancelButton);

		// Adicionar o painel à janela
		frame.add(panel);

		// Ação do botão "Conectar"
		connectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String url = urlField.getText();
				String user = userField.getText();
				String password = new String(passwordField.getPassword());

				// Aqui deves realizar a conexão com a BD usando os dados inseridos
				System.out.println("Conectar a: " + url);
				System.out.println("Utilizador: " + user);
				System.out.println("Palavra-passe: " + password);

				SQLServerConnection connection = SQLServerConnection.getConnection(url,user,password);
				
				EncomendasController ec = new EncomendasController(connection.getConnection());
				ProdutosController pc = new ProdutosController(connection.getConnection());
				
				frame.setVisible(false);
				
			}
		});

		// Ação do botão "Cancelar"
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // Fechar a janela
			}
		});

		// Central
		frame.setLocationRelativeTo(null);
		// Tornar a janela visível
		frame.setVisible(true);

	}
}
