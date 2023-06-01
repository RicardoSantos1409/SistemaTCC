package edu.evento;

public class EntidadeEvento {
	private String eventoNome = new String();
	private String eventoDesc = new String();
	private String eventoDuracao = new String();;
	
	public String getEventoNome() {
		return eventoNome;
	}
	public void setEventoNome(String eventoNome) {
		this.eventoNome = eventoNome;
	}
	
	public String getEventoDesc() {
		return eventoDesc;
	}
	
	public void setEventoDesc(String eventoNome) {
		this.eventoDesc = eventoNome;
	}
	
	public String getEventoDuracao() {
		return eventoDuracao;
	}
	
	public void setEventoDuracao(String eventoNome) {
		this.eventoDuracao = eventoNome;
	}
	
}
