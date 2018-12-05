package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import controller.CriadorBancoDadosOracle;
import controller.OracleConnectionFactory;
import controller.TestaConexao;

import model.Morador;
import modelDao.MoradorDao;

public class disparaSistema {

	public static void main(String[] args) {

		try {
		//seta look and feel
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} 
		catch (UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(null,"Look and feel não é suportado");
		}
		catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Look and feel não encontrado");
		}
		catch (InstantiationException e) {
			JOptionPane.showMessageDialog(null,"Look and feel não foi instaciado corretamente");
		}
		catch (IllegalAccessException e) {
			JOptionPane.showMessageDialog(null,"Look and feel tentou acesso ilegal");
		}
		JOptionPane.showMessageDialog(null, "Bem vindo ao RepManager");
		/*try {
			//new TestaConexao();
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Falha na conexão");
		}*/
		
		//Criar o banco  em si
		//new CriadorBancoDadosOracle();
		
		
		/*Testando o banco de dados oracle
		try {
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "123456");
			Statement st = con.createStatement();
			st.execute("CREATE or REPLACE TABLE arroz (id varchar(5))");
			con.close();
		} catch(SQLException e){
			JOptionPane.showMessageDialog(null,"Erro na conexão bro!");
		}*/
		//Fim teste===================
		/*
		Morador rodono = new Morador();
		rodono.setCpf(2928);
		rodono.setCurso("bcc");
		rodono.setNome("rodney");
		rodono.setVeterano(true);
		new MoradorDao().adicionar(rodono);
		*/
		new formMenu();
		
		
		

	}

}
