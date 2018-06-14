package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class cadastraMorador extends JFrame implements ActionListener{

	
	//componentes utilizado
	private JTextField txtCPF = new JTextField("CPF", 10);
	private JTextField txtNome = new JTextField("NOME", 20);
	private JTextField txtCurso = new JTextField("CURSO", 10);
	private JCheckBox chbVeterano = new JCheckBox("VETERANO");
	private JButton btnCadastra = new JButton("Cadastrar");
	private JButton btnAtualiza = new JButton("Atualizar");
	private JButton btnExclui = new JButton("Excluir");
	private JTable tblMoradores;
	
	
	public cadastraMorador() {
		//Mudando propriedades do JFrame
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(600, 250);
		this.setVisible(true); // deixa visível
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);
		this.setTitle("Cadastrar Morador");
		this.setResizable(false);
		
		//criando JTable
		TableModel dataModel = new AbstractTableModel() {
	          public int getColumnCount() { return 4; }
	          public int getRowCount() { return 10;}
	          public Object getValueAt(int row, int col) { return null; }
	      };
	      tblMoradores = new JTable(dataModel);
	      tblMoradores.getColumnModel().getColumn(0).setHeaderValue("CPF");
	      tblMoradores.getColumnModel().getColumn(1).setHeaderValue("Nome");
	      tblMoradores.getColumnModel().getColumn(2).setHeaderValue("Curso");
	      tblMoradores.getColumnModel().getColumn(3).setHeaderValue("Veterano");
	      
	      JScrollPane scrollpane = new JScrollPane(tblMoradores);
	      
	    //adicionando propriedades aos componentes
		btnCadastra.setSize(20,20);
		btnAtualiza.setSize(20,20);
		btnExclui.setSize(20,20);

			
			
		//adicionando listeners aos componentes
		btnCadastra.addActionListener(this);
		btnAtualiza.addActionListener(this);
		btnExclui.addActionListener(this);
			
		//adicionando componentes ao JFrame
		this.add(txtCPF);
		this.add(txtNome);
		this.add(txtCurso);
		this.add(chbVeterano);
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
