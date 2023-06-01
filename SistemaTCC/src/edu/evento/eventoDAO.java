package edu.evento;

import java.util.List;

public interface eventoDAO {
	void criar(EntidadeEvento t);
	List<EntidadeEvento> pesquisarNome(String nome);
	void apagar(String nome);
	void atualizar(String nomeAntigo, EntidadeEvento t);
	void limpar();
}
