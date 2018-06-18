package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Estadia;
import model.Quarto;
import modelDao.EstadiaDao;
import modelDao.QuartoDao;

public class cadastraEstadia extends JFrame implements ActionListener{
	
	//Variáveis necessáarias
	private String listaQuartos[];
	private String listaMoradores[];
	
	//componentes utilizados
	private JTextField txtCPF = new JTextField("CPF");
	private JTextField txtQuarto = new JTextField("nº Quarto");
	private JTextField txtCusto = new JTextField("Custo");
	private JTextField txtMes = new JTextField("Mes",2);
	private JTextField txtAno= new JTextField("Ano", 4);
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
		DefaultTableModel dataModel = new DefaultTableModel();
		
		//criando JTable
		dataModel.addColumn("CPF");
		dataModel.addColumn("Nº do Quarto");
		dataModel.addColumn("Custo");
		dataModel.addColumn("Mes");
		dataModel.addColumn("Ano");
	      tblRelacoes = new JTable(dataModel);
	      tblRelacoes.getColumnModel().getColumn(0).setHeaderValue("CPF");
	      tblRelacoes.getColumnModel().getColumn(1).setHeaderValue("Nº do Quarto");
	      tblRelacoes.getColumnModel().getColumn(2).setHeaderValue("Custo");
	      tblRelacoes.getColumnModel().getColumn(3).setHeaderValue("Mes");
	      tblRelacoes.getColumnModel().getColumn(4).setHeaderValue("Ano");

	      JScrollPane scrollpane = new JScrollPane(tblRelacoes);
			      
	      //adicionando propriedades aos componentes
	      btnCadastra.setSize(20,20);
	      btnAtualiza.setSize(20,20);
	      btnExclui.setSize(20,20);
						
						
	      //adicionando listeners aos componentes
	      btnCadastra.addActionListener(this);
	      btnAtualiza.addActionListener(this);
	      btnExclui.addActionListener(this);
	      tblRelacoes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
				        if (tblRelacoes.getSelectedRow() > -1) {
				        	int i = tblRelacoes.getSelectedRow();
							Object x = tblRelacoes.getValueAt(i, 0);
							txtCPF.setText(String.valueOf(x));
							x = tblRelacoes.getValueAt(i, 1);
							txtQuarto.setText(String.valueOf(x));
							x = tblRelacoes.getValueAt(i, 2);
							txtCusto.setText(String.valueOf(x));
							x=tblRelacoes.getValueAt(i, 3);
							txtMes.setText(String.valueOf(x));
							x=tblRelacoes.getValueAt(i, 4);
							txtAno.setText(String.valueOf(x));
				        }
				}
			});
		  
						
	      //adicionando componentes ao JFrame
	      this.add(txtCPF);
	      this.add(txtQuarto);
	      this.add(txtCusto);
	      this.add(txtMes);
	      this.add(txtAno);
	      this.add(btnCadastra);
	      this.add(btnAtualiza);
	      this.add(btnExclui);
	      this.add(scrollpane);
	      
	    //Lendo para a tabela
		atualizaTable();
	      
	}	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object alvo = e.getSource();
		if(alvo == btnCadastra) {
			Estadia es = new Estadia();
			es.setCpf(Integer.parseInt(txtCPF.getText()));
			es.setNumQuarto(Integer.parseInt(txtQuarto.getText()));
			es.setCusto(Double.parseDouble(txtCusto.getText()));
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MONTH, Integer.parseInt(txtMes.getText()));
			cal.set(Calendar.YEAR, Integer.parseInt(txtAno.getText()));
			es.setData(cal);
			EstadiaDao esbd = new EstadiaDao();
			if(verificaDisponbilidade(es.getNumQuarto())) {
				esbd.adicionar(es);
			}else JOptionPane.showMessageDialog(null,"Impossivel realizar operação!\nQuarto não possui capacidade.");

			txtCPF.setText("CPF");
			txtQuarto.setText("NºQuarto");
			txtCusto.setText("Custo");
			txtMes.setText("Mes");
			txtAno.setText("Ano");
			atualizaTable();
		}
		if(alvo == btnAtualiza) {
			Estadia es = new Estadia();
			es.setCpf(Integer.parseInt(txtCPF.getText()));
			es.setNumQuarto(Integer.parseInt(txtQuarto.getText()));
			es.setCusto(Double.parseDouble(txtCusto.getText()));
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MONTH, Integer.parseInt(txtMes.getText()));
			cal.set(Calendar.YEAR, Integer.parseInt(txtAno.getText()));
			es.setData(cal);
			EstadiaDao esbd = new EstadiaDao();
			esbd.atualizar(es);
			atualizaTable();
		}
		if(alvo == btnExclui) {
			Estadia es = new Estadia();
			es.setCpf(Integer.parseInt(txtCPF.getText()));
			EstadiaDao esbd = new EstadiaDao();
			esbd.excluir(es);
			atualizaTable();
		}
		
	}
	
	public void atualizaTable() {
		DefaultTableModel model = (DefaultTableModel) tblRelacoes.getModel();
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
	
	private boolean verificaDisponbilidade(int numQuarto) {
		QuartoDao qbd = new QuartoDao();
		List<Quarto> quartos = qbd.ler();
		EstadiaDao ebd = new EstadiaDao();
		int pessoasAlocadas = ebd.ler(numQuarto);
		for(Quarto quarto: quartos) {
			if(quarto.getNumQuarto() == numQuarto) {
				if(pessoasAlocadas < quarto.getCapacidade()) {
					return true;
				}else return false;
			}
		}
		return false;
	}

}
