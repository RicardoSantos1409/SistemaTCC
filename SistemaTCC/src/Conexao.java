import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexao {
	private static final String URL = "jdbc:mariadb://localhost:3306/escola";
	private static final String USER = "root";
	private static final String SENHA = "123456";
	private Connection con;

	public Conexao() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, SENHA);
			System.out.println("Conectando ao banco de dados");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void criar() {
		String sql = "INSERT INTO evento " +
					"(titulo, descricao, data)" +
					"VALUES";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
