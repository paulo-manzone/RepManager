package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class cadastraEstadia extends JFrame implements ActionListener{
	
	//Variáveis necessáarias
	private String listaQuartos[];
	private String listaMoradores[];
	
	//componentes utilizados
	private JComboBox<String> cmbMorador;
	private JComboBox<String> cmbQuarto;
	private JButton btnCadastra = new JButton("Cadastrar");
	private JButton btnAtualiza = new JButton("Atualizar");
	private JButton btnExclui = new JButton("Excluir");
	private JTable tblRelacoes;
	
	public cadastraEstadia() {
		//Mudando propriedades do JFrame
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(600, 250);
		this.setVisible(true); // deixa visível
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);
		this.setTitle("Cadastrar Estadias");
		this.setResizable(false);
		
		//criando JTable
		TableModel dataModel = new AbstractTableModel() {
	          public int getColumnCount() { return 2; }
	          public int getRowCount() { return 10;}
	          public Object getValueAt(int row, int col) { return null; }
	      };
	      tblRelacoes = new JTable(dataModel);
	      tblRelacoes.getColumnModel().getColumn(0).setHeaderValue("Nome do Morador");
	      tblRelacoes.getColumnModel().getColumn(1).setHeaderValue("Nº do Quarto");

	      JScrollPane scrollpane = new JScrollPane(tblRelacoes);
			      
	      //adicionando propriedades aos componentes
	      btnCadastra.setSize(20,20);
	      btnAtualiza.setSize(20,20);
	      btnExclui.setSize(20,20);
						
						
	      //adicionando listeners aos componentes
	      btnCadastra.addActionListener(this);
	      btnAtualiza.addActionListener(this);
	      btnExclui.addActionListener(this);
	      
	    //adicionando valores aos combobox
		  //listaQuartos[0] = "Quartos";
		  //listaMoradores[0] = "Moradores";
		  cmbMorador = new JComboBox<String>();
		  cmbQuarto = new JComboBox<String>();
		  
						
	      //adicionando componentes ao JFrame
	      this.add(cmbQuarto);
	      this.add(cmbMorador);
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
