package modelDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import controller.OracleConnectionFactory;
import model.Organizacao;

public class OrganizacaoDao {
private Connection con;
	
	
	//construtor inicia conexão
	public OrganizacaoDao() {
		this.con = new OracleConnectionFactory().getConnection();
	}

	
	//===========================================================================================
	//												 ADICIONAR
	//===========================================================================================
	
	public void adicionar(Organizacao o) {
		String sql = "insert into organizacao (cpf, numFesta) values (?,?)";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, o.getCpf());
			stmt.setInt(2, o.getNumFesta());	
			stmt.execute();
			stmt.close();
			con.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: "+ e.getMessage());
		}
	}
	
	//===========================================================================================
	//												 LER para cada festa
	//===========================================================================================
	public List<Organizacao> ler(int numFesta){
		List<Organizacao> organizadores = new ArrayList<Organizacao>();
		try {
			
			PreparedStatement stmt = this.con.prepareStatement("select * from organizacao where numFesta = ?");
			stmt.setInt(1, numFesta);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Organizacao o = new Organizacao();
				o.setCpf(rs.getInt("cpf"));
				o.setNumFesta(rs.getInt("numFesta"));
				organizadores.add(o);
			}
			rs.close();
			stmt.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: "+ e.getMessage());	
		}
		return organizadores;
	}
	
	
	//===========================================================================================
	//												 EXCLUIR
	//===========================================================================================
		public void excluir(Organizacao o) {
			try {
				PreparedStatement stmt = this.con.prepareStatement("delete from organizacao where cpf = ?");
				stmt.setInt(1, o.getCpf());
				stmt.execute();
				stmt.close();
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro: "+ e.getMessage());	
			}
		}
}
