package edu.nota;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class notaDAOimp implements notaDAO{
	private static final String URL = "jdbc:mariadb://localhost:3306/escola";
	private static final String USER = "root";
	private static final String SENHA = "alunofatec";
	private Connection con;
	
	public notaDAOimp() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, SENHA);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void criar(EntidadeNota n) {
		String sql = "INSERT INTO nota (tcc_nome, nota, motivo) VALUES ('" + n.getNomeTcc() + "', " + n.getNotaTcc() + ", '" +n.getMotivoTcc() +"')"; 
		try {
			PreparedStatement prtmt = con.prepareStatement(sql);
			prtmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void editar(String nome, EntidadeNota n) {
		String sql = "UPDATE nota SET tcc_nome = '" + n.getNomeTcc() + "', nota = " + n.getNotaTcc() + ", motivo = '" +n.getMotivoTcc() +"' WHERE tcc_nome = '" + nome + "'"; 
		try {
			PreparedStatement prtmt = con.prepareStatement(sql);
			prtmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
	}

	@Override
	public void apagar(String nome) {
		String sql = "DELETE FROM nota WHERE tcc_nome = '" + nome + "'"; 
		try {
			PreparedStatement prtmt = con.prepareStatement(sql);
			prtmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		
	}

	@Override
	public List<EntidadeNota> pesquisarNome(String nome) {
		List<EntidadeNota> lista = new ArrayList<>();
		String sql = "SELECT * FROM nota WHERE tcc_nome LIKE '%" + nome + "%'";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				EntidadeNota n = new EntidadeNota();
				n.setNomeTcc(rs.getString("tcc_nome"));
				n.setNotaTCC(rs.getDouble("nota"));
				n.setMotivoTcc(rs.getString("motivo"));
				lista.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
