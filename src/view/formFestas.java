package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.sun.xml.internal.txw2.output.TXWResult;

import model.Festa;
import model.Tarefa;
import modelDao.FestaDao;
import modelDao.TarefaDao;
import sun.util.calendar.LocalGregorianCalendar.Date;

public class formFestas extends JFrame implements ActionListener{
	
	//variáveis necessárias
	private String[] listaDias;
	private String[] listaMeses;
	private String[] listaAnos;

	//componentes utilzados
	private JTextField txtNFesta = new JTextField("Nº Festa", 8);
	private JTextField txtDia = new JTextField("Dia", 2);
	private JTextField txtMes = new JTextField("Mes", 2);
	private JTextField txtAno = new JTextField("Ano", 2);
	private JTextField txtHora = new JTextField("Hora", 8);;
	private JTextField txtLocal = new JTextField("Local", 8);
	private JButton btnCadastra = new JButton("Cadastrar Festa");
	private JButton btnAtualiza = new JButton("Atualizar Festa");
	private JButton btnExclui = new JButton("Excluir Festa");
	private JButton btnSair = new JButton("Sair");
	private JTable tblFestas;
	DefaultTableModel dataModel = new DefaultTableModel();
	
	
	public formFestas() {
		//Mudando propriedades do JFrame
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(600, 450);
		this.setUndecorated(true); // tira bordas
		this.setVisible(true); // deixa visível
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);
		this.setTitle("Festas");
		this.setResizable(false);
		
		//criando JTable
	      tblFestas = new JTable(dataModel);
	      dataModel.addColumn("nº festa");
	      dataModel.addColumn("data");
	      dataModel.addColumn("local");
	      tblFestas.getColumnModel().getColumn(0).setHeaderValue("Nº Festa");
	      tblFestas.getColumnModel().getColumn(1).setHeaderValue("Data");
	      tblFestas.getColumnModel().getColumn(2).setHeaderValue("Local");
	      JScrollPane scrollpane = new JScrollPane(tblFestas);
	      
	      //adicionando propriedades aos componentes
	      btnCadastra.setSize(20,20);
	      btnAtualiza.setSize(20,20);
	      btnExclui.setSize(20,20);
	      btnSair.setSize(20,20);
			
		//adicionando listeners aos componentes
		btnCadastra.addActionListener(this);
		btnAtualiza.addActionListener(this);
		btnExclui.addActionListener(this);
		btnSair.addActionListener(this);
		tblFestas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
			        if (tblFestas.getSelectedRow() > -1) {
			        	int aux;
			        	int i = tblFestas.getSelectedRow();
						Object x = tblFestas.getValueAt(i, 0);
						txtNFesta.setText(String.valueOf(x));
						aux = Integer.parseInt(String.valueOf(x));
						x = tblFestas.getValueAt(i, 2);
						txtLocal.setText(String.valueOf(x));
						x=tblFestas.getValueAt(i, 1);
						new cadastraOrganizador(aux);
						
			        }
			}
		}); 
		
		//adicionando componentes ao JFrame
		this.add(txtNFesta);
		this.add(txtDia);
		this.add(txtMes);
		this.add(txtAno);
		this.add(txtHora);
		this.add(txtLocal);
		this.add(btnCadastra);
		this.add(btnAtualiza);
		this.add(btnExclui);
		this.add(btnSair);
		this.add(scrollpane);
		
		//Lendo para a tabela
		atualizaTable();
	}
	
	public void actionPerformed(ActionEvent e) {
		Object alvo = e.getSource();
		if(alvo == btnSair) {
			this.dispose();
		}
		if(alvo == btnCadastra) {
			Festa f = new Festa();
			f.setNumFesta(Integer.parseInt(txtNFesta.getText()));
			f.setLocal(txtLocal.getText());
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(txtDia.getText()));
			c.set(Calendar.MONTH, Integer.parseInt(txtMes.getText()));
			c.set(Calendar.YEAR, Integer.parseInt(txtAno.getText()));
			c.set(Calendar.HOUR, Integer.parseInt(txtHora.getText()));
			f.setData(c);
			FestaDao fbd = new FestaDao();
			fbd.adicionar(f);
			txtNFesta.setText("");
			txtDia.setText("");
			txtMes.setText("");
			txtAno.setText("");
			txtLocal.setText("");
			txtHora.setText("");
			atualizaTable();
		}
		
		if(alvo == btnAtualiza) {
			Festa f = new Festa();
			f.setNumFesta(Integer.parseInt(txtNFesta.getText()));
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(txtDia.getText()));
			c.set(Calendar.MONTH, Integer.parseInt(txtMes.getText()));
			c.set(Calendar.YEAR, Integer.parseInt(txtAno.getText()));
			c.set(Calendar.HOUR, Integer.parseInt(txtHora.getText()));
			f.setData(c);
			f.setLocal(txtLocal.getText());
			FestaDao fbd = new FestaDao();
			fbd.atualizar(f);
			atualizaTable();
		}
		
		if(alvo == btnExclui) {
			Festa f = new Festa();
			f.setNumFesta(Integer.parseInt(txtNFesta.getText()));
			FestaDao fbd = new FestaDao();
			fbd.excluir(f);
			txtNFesta.setText("");
			txtDia.setText("");
			txtMes.setText("");
			txtAno.setText("");
			txtLocal.setText("");
			txtHora.setText("");
			atualizaTable();
		}
		
	}
	
	public void atualizaTable() {
		DefaultTableModel model = (DefaultTableModel) tblFestas.getModel();
		model.setRowCount(0); // apaga tudo
		List<Festa> festas;
		FestaDao fbd2 = new FestaDao();
		festas = fbd2.ler();
		for(Festa festa: festas) {
			model.addRow(new Object[]{festa.getNumFesta(), festa.getData().getTime(), festa.getLocal()});//insere todas as coisas lidas
		}
		
	}

}
