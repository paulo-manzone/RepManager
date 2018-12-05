package modelDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.cj.protocol.Resultset;

import controller.OracleConnectionFactory;
import model.Tarefa;

public class TarefaDao {
	private Connection con;
	
	
	//construtor inicia conexão
	public TarefaDao() {
		this.con = new OracleConnectionFactory().getConnection();
	}

	
	//===========================================================================================
	//												 ADICIONAR
	//===========================================================================================
	
	public void adicionar(Tarefa tarefa) {
		JOptionPane.showMessageDialog(null,"1");
		String sql = "insert into tarefa (numTarefa, descricao) values (?,?)";
		JOptionPane.showMessageDialog(null,"2");
		PreparedStatement stmt;
		JOptionPane.showMessageDialog(null,"3");
		try {
			JOptionPane.showMessageDialog(null,"4");
			stmt = con.prepareStatement(sql);
			JOptionPane.showMessageDialog(null,"5");
			stmt.setInt(1, tarefa.getNumTarefa());
			
			stmt.setString(2, tarefa.getDescricao());
			JOptionPane.showMessageDialog(null,tarefa.getDescricao());
			JOptionPane.showMessageDialog(null,tarefa.getNumTarefa());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null,"8");
			stmt.close();
			JOptionPane.showMessageDialog(null,"9");
			con.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: "+ e.getMessage());
		}
	}
	
	//===========================================================================================
	//												 LER
	//===========================================================================================
	public List<Tarefa> ler(){
		List <Tarefa> tarefas = new ArrayList<Tarefa>();
		try {
			
			PreparedStatement stmt = this.con.prepareStatement("select * from tarefa");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Tarefa t = new Tarefa();
				t.setNumTarefa(rs.getInt("numTarefa"));
				t.setDescricao(rs.getString("descricao"));
				tarefas.add(t);
			}
			rs.close();
			stmt.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: "+ e.getMessage());	
		}
		return tarefas;
	}
	
	//===========================================================================================
	//												 ATUALIZAR
	//===========================================================================================
	public void atualizar(Tarefa t) {
		
		try {
			PreparedStatement stmt = this.con.prepareStatement("update tarefa set numTarefa = ?, descricao=? where numTarefa=?");
			stmt.setInt(1, t.getNumTarefa());
			stmt.setString(2, t.getDescricao());
			stmt.setInt(3, t.getNumTarefa());
			stmt.execute();
			stmt.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: "+ e.getMessage());	
		}
	}
	//===========================================================================================
	//												 EXCLUIR
	//===========================================================================================
	public void excluir(Tarefa t) {
		try {
			PreparedStatement stmt = this.con.prepareStatement("delete from tarefa where numTarefa = ?");
			stmt.setInt(1, t.getNumTarefa());
			stmt.execute();
			stmt.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: "+ e.getMessage());	
		}
	}
	
	
	
}
