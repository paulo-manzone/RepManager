package modelDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import controller.ConnectionFactory;
import model.Morador;
import model.Quarto;

public class QuartoDao {
private Connection con;
	
	
	//construtor inicia conexão
	public QuartoDao() {
		this.con = new ConnectionFactory().getConnection();
	}

	
	//===========================================================================================
	//												 ADICIONAR
	//===========================================================================================
	
	public void adicionar(Quarto quarto) {
		String sql = "insert into quarto (NumQuarto, capacidade) values (?,?)";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, quarto.getNumQuarto());
			stmt.setInt(2, quarto.getCapacidade());		
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
	public List<Quarto> ler(){
		List<Quarto> quartos = new ArrayList<Quarto>();
		try {
			
			PreparedStatement stmt = this.con.prepareStatement("select * from quarto");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Quarto q = new Quarto();
				q.setNumQuarto(rs.getInt("numQuarto"));
				q.setCapacidade(rs.getInt("capacidade"));
				quartos.add(q);
			}
			rs.close();
			stmt.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: "+ e.getMessage());	
		}
		return quartos;
	}
	
	//===========================================================================================
	//												 ATUALIZAR
	//===========================================================================================
	public void atualizar(Quarto q) {
		
		try {
			PreparedStatement stmt = this.con.prepareStatement("update Quarto set numQuarto=?, capacidade=? where numQuarto=?");
			stmt.setInt(1, q.getNumQuarto());
			stmt.setInt(2, q.getCapacidade());
			stmt.setInt(3, q.getNumQuarto());
			stmt.execute();
			stmt.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: "+ e.getMessage());	
		}
	}
	
	//===========================================================================================
		//												 EXCLUIR
		//===========================================================================================
		public void excluir(Quarto q) {
			try {
				PreparedStatement stmt = this.con.prepareStatement("delete from quarto where numQuarto = ?");
				stmt.setInt(1, q.getNumQuarto());
				stmt.execute();
				stmt.close();
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro: "+ e.getMessage());	
			}
		}

}
