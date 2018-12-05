package modelDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import controller.ConnectionFactory;
import controller.OracleConnectionFactory;
import model.Morador;

public class MoradorDao {
private Connection con;
	
	
	//construtor inicia conexão
	public MoradorDao() {
		this.con = new OracleConnectionFactory().getConnection();
	}

	
	//===========================================================================================
	//												 ADICIONAR
	//===========================================================================================
	
	public void adicionar(Morador morador) {
		String sql = "insert into morador (cpf, nome, curso, veterano) values (?,?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, morador.getCpf());
			stmt.setString(2, morador.getNome());
			stmt.setString(3, morador.getCurso());
			stmt.setInt(4, morador.getVeterano());
			//stmt.setNull(5, java.sql.Types.REF,"FESTA_NT");
			stmt.execute();
			stmt.close();
			con.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: "+ e.getMessage());
		}
	}
	
	//===========================================================================================
	//												 LER
	//===========================================================================================
	public List<Morador> ler(){
		List<Morador> moradores = new ArrayList<Morador>();
		try {
			
			PreparedStatement stmt = this.con.prepareStatement("select * from morador");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Morador m = new Morador();
				m.setCpf(rs.getInt("cpf"));
				m.setNome(rs.getString("nome"));
				m.setCurso(rs.getString("curso"));
				m.setVeterano(rs.getInt("veterano"));
				moradores.add(m);
			}
			rs.close();
			stmt.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: "+ e.getMessage());	
		}
		return moradores;
	}
	
	//===========================================================================================
	//												 ATUALIZAR
	//===========================================================================================
	public void atualizar(Morador m) {
		
		try {
			PreparedStatement stmt = this.con.prepareStatement("update morador set cpf = ?, nome=?, curso=?, veterano=? where cpf=?");
			stmt.setInt(1, m.getCpf());
			stmt.setString(2, m.getNome());
			stmt.setString(3, m.getCurso());
			stmt.setInt(4, m.getVeterano());
			stmt.setInt(5, m.getCpf());
			stmt.execute();
			stmt.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: "+ e.getMessage());	
		}
	}
	//===========================================================================================
	//												 EXCLUIR
	//===========================================================================================
	public void excluir(Morador m) {
		try {
			PreparedStatement stmt = this.con.prepareStatement("delete from Morador where cpf = ?");
			stmt.setInt(1, m.getCpf());
			stmt.execute();
			stmt.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: "+ e.getMessage());	
		}
	}
	
	//==========================================================================================
	//											LER POR CPF
	//==========================================================================================
	public Morador lerCpf(int cpf){
		Morador morador = new Morador();
		try {
			PreparedStatement stmt = this.con.prepareStatement("select * from morador where cpf=?");
			stmt.setInt(1, cpf);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Morador m = new Morador();
				m.setCpf(rs.getInt("cpf"));
				m.setNome(rs.getString("nome"));
				m.setCurso(rs.getString("curso"));
				m.setVeterano(rs.getInt("veterano"));
				morador = m;
			}
			rs.close();
			stmt.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: "+ e.getMessage());	
		}
		return morador;
	}
}
