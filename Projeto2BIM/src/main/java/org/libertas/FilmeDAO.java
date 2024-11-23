package org.libertas;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class FilmeDAO {
	private static LinkedList<Filme> lista = new LinkedList<Filme>();
	Conexao con = new Conexao();
	Retorno r = new Retorno();
	
	public Retorno salvar(Filme p) {
		if(p.getId() > 0) {
			return alterar(p);
		}else {
			return inserir(p);
		}
	}
	
	private void criaTabela() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS filme("
				+ "id INTEGER PRIMARY KEY AUTO_INCREMENT, "
				+ "titulo VARCHAR(255), "
				+ "duracao INTEGER, "
				+ "genero VARCHAR(255),"
				+ "diretor VARCHAR(255),"
				+ "distribuidor VARCHAR(255)"
				+ ");";
		PreparedStatement sta = con.getCon().prepareStatement(sql);
		sta.execute();
	}
	
	public Retorno inserir(Filme p) {
		
		try {
			criaTabela();
			String sql = "INSERT INTO filme (titulo, genero, duracao, diretor, distribuidor) VALUES "
					+ "(?, ?, ?, ?, ?);";
			
			PreparedStatement prep = con.getCon().prepareStatement(sql);
			prep.setString(1, p.getTitulo());
			prep.setString(2, p.getGenero());
			prep.setInt(3, p.getDuracao());
			prep.setString(4, p.getDiretor());
			prep.setString(5, p.getDistribuidor());
			
			prep.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			r.setSucesso(false);
		}finally {
			r.setMensagem((r.getSucesso() ? "Filme registrado com sucesso." : "Falha ao registrar filme."));
			con.desconecta();
		}
		return r;
	}
	
	public LinkedList<Filme> listar(String pesquisa) {
		lista = new LinkedList<Filme>();
		try {
			String sql = "SELECT * FROM filme WHERE titulo like ? ORDER BY titulo";
			PreparedStatement sta = con.getCon().prepareStatement(sql);
			sta.setString(1, "%"+pesquisa+"%");
			ResultSet res = sta.executeQuery();
			while(res.next()) {
				Filme p = new Filme();
				p.setId(res.getInt("id"));
				p.setTitulo(res.getString("titulo"));
				p.setGernero(res.getString("genero"));
				p.setDuracao(res.getInt("duracao"));
				p.setDiretor(res.getString("diretor"));
				p.setDistribuidor(res.getString("distribuidor"));
				lista.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.desconecta();
		return lista;
	}
	
	public Retorno alterar(Filme p) {
		try {
			String sql = "UPDATE filme SET titulo = ?, genero = ?, duracao = ?, diretor = ?, distribuidor = ? WHERE id = ?";
			
			PreparedStatement prep = con.getCon().prepareStatement(sql);
			prep.setString(1, p.getTitulo());
			prep.setString(2, p.getGenero());
			prep.setInt(3, p.getDuracao());
			prep.setString(4, p.getDiretor());
			prep.setString(5, p.getDistribuidor());
			prep.setInt(6, p.getId());
			
			prep.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			r.setSucesso(false);
		}finally {
			r.setMensagem((r.getSucesso() ? "Filme alterado com sucesso." : "Falha ao alterar filme."));
			con.desconecta();
		}
		return r;
	}
	
	public Retorno excluir(int id) {
		try {
			String sql = "DELETE FROM filme WHERE id = ?;";
			
			PreparedStatement prep = con.getCon().prepareStatement(sql);
			prep.setInt(1, id);
			
			prep.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			r.setSucesso(false);
		}finally {
			r.setMensagem((r.getSucesso() ? "Filme excluido com sucesso." : "Falha ao excluir filme."));
			con.desconecta();
		}
		return r;
	}
	
	public Filme consultar(int id) {
		Filme p = new Filme();
		try {
			String sql = "SELECT * FROM filme WHERE id = "+id+";";
			Statement sta = con.getCon().createStatement();
			ResultSet res = sta.executeQuery(sql);
			if(res.next()) {
				p.setId(res.getInt("id"));
				p.setTitulo(res.getString("titulo"));
				p.setGernero(res.getString("genero"));
				p.setDuracao(res.getInt("duracao"));
				p.setDiretor(res.getString("diretor"));
				p.setDistribuidor(res.getString("distribuidor"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.desconecta();
		return p;
	}
}
