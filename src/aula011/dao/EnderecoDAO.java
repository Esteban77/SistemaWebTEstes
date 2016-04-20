package aula011.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import aula011.entity.Endereco;
import aula10.TConectionFactory;

public class EnderecoDAO {
	
	
	
	public static void main(String[] a){
		//Apenas para testes!
		
		EnderecoDAO dao = new EnderecoDAO(connection);
		
//		Endereco endereco = new Endereco();
//		endereco.setId(1);
//		endereco.setBairro("Coqueiros");
//		endereco.setRua("Outra rua");
//		endereco.setNumero(150);
		
		//int idGerado = dao.incluir(endereco);
		
		//dao.atualizar(endereco);
		dao.listarEndereco();
		
	}
	private static Connection connection;

	public EnderecoDAO(Connection conn){
		this.connection = conn;
	}

	public int incluir(Endereco endereco) {
		
		int idInserido = 0;
		String sql = "INSERT INTO Endereco (rua, numero, bairro) VALUES (?, ?, ?)";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			// seta os valores
			stmt.setString(1, endereco.getRua());
			stmt.setInt(2, endereco.getNumero());
			stmt.setString(3, endereco.getBairro());
			
			// executa
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();  
		    
			if(rs.next()){
		    	idInserido = rs.getInt(1);
		    }  
		    
			if(idInserido > 0){
				System.out.println("Endereco inserido com sucesso no BD!");
			}else{
				System.out.println("Erro ao inserir Endereco no BD!");
			}
			
			stmt.close();
			
			return idInserido;
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Endereco no BD!");
			throw new RuntimeException(e);
		}finally {
			
		}
	}

	public boolean atualizar(Endereco endereco) {

		boolean atualizadoSucesso = false;
		String sql = "UPDATE Endereco SET rua = ?, numero = ?, bairro = ? WHERE id = ?";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1, endereco.getRua());
			stmt.setInt(2, endereco.getNumero());
			stmt.setString(3, endereco.getBairro());
			stmt.setInt(4, endereco.getId());
			
			// executa
			int ok = stmt.executeUpdate();
			
			if(ok == 1){ //1 - Sucesso no update
				System.out.println("Endereço atualizado com sucesso no BD!");
				atualizadoSucesso = true;
			}else{
				System.out.println("Erro ao atualizar Endereco no BD!");
			}
			
			stmt.close();
			
			return atualizadoSucesso;
			
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Endereco no BD!");
			throw new RuntimeException(e);
		}
		finally {
			
		}
	}

	public boolean remover(int cdPessoa) {
		
		boolean removidoSucesso = false;
		String sql = "DELETE FROM Endereco WHERE id IN (SELECT p.endereco_id from Pessoa p WHERE p.id = ?)";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setInt(1, cdPessoa);
			
			// executa
			int ok = stmt.executeUpdate();
			if(ok == 1){
				System.out.println("Endereço removido com sucesso no BD!");
				removidoSucesso = true;
			}else{
				System.out.println("Erro ao remover Endereco no BD!");
			}
			
			stmt.close();
			
			return removidoSucesso;
		} catch (SQLException e) {
			System.out.println("Erro ao remover Endereco no BD!");
			throw new RuntimeException(e);
		}
	}
	
	public Endereco obterEndereco(int cdPessoa) {
		Endereco endereco = null;
		String sql = "SELECT e.* FROM Endereco e INNER JOIN Pessoa p ON e.id = p.endereco_id WHERE p.id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, cdPessoa);
			stmt.setMaxRows(1);
			
			// executa
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				endereco = new Endereco();
				endereco.setId(rs.getInt("id"));
				endereco.setRua(rs.getString("rua"));
				endereco.setNumero(rs.getInt("numero"));
				endereco.setBairro(rs.getString("bairro"));
				System.out.println("ENDERECO: " + endereco.getId() + " :: " + endereco.getRua());
			}
			
			stmt.close();
			return endereco;
		} catch (SQLException e) {
			System.out.println("Erro ao buscar endereco no BD!");
			return null;
		}finally {
			
		}
	}
	
	public List<Endereco> listarEndereco() {

		List<Endereco> enderecos = new ArrayList<Endereco>();

		String sql = "SELECT * FROM Endereco";
		try {
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// executa
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { //Percorrer todos os resultados e criar enderecos
				Endereco endereco = new Endereco();
				endereco.setId(rs.getInt("id"));
				endereco.setRua(rs.getString("rua"));
				endereco.setNumero(rs.getInt("numero"));
				endereco.setBairro(rs.getString("bairro"));
				enderecos.add(endereco);
				System.out.println("Endereço: " + endereco.getId() + " :: "+ endereco.getBairro() +  " :: " + endereco.getRua() + "::" + endereco.getNumero());
			}
			stmt.close();
			
			return enderecos;
		} catch (SQLException e) {
			System.out.println("Erro ao buscar enderecos no BD!");
			return enderecos;
		}
		finally {
			
		}
	}
}
