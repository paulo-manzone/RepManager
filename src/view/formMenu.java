package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.scene.layout.Border;

public class formMenu extends JFrame implements ActionListener {

	private static final long serialVersionUID = 7873173409182286284L;
	
	//Componentes utilizados
	private JButton btnTarefas = new JButton("Tarefas");
	private JButton btnEstadia = new JButton("Estadia");
	private JButton btnFestas = new JButton("Festas");
	private JButton btnSair = new JButton("Sair");
	private JButton Titulo = new JButton("# RepManager #");
	
	
	public formMenu(){
		//Mudando propriedades do JFrame
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 600);
		//this.setUndecorated(true); // tira bordas
		this.setVisible(true); // deixa visível
		this.setLayout(new GridLayout(5, 1, 10, 10));
		this.setLocationRelativeTo(null);
		this.setTitle("RepManager v0.1");
		this.setResizable(false);
		
		//adicionando componentes ao JFrame
		this.add(Titulo);
		this.add(btnEstadia);
		this.add(btnFestas);
		this.add(btnTarefas);
		this.add(btnSair);

		//setando propriedades dos componentes
		btnSair.setBackground(Color.WHITE);
		btnSair.setBorder(BorderFactory.createBevelBorder(0));
		btnSair.setSize(100, 50);
		btnEstadia.setSize(100, 50);
		btnFestas.setSize(100, 50);
		btnTarefas.setSize(100, 50);
		Titulo.setEnabled(false);
		Titulo.setBackground(Color.GRAY);
		Titulo.setBorder(BorderFactory.createBevelBorder(1));

		//adicionando listeners aos componentes
		btnTarefas.addActionListener(this);
		btnSair.addActionListener(this);
		btnEstadia.addActionListener(this);
		btnFestas.addActionListener(this);
		
	}

	
	
	//Listeners
	public void actionPerformed(ActionEvent e) {
		Object alvo = e.getSource();
		
		if(alvo == btnTarefas) {
			new formTarefas();
		}
		if(alvo == btnSair) {
			this.dispose();
		}
		if(alvo == btnEstadia) {
			new formEstadia();
		}
		
		if(alvo == btnFestas) {
			new formFestas();
		}
		
		
	}
	
	
	
	
	
}
