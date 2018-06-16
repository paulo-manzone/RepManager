package controller;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class TestaConexao {

	public TestaConexao() throws SQLException {
		Connection con = new ConnectionFactory().getConnection();
		JOptionPane.showMessageDialog(null,"Conexao Aberta!");
		con.close();
	}
}
