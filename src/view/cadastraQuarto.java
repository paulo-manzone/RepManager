package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class cadastraQuarto extends JFrame implements ActionListener{

	
	//componentes utilizados
	private JTextField txtNQuarto = new JTextField("Nº do Quarto",13);
	private JTextField txtCapacidade = new JTextField("Capacidade",10);
	private JButton btnCadastra = new JButton("Cadastrar");
	private JButton btnAtualiza = new JButton("Atualizar");
	private JButton btnExclui = new JButton("Excluir");
	private JTable tblQuartos;
	
	public cadastraQuarto() {
		//Mudando propriedades do JFrame
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(600, 250);
		this.setVisible(true); // deixa visível
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);
		this.setTitle("Cadastrar Quarto");
		this.setResizable(false);
		
		//criando JTable
		TableModel dataModel = new AbstractTableModel() {
	          public int getColumnCount() { return 2; }
	          public int getRowCount() { return 10;}
	          public Object getValueAt(int row, int col) { return null; }
	      };
	      tblQuartos = new JTable(dataModel);
	      tblQuartos.getColumnModel().getColumn(0).setHeaderValue("Nº do Quarto");
	      tblQuartos.getColumnModel().getColumn(1).setHeaderValue("Capacidade");
	      tblQuartos.getColumnModel().getColumn(0).setMaxWidth(100);

	      JScrollPane scrollpane = new JScrollPane(tblQuartos);
	      
	    //adicionando propriedades aos componentes
	    btnCadastra.setSize(20,20);
		btnAtualiza.setSize(20,20);
		btnExclui.setSize(20,20);

				
				
		//adicionando listeners aos componentes
		btnCadastra.addActionListener(this);
		btnAtualiza.addActionListener(this);
		btnExclui.addActionListener(this);
				
		//adicionando componentes ao JFrame
		this.add(txtNQuarto);
		this.add(txtCapacidade);
		this.add(btnCadastra);
		this.add(btnAtualiza);
		this.add(btnExclui);
		this.add(scrollpane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object alvo = e.getSource();
		if(alvo == btnCadastra) {
			
		}
		if(alvo == btnAtualiza) {
			
		}
		if(alvo == btnExclui) {
	
		}
	}
}
