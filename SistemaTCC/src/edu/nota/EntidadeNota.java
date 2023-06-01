package edu.nota;

public class EntidadeNota {
	private String nomeTcc = new String();
	private double notaTcc;
	private String motivoTcc = new String();
	
	public String getNomeTcc() {
		return nomeTcc;
	}
	
	public void setNomeTcc (String nomeTcc) {
		this.nomeTcc = nomeTcc;
	}
	
	public double getNotaTcc() {
		return notaTcc;
	}
	
	public void setNotaTCC (double notaTcc) {
		this.notaTcc = notaTcc;
	}
	
	public String getMotivoTcc() {
		return motivoTcc;
	}
	
	public void setMotivoTcc(String motivoTcc) {
		this.motivoTcc = motivoTcc;
	}
}
