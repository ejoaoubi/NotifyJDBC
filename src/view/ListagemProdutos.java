package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Produto;

public class ListagemProdutos {

	static DefaultTableModel modeloTabela;

	public ListagemProdutos() {

		JFrame frame = new JFrame("Lista de Produtos");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 400);

		// Criar a tabela e adicionar ao JFrame
		criarTabelaEncomendas(frame);

		// Tornar a janela visível
		frame.setVisible(true);
	}

	private static void criarTabelaEncomendas(JFrame frame) {
		// Definir colunas da tabela
		String[] colunas = { "ProdutoId", "Designacao", "Preco", "QtdStock", "QtdEncomendado" };

		// Criar o modelo da tabela
		modeloTabela = new DefaultTableModel(colunas, 0);

		// Criar a tabela com o modelo carregado
		JTable tabela = new JTable(modeloTabela);

		// Adicionar a tabela a um painel de rolagem
		JScrollPane painelRolagem = new JScrollPane(tabela);

		// Adicionar o painel de rolagem ao JFrame
		frame.add(painelRolagem);
	}

	public void addLinha(Produto p) {
		Object[] novaLinha = { p.getProdutoId(), p.getDesignacao(), p.getPreco(),
				p.getQtdStock(), p.getQtdEncomendado() };
		modeloTabela.addRow(novaLinha);
	}

}
