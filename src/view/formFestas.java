package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import sun.util.calendar.LocalGregorianCalendar.Date;

public class formFestas extends JFrame implements ActionListener{
	
	//variáveis necessárias
	private String[] listaDias;
	private String[] listaMeses;
	private String[] listaAnos;

	//componentes utilzados
	private JTextField txtNFesta = new JTextField("Nº Festa", 8);
	private JComboBox<String> cmbDia;
	private JComboBox<String> cmbMes;
	private JComboBox<String> cmbAno;
	private JTextField hora = new JTextField("Hora", 8);;
	private JTextField txtLocal = new JTextField("Local", 8);
	private JButton btnCadastra = new JButton("Cadastrar Festa");
	private JButton btnAtualiza = new JButton("Atualizar Festa");
	private JButton btnExclui = new JButton("Excluir Festa");
	private JButton btnSair = new JButton("Sair");
	private JTable tblFestas;
	
	
	public formFestas() {
		//Mudando propriedades do JFrame
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600, 450);
		this.setUndecorated(true); // tira bordas
		this.setVisible(true); // deixa visível
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);
		this.setTitle("Festas");
		this.setResizable(false);
		
		//criando JTable
		TableModel dataModel = new AbstractTableModel() {
	          public int getColumnCount() { return 6; }
	          public int getRowCount() { return 10;}
	          public Object getValueAt(int row, int col) { return null; }
	      };
	      tblFestas = new JTable(dataModel);
	      tblFestas.getColumnModel().getColumn(0).setHeaderValue("Nº Festa");
	      tblFestas.getColumnModel().getColumn(1).setHeaderValue("Dia");
	      tblFestas.getColumnModel().getColumn(2).setHeaderValue("Mes");
	      tblFestas.getColumnModel().getColumn(3).setHeaderValue("Ano");
	      tblFestas.getColumnModel().getColumn(4).setHeaderValue("Local");
	      tblFestas.getColumnModel().getColumn(5).setHeaderValue("Horario");
	      JScrollPane scrollpane = new JScrollPane(tblFestas);
	      
	      //adicionando propriedades aos componentes
	      btnCadastra.setSize(20,20);
	      btnAtualiza.setSize(20,20);
	      btnExclui.setSize(20,20);
	      btnSair.setSize(20,20);
	      
	      //adicionando dia mes ano nos cmbBox
	      /*
	      int i;
	      for(i = 1; i<=31; i++) {
	    	  listaDias[i] = Integer.toString(i);
	      }
	      for(i = 1; i<=12; i++) {
	    	  listaMeses[i] = Integer.toString(i);
	      }
	      for(i = 2018; i<=2030; i++) {
	    	  listaAnos[i] = Integer.toString(i);
	      }
	      
	      cmbDia = new JComboBox<>(listaDias);
	      cmbDia = new JComboBox<>(listaMeses);
	      cmbDia = new JComboBox<>(listaAnos);
			*/
			
		//adicionando listeners aos componentes
		btnCadastra.addActionListener(this);
		btnAtualiza.addActionListener(this);
		btnExclui.addActionListener(this);
		btnSair.addActionListener(this);
		
		//adicionando componentes ao JFrame
		this.add(btnCadastra);
		this.add(btnAtualiza);
		this.add(btnExclui);
		this.add(btnSair);
		this.add(scrollpane);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object alvo = e.getSource();
		if(alvo == btnSair) {
			this.dispose();
		}
		
	}

}
