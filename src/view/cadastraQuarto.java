package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Morador;
import model.Quarto;
import modelDao.MoradorDao;
import modelDao.QuartoDao;

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
		DefaultTableModel dataModel = new DefaultTableModel();
		
		
		//criando JTable
		dataModel.addColumn("Nº do Quarto");
		dataModel.addColumn("Capacidade");
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
		tblQuartos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
			        if (tblQuartos.getSelectedRow() > -1) {
			        	int i = tblQuartos.getSelectedRow();
						Object x = tblQuartos.getValueAt(i, 0);
						txtNQuarto.setText(String.valueOf(x));
						x = tblQuartos.getValueAt(i, 1);
						txtCapacidade.setText(String.valueOf(x));
						
			        }
			}
		});
				
		//adicionando componentes ao JFrame
		this.add(txtNQuarto);
		this.add(txtCapacidade);
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
			Quarto q = new Quarto();
			q.setNumQuarto(Integer.parseInt(txtNQuarto.getText()));
			q.setCapacidade(Integer.parseInt(txtCapacidade.getText()));
			QuartoDao qbd = new QuartoDao();
			qbd.adicionar(q);
			txtNQuarto.setText("");
			txtCapacidade.setText("");
			atualizaTable();
			
		}
		if(alvo == btnAtualiza) {
			Quarto q = new Quarto();
			q.setNumQuarto(Integer.parseInt(txtNQuarto.getText()));
			q.setCapacidade(Integer.parseInt(txtCapacidade.getText()));
			QuartoDao qbd = new QuartoDao();
			qbd.atualizar(q);
			atualizaTable();
			
		}
		if(alvo == btnExclui) {
			Quarto q = new Quarto();
			q.setNumQuarto(Integer.parseInt(txtNQuarto.getText()));
			QuartoDao qbd = new QuartoDao();
			qbd.excluir(q);
			txtNQuarto.setText("");
			txtCapacidade.setText("");
			atualizaTable();
		}
	}
	
	public void atualizaTable() {
		DefaultTableModel model = (DefaultTableModel) tblQuartos.getModel();
		model.setRowCount(0); // apaga tudo
		List<Quarto> quartos;
		QuartoDao qbd2 = new QuartoDao();
		quartos = qbd2.ler();
		for(Quarto quarto: quartos) {
			model.addRow(new Object[]{quarto.getNumQuarto(),quarto.getCapacidade()});//insere todas as coisas lidas
		}
		
	}
}
