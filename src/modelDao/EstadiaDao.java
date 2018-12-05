package modelDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import controller.ConnectionFactory;
import controller.OracleConnectionFactory;
import model.Estadia;

public class EstadiaDao {
private Connection con;
	
	


	//construtor inicia conex�o
	public EstadiaDao() {
		this.con = new OracleConnectionFactory().getConnection();
	}

	
	//===========================================================================================
	//												 ADICIONAR
	//===========================================================================================
	
	public void adicionar(Estadia estadia) {

		String sql = "insert into mora(morador, quarto, custo, data) values ((select ref(m) from morador m where m.cpf = ?), (select ref(q) from quarto q where q.NUMQUARTO =  ?),?,?)";

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
		}
	}
	
	//===========================================================================================
	//												 LER
	//===========================================================================================
	public List<Estadia> ler(){
		List<Estadia> estadias = new ArrayList<Estadia>();
		try {
			PreparedStatement stmt = this.con.prepareStatement("select m.morador.cpf as cpf, m.quarto.numQuarto as numQuarto, m.custo, m.data from mora m");
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
		}
		return estadias;
	}
	//===========================================================================================
	//												 COUNT de pessoas num quarto
	//===========================================================================================
				public int ler(int numQuarto){
					int res = 0;
					try {
						PreparedStatement stmt = this.con.prepareStatement("select count(*) as qtd from mora m where m.quarto.numQuarto=?");
						stmt.setInt(1, numQuarto);
						ResultSet rs = stmt.executeQuery();
						while(rs.next()) {
							res = rs.getInt("qtd");
						}
						rs.close();
						stmt.close();
					}catch (Exception e) {
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
		}
	}
	
	//===========================================================================================
	//												 EXCLUIR
	//===========================================================================================
		public void excluir(Estadia es) {
			try {
				PreparedStatement stmt = this.con.prepareStatement("delete from mora m where m.morador.cpf = ?");
				stmt.setInt(1, es.getCpf());
				stmt.execute();
				stmt.close();
			}catch (Exception e) {
			}
		}
}
