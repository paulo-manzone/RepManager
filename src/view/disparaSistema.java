package view;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import controller.TestaConexao;

public class disparaSistema {

	public static void main(String[] args) {

		try {
		//seta look and feel
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} 
		catch (UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(null,"Look and feel n�o � suportado");
		}
		catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Look and feel n�o encontrado");
		}
		catch (InstantiationException e) {
			JOptionPane.showMessageDialog(null,"Look and feel n�o foi instaciado corretamente");
		}
		catch (IllegalAccessException e) {
			JOptionPane.showMessageDialog(null,"Look and feel tentou acesso ilegal");
		}
		JOptionPane.showMessageDialog(null, "Bem vindo ao RepManager");
		try {
			new TestaConexao();
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Falha na conex�o");
		}
		new formMenu();

	}

}
