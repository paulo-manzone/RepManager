package view;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

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
		new formMenu();

	}

}
