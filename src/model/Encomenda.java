package model;

public class Encomenda {
	
	private int encId;
	private int clienteId;
	private String data;
	private double total;
	
	public Encomenda(int encId, int clienteId, String data, double total) {
		this.encId = encId;
		this.clienteId = clienteId;
		this.data = data;
		this.total = total;
	}
	
	public int getEncId() {
		return encId;
	}
	public void setEncId(int encId) {
		this.encId = encId;
	}
	public int getClienteId() {
		return clienteId;
	}
	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	

}
