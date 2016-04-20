package aula10;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class TExemploJDBC {

	public static void main(String[] args) {
		new TExemploJDBC().conectaBanco();
		
	}
	
	public void conectaBanco(){
		String nomeB = "Test";
		String servidor = "jdbc:mysql://localhost/"+nomeB;
		String usuario = "root";
		String senha = "";
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexao = (Connection) DriverManager.getConnection(servidor,usuario,senha);
			System.out.println("Conexão aberta!");
		} catch (SQLException|ClassNotFoundException e) {
			System.out.println("Erro ao obter conexão com o BD!");
			throw new RuntimeException(e);
		}
		
		
		
	}
	
	

}
