package edu.evento;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class eventoDAOimp implements eventoDAO{
	private static final String URL = "jdbc:mariadb://localhost:3306/escola";
	private static final String USER = "root";
	private static final String SENHA = "alunofatec";
	private Connection con;
	
	public eventoDAOimp () {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, SENHA);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void criar(EntidadeEvento t) {
		String sql = "INSERT INTO evento" +
					"(nome, descricao, data)" +
					"VALUES ('" + t.getEventoNome() +
					"', '" + t.getEventoDesc() +
					"', '" + t.getEventoDuracao() + "')";
		
		try {
			PreparedStatement prtmt = con.prepareStatement(sql);
			prtmt.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<EntidadeEvento> pesquisarNome(String nome) {
		List<EntidadeEvento> lista = new ArrayList<>();
		String sql = "SELECT * FROM evento WHERE nome LIKE '%" + nome + "%'";
		try {
			PreparedStatement prtmt = con.prepareStatement(sql);
			ResultSet rs = prtmt.executeQuery();
			while(rs.next()) {
				EntidadeEvento e = new EntidadeEvento();
				e.setEventoNome(rs.getString("nome"));
				e.setEventoDesc(rs.getString("descricao"));
				e.setEventoDuracao(rs.getString("data"));
				lista.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public void atualizar(String nomeAntigo, EntidadeEvento t) {
		String sql = "UPDATE evento" +
				" SET nome = '" + t.getEventoNome() +
				"',descricao  = '" + t.getEventoDesc() +
				"',data = '" + t.getEventoDuracao() + 
				"' WHERE nome =  '" + nomeAntigo + "'";
	
	try {
		PreparedStatement prtmt = con.prepareStatement(sql);
		prtmt.executeUpdate(); 
	} catch (SQLException e) {
		e.printStackTrace();
	}
		
	}

	@Override
	public void apagar(String nome) {
		String sql = "DELETE FROM evento" +
				" WHERE nome = '" + nome + "'";
	
	try {
		PreparedStatement prtmt = con.prepareStatement(sql);
		prtmt.executeUpdate(); 
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}

	@Override
	public void limpar() {
		String sql = "DELETE FROM evento";
	
	try {
		PreparedStatement prtmt = con.prepareStatement(sql);
		prtmt.executeUpdate(); 
	} catch (SQLException e) {
		e.printStackTrace();
	}
		
	}
	
	
}
