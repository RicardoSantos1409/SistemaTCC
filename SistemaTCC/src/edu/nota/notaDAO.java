package edu.nota;

import java.util.List;

public interface notaDAO {
	void criar (EntidadeNota n);
	List<EntidadeNota> pesquisarNome(String nome);
	void editar (String nome, EntidadeNota n);
	void apagar (String nome);
}
