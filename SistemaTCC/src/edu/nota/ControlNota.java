package edu.nota;

import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControlNota {
	private StringProperty tcc = new SimpleStringProperty();
	private DoubleProperty nota = new SimpleDoubleProperty();
	private StringProperty motivo = new SimpleStringProperty();
	
	private ObservableList<EntidadeNota> lista = FXCollections.observableArrayList();
	
	private notaDAO notaDAO = new notaDAOimp(); 
	
	private boolean editando = false;
	private String nomeAntigo = null;
	
	public EntidadeNota getEntity() {
		EntidadeNota n = new EntidadeNota();
		n.setNomeTcc(tcc.get());
		n.setNotaTCC(nota.get());
		n.setMotivoTcc(motivo.get());
		lista.addAll(n);
		return n;
	}
	
	public void editar() {
		this.editando = true;
		this.nomeAntigo = tcc.get();
	}
	
	public void adicionar() {
		EntidadeNota n = getEntity();
		if (this.editando) {
			notaDAO.editar(nomeAntigo, n);
		}else {
			notaDAO.criar(n);
		}
	}
	
	public void preecher() {
		for (EntidadeNota n : lista) {
			if(n.getNomeTcc().contains(tcc.get())) {
				tcc.set(n.getNomeTcc());
				nota.set(n.getNotaTcc());
				motivo.set(n.getMotivoTcc());
				break;
			}
		}
	}
	
	public void pesquisar() {
		List<EntidadeNota> tempLista = notaDAO.pesquisarNome(tcc.get());
		lista.clear();
		lista.addAll(tempLista);
		preecher();
	}
	
	public void limpar() {
		tcc.set("");
		nota.set(0);
		motivo.set("");
	}
	
	public void apagar() {
		notaDAO.apagar(tcc.get());
	}
	
	public ObservableList<EntidadeNota> getLista(){
		return lista;
	}
	
	public StringProperty tccStringProperty() {
		return tcc;
	}
	
	public DoubleProperty notaProperty() {
		return nota;
	}
	
	public StringProperty motivoProperty() {
		return motivo;
	}
}
