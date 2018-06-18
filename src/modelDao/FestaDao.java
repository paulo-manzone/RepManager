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
import model.Festa;
import model.Tarefa;

public class FestaDao {
	
private Connection con;
	
	
	//construtor inicia conexão
	public FestaDao() {
		this.con = new ConnectionFactory().getConnection();
	}

	
	//===========================================================================================
	//												 ADICIONAR
	//===========================================================================================
	
	public void adicionar(Festa festa) {
		String sql = "insert into festa (numFesta, data, local) values (?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, festa.getNumFesta());
			stmt.setDate(2, new Date(festa.getData().getTimeInMillis()));
			stmt.setString(3, festa.getLocal());
			
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
	public List<Festa> ler(){
		List<Festa> festas = new ArrayList<Festa>();
		try {
			
			PreparedStatement stmt = this.con.prepareStatement("select * from festa");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(rs.getDate("data"));
				Festa f = new Festa();
				f.setNumFesta(rs.getInt("numFesta"));
				f.setData(cal);
				f.setLocal(rs.getString("local"));
				festas.add(f);
			}
			rs.close();
			stmt.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: "+ e.getMessage());	
		}
		return festas;
	}
	
	//===========================================================================================
	//												 ATUALIZAR
	//===========================================================================================
	public void atualizar(Festa f) {
		
		try {
			PreparedStatement stmt = this.con.prepareStatement("update festa set numFesta = ?, data=?, local=? where numFesta=?");
			stmt.setInt(1, f.getNumFesta());
			stmt.setDate(2,  new Date(f.getData().getTimeInMillis()));
			stmt.setString(3, f.getLocal());
			stmt.setInt(4, f.getNumFesta());
			stmt.execute();
			stmt.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: "+ e.getMessage());	
		}
	}
	//===========================================================================================
	//												 EXCLUIR
	//===========================================================================================
	public void excluir(Festa f) {
		try {
			PreparedStatement stmt = this.con.prepareStatement("delete from festa where numFesta = ?");
			stmt.setInt(1, f.getNumFesta());
			stmt.execute();
			stmt.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: "+ e.getMessage());	
		}
	}
	
	
}
