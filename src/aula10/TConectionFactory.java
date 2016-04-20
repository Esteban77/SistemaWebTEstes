package aula10;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class TConectionFactory {

	public Connection getconection(){
	String nomeB = "Test";
	String servidor = "jdbc:mysql://localhost/"+nomeB;
	String usuario = "root";
	String senha = "";
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexao = (Connection) DriverManager.getConnection(servidor,usuario,senha);
			System.out.println("Conexão aberta!");
			return conexao;
		} catch (SQLException|ClassNotFoundException e) {
			System.out.println("Erro ao obter conexão com o BD!");
			throw new RuntimeException(e);
		}
	
	}
	public static void fechaConexao(java.sql.Connection connection){
		try {
			connection.close();
			System.out.println("Conexão com o BD fechada!");
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
	}
}
