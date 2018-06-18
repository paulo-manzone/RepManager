package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import model.Estadia;
import model.Responsavel;
import modelDao.EstadiaDao;
import modelDao.ResponsavelDao;

public class cadastraResponsavel extends JFrame implements ActionListener{
	//variavel necessária
	private int tarefa;
	
	//Componentes utilizados
	private JTextField txtCPF = new JTextField("CPF");
	private JTextField txtDia = new JTextField("Dia");
	private JTextField txtMes = new JTextField("Mes",2);
	private JTextField txtAno = new JTextField("Ano", 4);
	private JButton btnAdd = new JButton("+");
	private JButton btnRemove = new JButton("-");
	private JTable tblResponsaveis;
	
	
	public cadastraResponsavel(int tarefaSelecionada){
		//Mudando propriedades do JFrame
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(600, 250);
		this.setVisible(true); // deixa visível
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);
		this.setTitle("Cadastrar Responsaveis");
		this.setResizable(false);
		DefaultTableModel dataModel = new DefaultTableModel();
		tarefa = tarefaSelecionada;
		
		//criando JTable
		dataModel.addColumn("CPF");
		dataModel.addColumn("Nº da Tarefa");
		dataModel.addColumn("Dia");
		dataModel.addColumn("Mes");
		dataModel.addColumn("Ano");
		tblResponsaveis = new JTable(dataModel);
		tblResponsaveis.getColumnModel().getColumn(0).setHeaderValue("CPF");
		tblResponsaveis.getColumnModel().getColumn(1).setHeaderValue("Nº da Tarefa");
		tblResponsaveis.getColumnModel().getColumn(2).setHeaderValue("Dia");
		tblResponsaveis.getColumnModel().getColumn(2).setHeaderValue("Mes");
		tblResponsaveis.getColumnModel().getColumn(2).setHeaderValue("Ano");

		JScrollPane scrollpane = new JScrollPane(tblResponsaveis);
		
		//adicionando propriedades aos componentes
		btnAdd.setSize(10,10);
		btnRemove.setSize(10,10);
		
		//adicionando listeners aos componentes
		btnAdd.addActionListener(this);
		btnRemove.addActionListener(this);
		tblResponsaveis.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
				        if (tblResponsaveis.getSelectedRow() > -1) {
				        	int i = tblResponsaveis.getSelectedRow();
							Object x = tblResponsaveis.getValueAt(i, 0);
							txtCPF.setText(String.valueOf(x));
							x = tblResponsaveis.getValueAt(i, 2);
							txtDia.setText(String.valueOf(x));
							x = tblResponsaveis.getValueAt(i, 3);
							txtMes.setText(String.valueOf(x));
							x = tblResponsaveis.getValueAt(i, 4);
							txtAno.setText(String.valueOf(x));
							
				        }
				}
			});
		
		//adicionando componentes ao JFrame
	      this.add(txtCPF);
	      this.add(txtDia);
	      this.add(txtMes);
	      this.add(txtAno);
	      this.add(btnAdd);
	      this.add(btnRemove);
	      this.add(scrollpane);
	      
	    //Lendo para a tabela
		atualizaTable();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object alvo = e.getSource();
		if(alvo == btnAdd) {
			Responsavel r = new Responsavel();
			r.setCpf(Integer.parseInt(txtCPF.getText()));
			r.setNumTarefa(tarefa);
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(txtDia.getText()));
			cal.set(Calendar.MONTH, Integer.parseInt(txtMes.getText()));
			cal.set(Calendar.YEAR, Integer.parseInt(txtAno.getText()));
			r.setData(cal);
			ResponsavelDao rbd = new ResponsavelDao();
			rbd.adicionar(r);
			txtCPF.setText("CPF");
			txtDia.setText("Dia");
			txtMes.setText("Mes");
			txtAno.setText("Ano");
			atualizaTable();
		}
		if(alvo == btnRemove) {
			Responsavel r = new Responsavel();
			r.setCpf(Integer.parseInt(txtCPF.getText()));
			ResponsavelDao rbd = new ResponsavelDao();
			rbd.excluir(r);
			atualizaTable();
		}
		
	}
	
	public void atualizaTable() {
		DefaultTableModel model = (DefaultTableModel) tblResponsaveis.getModel();
		model.setRowCount(0); // apaga tudo
		List<Responsavel> responsaveis;
		ResponsavelDao rbd2 = new ResponsavelDao();
		responsaveis = rbd2.ler(tarefa);
		for(Responsavel responsavel: responsaveis) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date(responsavel.getData().getTimeInMillis()));
			int dia, mes, ano;
			dia = cal.DAY_OF_MONTH;
			mes = cal.MONTH;
			ano = cal.YEAR;
			model.addRow(new Object[]{responsavel.getCpf(),responsavel.getNumTarefa(), dia, mes, ano});//insere todas as coisas lidas
		}
		
	}
	
	
	

}
