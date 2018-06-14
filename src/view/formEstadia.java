package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class formEstadia extends JFrame implements ActionListener{
	
	//Declaração de componentes
	private JButton btnMorador = new JButton("+ M");
	private JButton btnQuarto = new JButton("+ Q");
	private JButton btnRelacao = new JButton("+ R");
	private JButton btnFechar = new JButton("Fechar");
	private JTable relacao;
	
	public formEstadia() {
		//Mudando propriedades do JFrame
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600, 450);
		this.setUndecorated(true); // tira bordas
		this.setVisible(true); // deixa visível
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);
		this.setTitle("Estadia");
		this.setResizable(false);
		
		//criando JTable
		TableModel dataModel = new AbstractTableModel() {
	          public int getColumnCount() { return 5; }
	          public int getRowCount() { return 10;}
	          public Object getValueAt(int row, int col) { return null; }
	      };
	      relacao = new JTable(dataModel);
	      relacao.getColumnModel().getColumn(0).setHeaderValue("CPF");
	      relacao.getColumnModel().getColumn(1).setHeaderValue("NumQuarto");
	      relacao.getColumnModel().getColumn(2).setHeaderValue("Custo");
	      relacao.getColumnModel().getColumn(3).setHeaderValue("Mes");
	      relacao.getColumnModel().getColumn(4).setHeaderValue("Ano");
	      JScrollPane scrollpane = new JScrollPane(relacao);
		
		//adicionando propriedades aos componentes
		btnMorador.setSize(20,20);
		btnQuarto.setSize(20,20);
		btnRelacao.setSize(20,20);
		btnFechar.setSize(20,20);
		
		
		//adicionando listeners aos componentes
		btnMorador.addActionListener(this);
		btnQuarto.addActionListener(this);
		btnRelacao.addActionListener(this);
		btnFechar.addActionListener(this);
		
		//adicionando componentes ao JFrame
		this.add(btnMorador);
		this.add(btnQuarto);
		this.add(btnRelacao);
		this.add(btnFechar);
		this.add(scrollpane);
		
	}

	//Listeners
	public void actionPerformed(ActionEvent e) {
		Object alvo = e.getSource();
		if(alvo == btnFechar) {
			this.dispose();
		}
		if(alvo == btnMorador) {
			new cadastraMorador();
		}
		if(alvo == btnQuarto) {
			new cadastraQuarto();
		}
		
		if(alvo == btnRelacao) {
			new cadastraEstadia();
		}
		
	}

}
