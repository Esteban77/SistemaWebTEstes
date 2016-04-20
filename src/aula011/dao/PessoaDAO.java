package aula011.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import aula011.entity.Endereco;
import aula011.entity.Pessoa;
import aula10.TConectionFactory;

public class PessoaDAO {

	private Connection connection;

	public PessoaDAO(Connection conn){
		this.connection = conn;
	}

	public int incluir(Pessoa pessoa) {

		int idInserido = 0;
		int idEndereco = 0;
		String sql = "INSERT INTO Pessoa (nome, sobrenome, endereco_id) VALUES (?, ?, ?)";

		if(pessoa.getEndereco() != null){
			Endereco endereco = pessoa.getEndereco();
			EnderecoDAO enderecoDAO = new EnderecoDAO(connection);

			idEndereco = enderecoDAO.incluir(endereco);
			if(idEndereco == 0){
				return 0;
			}
		}

		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// seta os valores
			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getSobrenome());
			stmt.setInt(3, idEndereco);
			// executa
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();  

			if(rs.next()){
				idInserido = rs.getInt(1);
			}
			
			if(idInserido > 0){
				System.out.println("Pessoa inserida com sucesso no BD!");
			}else{
				System.out.println("Erro ao inserir Pessoa no BD!");
			}

			stmt.close();
			
			return idInserido;
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Pessoa no BD!");
			throw new RuntimeException(e);
		}
		finally {
			
		}
	}

	public boolean atualizar(Pessoa pessoa) {

		boolean atualizadoSucesso = false;
		String sql = "UPDATE Pessoa SET nome = ?, sobrenome = ?, endereco_id = ? WHERE id = ?";

		Endereco endereco = pessoa.getEndereco();
		EnderecoDAO enderecoDAO = new EnderecoDAO(connection);

		if(enderecoDAO.atualizar(endereco) == false){
			return false;
		}

		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getSobrenome());
			stmt.setInt(3, pessoa.getEndereco().getId());
			stmt.setInt(4, pessoa.getId());
			
			// executa
			int ok = stmt.executeUpdate();

			if(ok == 1){
				System.out.println("Pessoa atualizada com sucesso no BD!");
				atualizadoSucesso = true;
			}else{
				System.out.println("Erro ao atualizar Pessoa no BD!");
			}

			stmt.close();
			
			return atualizadoSucesso;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Pessoa no BD!");
			throw new RuntimeException(e);
		}
		finally {
			
		}
	}

	public boolean remover(int cdPessoa) {

		

		boolean removidoSucesso = false;
		String sql = "DELETE FROM Pessoa WHERE id = ?";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setInt(1, cdPessoa);
			
			// executa
			int ok = stmt.executeUpdate();

			if(ok == 1){
				System.out.println("Pessoa removida com sucesso no BD!");
				removidoSucesso = true;
			}else{
				System.out.println("Erro ao remover Pessoa no BD!");
			}
			EnderecoDAO enderecoDAO = new EnderecoDAO(connection);
			if(enderecoDAO.remover(cdPessoa) == false)
				return false;

			stmt.close();
			return removidoSucesso;
		} catch (SQLException e) {
			System.out.println("Erro ao remover Pessoa no BD!");
			throw new RuntimeException(e);
		}
	}

	public Pessoa obterPessoa(int cdPessoa) {
		Pessoa pessoa = null;

		String sql = "SELECT * FROM Pessoa WHERE id = ?";

		EnderecoDAO enderecoDAO = new EnderecoDAO(connection);
		Endereco endereco = enderecoDAO.obterEndereco(cdPessoa);
		System.out.println("ENDERECO: " + endereco.getId() + " :: " + endereco.getRua());
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setMaxRows(1);
			stmt.setInt(1, cdPessoa);
			// executa
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				pessoa = new Pessoa();
				pessoa.setId(rs.getInt("id"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setSobrenome(rs.getString("sobrenome"));
				pessoa.setEndereco(endereco);
				System.out.println("PESSOA: " + pessoa.getId() + " :: " + pessoa.getEndereco().getRua());
			}
			
			stmt.close();
			return pessoa;
			
		} catch (SQLException e) {
			System.out.println("Erro ao buscar pessoas no BD!");
			e.printStackTrace();
			return null;
		}finally {
			
		}
	}

	public List<Pessoa> listarPessoas() {

		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		EnderecoDAO enderecoDAO;
		String sql = "SELECT * FROM Pessoa";
		try {

			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// executa
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				enderecoDAO = new EnderecoDAO(connection);
				Pessoa pessoa = new Pessoa();
				pessoa.setId(rs.getInt("id"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setSobrenome(rs.getString("sobrenome"));
				pessoa.setEndereco(enderecoDAO.obterEndereco(pessoa.getId()));
				pessoas.add(pessoa);
				System.out.println("PESSOA: " + pessoa.getId() + " :: " + pessoa.getEndereco().getRua());
			}
			
			stmt.close();
			
			return pessoas;
		} catch (SQLException e) {
			System.out.println("Erro ao buscar pessoas no BD!");
			return pessoas;
		}
		finally {
			
		}
	}
}
