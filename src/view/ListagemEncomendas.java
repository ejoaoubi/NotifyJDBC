package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Encomenda;

public class ListagemEncomendas {

	static DefaultTableModel modeloTabela;

	public ListagemEncomendas() {

		JFrame frame = new JFrame("Lista de Encomendas");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 400);

		// Criar a tabela e adicionar ao JFrame
		criarTabelaEncomendas(frame);
		frame.setLocationRelativeTo(null);
		// Tornar a janela visível
		frame.setVisible(true);
	}

	private static void criarTabelaEncomendas(JFrame frame) {
		// Definir colunas da tabela
		String[] colunas = { "EncId", "ClienteId", "Data", "Total" };

		// Criar o modelo da tabela
		modeloTabela = new DefaultTableModel(colunas, 0);

		// Criar a tabela com o modelo carregado
		JTable tabela = new JTable(modeloTabela);

		// Adicionar a tabela a um painel de rolagem
		JScrollPane painelRolagem = new JScrollPane(tabela);

		// Adicionar o painel de rolagem ao JFrame
		frame.add(painelRolagem);
	}

	public void addLinha(Encomenda e) {
		Object[] novaLinha = { e.getEncId(), e.getClienteId(), e.getData(),
				e.getTotal() };
		modeloTabela.addRow(novaLinha);
	}

}
