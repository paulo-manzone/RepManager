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
import model.Responsavel;

public class ResponsavelDao {
private Connection con;
	
	
	//construtor inicia conexão
	public ResponsavelDao() {
		this.con = new ConnectionFactory().getConnection();
	}

	
	//===========================================================================================
	//												 ADICIONAR
	//===========================================================================================
	
	public void adicionar(Responsavel responsavel) {
		String sql = "insert into responsavel (cpf, numTarefa, data) values (?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, responsavel.getCpf());
			stmt.setInt(2, responsavel.getNumTarefa());	
			stmt.setDate(3, new Date(responsavel.getData().getTimeInMillis()));
			stmt.execute();
			stmt.close();
			con.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: "+ e.getMessage());
		}
	}
	
	//===========================================================================================
	//												 LER para cada tarefa
	//===========================================================================================
	public List<Responsavel> ler(int numTarefa){
		List<Responsavel> responsaveis = new ArrayList<Responsavel>();
		try {
			
			PreparedStatement stmt = this.con.prepareStatement("select * from responsavel where numTarefa = ?");
			stmt.setInt(1, numTarefa);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Responsavel r = new Responsavel();
				r.setCpf(rs.getInt("cpf"));
				r.setNumTarefa(rs.getInt("numTarefa"));
				Calendar cal = Calendar.getInstance();
				cal.setTime(rs.getDate("data"));
				r.setData(cal);
				responsaveis.add(r);
			}
			rs.close();
			stmt.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: "+ e.getMessage());	
		}
		return responsaveis;
	}
	
	
	//===========================================================================================
	//												 EXCLUIR
	//===========================================================================================
		public void excluir(Responsavel r) {
			try {
				PreparedStatement stmt = this.con.prepareStatement("delete from responsavel where cpf = ?");
				stmt.setInt(1, r.getCpf());
				stmt.execute();
				stmt.close();
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro: "+ e.getMessage());	
			}
		}
}
