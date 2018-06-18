package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Estadia;
import modelDao.EstadiaDao;

public class formEstadia extends JFrame implements ActionListener{
	
	//Declaração de componentes
	private JButton btnMorador = new JButton("+ M");
	private JButton btnQuarto = new JButton("+ Q");
	private JButton btnRelacao = new JButton("+ R");
	private JButton btnRefresh = new JButton("Refresh");
	
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
		DefaultTableModel dataModel = new DefaultTableModel();
		
		//criando JTable
		dataModel.addColumn("CPF");
		dataModel.addColumn("Nº do Quarto");
		dataModel.addColumn("Custo");
		dataModel.addColumn("Mes");
		dataModel.addColumn("Ano");
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
		btnRefresh.setSize(30,20);
		btnFechar.setSize(20,20);
		
		
		//adicionando listeners aos componentes
		btnMorador.addActionListener(this);
		btnQuarto.addActionListener(this);
		btnRelacao.addActionListener(this);
		btnRefresh.addActionListener(this);
		btnFechar.addActionListener(this);
		
		//adicionando componentes ao JFrame
		this.add(btnMorador);
		this.add(btnQuarto);
		this.add(btnRelacao);
		this.add(btnRefresh);
		this.add(btnFechar);
		this.add(scrollpane);
		
		atualizaTable();
		
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
		if(alvo == btnRefresh) {
			atualizaTable();
		}
		
	}

	public void atualizaTable() {
		DefaultTableModel model = (DefaultTableModel) relacao.getModel();
		model.setRowCount(0); // apaga tudo
		List<Estadia> estadias;
		EstadiaDao esbd2 = new EstadiaDao();
		estadias = esbd2.ler();
		for(Estadia estadia: estadias) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date(estadia.getData().getTimeInMillis()));
			int mes, ano;
			mes = cal.MONTH;
			ano = cal.YEAR;
			model.addRow(new Object[]{estadia.getCpf(),estadia.getNumQuarto(), estadia.getCusto(), mes, ano});//insere todas as coisas lidas
		}
		
	}
	
}
