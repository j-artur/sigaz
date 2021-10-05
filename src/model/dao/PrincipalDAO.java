package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.PrincipalVO;

public class PrincipalDAO extends BaseDAO implements IUserDAO<PrincipalVO> {
	public void create(PrincipalVO principal) {
		String sql = "INSERT INTO principals (name, email, password) VALUES (?, ?, ?)";

		try {
			PreparedStatement statement = this.getConnection().prepareStatement(sql);
			statement.setString(1, principal.getName());
			statement.setString(2, principal.getEmail());
			statement.setString(3, principal.getPassword());
			statement.execute();
		} catch (SQLException e) {
			System.out.println("Não foi possível salvar o diretor!");
			e.printStackTrace();
		}
	}

	public List<PrincipalVO> findAll() {
		String sql = "SELECT * FROM principals";
		List<PrincipalVO> principalList = new ArrayList<PrincipalVO>();

		try {
			Statement statement = this.getConnection().createStatement();
			ResultSet set = statement.executeQuery(sql);

			while (set.next()) {
				PrincipalVO principal = new PrincipalVO();
				principal.setId(set.getLong("id"));
				principal.setName(set.getString("name"));
				principal.setEmail(set.getString("email"));
				principal.setPassword(set.getString("password"));
				principalList.add(principal);
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível buscar os diretores!");
			e.printStackTrace();
		}

		return principalList;
	}

	public PrincipalVO findByEmail(PrincipalVO data) {
		String sql = "SELECT * FROM principals WHERE email = ?";
		PrincipalVO principal = null;

		try {
			PreparedStatement statement = this.getConnection().prepareStatement(sql);
			statement.setString(1, data.getEmail());
			ResultSet set = statement.executeQuery();

			if (set.next()) {
				principal = new PrincipalVO();
				principal.setId(set.getLong("id"));
				principal.setName(set.getString("name"));
				principal.setEmail(set.getString("email"));
				principal.setPassword(set.getString("password"));
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível buscar o diretor!");
			e.printStackTrace();
		}

		return principal;
	}

	public void update(PrincipalVO principal, PrincipalVO data) {
		String sql = "UPDATE principals SET name = ?, email = ?, password = ? WHERE id = ?";

		try {
			PreparedStatement statement = this.getConnection().prepareStatement(sql);
			statement.setString(1, data.getName());
			statement.setString(2, data.getEmail());
			statement.setString(3, data.getPassword());
			statement.setLong(6, principal.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Não foi possível alterar o diretor!");
			e.printStackTrace();
		}
	}

	public void delete(PrincipalVO principal) {
		String sql = "DELETE FROM principals WHERE id = ?";
		try {
			PreparedStatement statement = this.getConnection().prepareStatement(sql);
			statement.setLong(1, principal.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Não foi possível excluir o diretor!");
			e.printStackTrace();
		}
	}
}
