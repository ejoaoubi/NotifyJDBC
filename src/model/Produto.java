package model;

public class Produto {
	
	private int produtoId;
	private String designacao;
	private double preco;
	private int qtdStock;
	private int qtdEncomendado;
	public int getProdutoId() {
		return produtoId;
	}
	public void setProdutoId(int produtoId) {
		this.produtoId = produtoId;
	}
	public String getDesignacao() {
		return designacao;
	}
	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public int getQtdStock() {
		return qtdStock;
	}
	public void setQtdStock(int qtdStock) {
		this.qtdStock = qtdStock;
	}
	public int getQtdEncomendado() {
		return qtdEncomendado;
	}
	public void setQtdEncomendado(int qtdEncomendado) {
		this.qtdEncomendado = qtdEncomendado;
	}
	
	
	public Produto(int produtoId, String designacao, double preco, int qtdStock, int qtdEncomendado) {
		this.produtoId = produtoId;
		this.designacao = designacao;
		this.preco = preco;
		this.qtdStock = qtdStock;
		this.qtdEncomendado = qtdEncomendado;
	}
	
	
	
	
}
