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
import model.Estadia;

public class EstadiaDao {
private Connection con;
	
	
	//construtor inicia conex�o
	public EstadiaDao() {
		this.con = new ConnectionFactory().getConnection();
	}

	
	//===========================================================================================
	//												 ADICIONAR
	//===========================================================================================
	
	public void adicionar(Estadia estadia) {
		String sql = "insert into mora (cpf, numQuarto, custo, data) values (?,?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, estadia.getCpf());
			stmt.setInt(2, estadia.getNumQuarto());	
			stmt.setDouble(3, estadia.getCusto());
			stmt.setDate(4, new Date(estadia.getData().getTimeInMillis()));
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
	public List<Estadia> ler(){
		List<Estadia> estadias = new ArrayList<Estadia>();
		try {
			
			PreparedStatement stmt = this.con.prepareStatement("select * from mora");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Estadia es = new Estadia();
				es.setCpf(rs.getInt("cpf"));
				es.setNumQuarto(rs.getInt("numQuarto"));
				es.setCusto(rs.getDouble("custo"));
				Calendar cal = Calendar.getInstance();
				cal.setTime(rs.getDate("data"));
				es.setData(cal);
				estadias.add(es);
			}
			rs.close();
			stmt.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: "+ e.getMessage());	
		}
		return estadias;
	}
	//===========================================================================================
	//												 COUNT de pessoas num quarto
	//===========================================================================================
				public int ler(int numQuarto){
					int res = 0;
					try {
						PreparedStatement stmt = this.con.prepareStatement("select count(*) as qtd from mora where numQuarto=?");
						stmt.setInt(1, numQuarto);
						ResultSet rs = stmt.executeQuery();
						while(rs.next()) {
							res = rs.getInt("qtd");
						}
						rs.close();
						stmt.close();
					}catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Erro: "+ e.getMessage());	
					}
					return res;
				}
	
	//===========================================================================================
	//												 ATUALIZAR
	//===========================================================================================
	public void atualizar(Estadia es) {
		
		try {
			PreparedStatement stmt = this.con.prepareStatement("update mora set cpf=?, numQuarto=?, custo=?, data=? where cpf=?");
			stmt.setInt(1, es.getCpf());
			stmt.setInt(2, es.getNumQuarto());
			stmt.setDouble(3, es.getCusto());
			stmt.setDate(4, new Date(es.getData().getTimeInMillis()));
			stmt.setInt(5, es.getCpf());
			stmt.execute();
			stmt.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: "+ e.getMessage());	
		}
	}
	
	//===========================================================================================
	//												 EXCLUIR
	//===========================================================================================
		public void excluir(Estadia es) {
			try {
				PreparedStatement stmt = this.con.prepareStatement("delete from mora where cpf = ?");
				stmt.setInt(1, es.getCpf());
				stmt.execute();
				stmt.close();
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro: "+ e.getMessage());	
			}
		}
}
