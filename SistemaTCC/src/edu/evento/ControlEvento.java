package edu.evento;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControlEvento {
	
	private ObservableList<EntidadeEvento> lista = FXCollections.observableArrayList(); 
	
//	private List<EntidadeTarefa> lista = new ArrayList<>();
	
	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty desc = new SimpleStringProperty("");
	private StringProperty duracao = new SimpleStringProperty("");
	
	private eventoDAO eventoDAO = new eventoDAOimp();
	
	private Boolean editando = false;
	private String nomeAntigo = null;
	
	public EntidadeEvento getEntity() {
		EntidadeEvento t = new EntidadeEvento();
		t.setEventoNome(nome.get());
		t.setEventoDesc(desc.get());
		t.setEventoDuracao(duracao.get());
		lista.addAll(t);
		return t;
	}
	
	public void editar() {
		this.editando = true;
		this.nomeAntigo = nome.get();
	}
	
	public void salvar() {
		EntidadeEvento t = getEntity();
		if (this.editando == true){
			eventoDAO.atualizar(nomeAntigo, t);
		}else {
			eventoDAO.criar(t);
		}
	}
	
	public void preecher() {
		for (EntidadeEvento e : lista) {
			if (e.getEventoNome().contains(nome.get())) { 
				nome.set(e.getEventoNome());
				desc.set(e.getEventoDesc());
				duracao.set(e.getEventoDuracao());
				break;
			}
		}
	}
	
	public void pesquisar() {
		List<EntidadeEvento> tempLista = eventoDAO.pesquisarNome(nome.get());
		lista.clear();
		lista.addAll(tempLista);
		preecher();
	}
	
	public void limpar() {
		eventoDAO.limpar();
	}
	
	public void apagar() {
		eventoDAO.apagar(nome.get());
	}
	
	public ObservableList<EntidadeEvento> getLista(){
		return lista;
	}
	
	public StringProperty nomeProperty() {
		return nome;
	}
	
	public StringProperty descProperty() {
		return desc;
	}
	
	public StringProperty duracaoProperty() {
		return duracao;
	}
}