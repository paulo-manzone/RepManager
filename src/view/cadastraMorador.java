package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Festa;
import model.Morador;
import modelDao.FestaDao;
import modelDao.MoradorDao;

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
	DefaultTableModel dataModel = new DefaultTableModel();
	
	
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
		dataModel.addColumn("cpf");
		dataModel.addColumn("nome");
		dataModel.addColumn("curso");
		dataModel.addColumn("veterano");
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
		tblMoradores.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
			        if (tblMoradores.getSelectedRow() > -1) {
			        	int i = tblMoradores.getSelectedRow();
						Object x = tblMoradores.getValueAt(i, 0);
						txtCPF.setText(String.valueOf(x));
						x = tblMoradores.getValueAt(i, 1);
						txtNome.setText(String.valueOf(x));
						x=tblMoradores.getValueAt(i, 2);
						txtCurso.setText(String.valueOf(x));
						x=tblMoradores.getValueAt(i, 3);
						if(String.valueOf(x) == "true") {
							chbVeterano.setSelected(true);
						}else chbVeterano.setSelected(false);
						
			        }
			}
		});
			
		//adicionando componentes ao JFrame
		this.add(txtCPF);
		this.add(txtNome);
		this.add(txtCurso);
		this.add(chbVeterano);
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
			Morador m = new Morador();
			m.setCpf(Integer.parseInt(txtCPF.getText()));
			m.setNome(txtNome.getText());
			m.setCurso(txtCurso.getText());
			m.setVeterano(chbVeterano.isSelected());
			MoradorDao mbd = new MoradorDao();
			mbd.adicionar(m);
			txtCPF.setText("");
			txtNome.setText("");
			txtCurso.setText("");
			chbVeterano.setSelected(false);
			atualizaTable();
		}
		if(alvo == btnAtualiza) {
			Morador m = new Morador();
			m.setCpf(Integer.parseInt(txtCPF.getText()));
			m.setNome(txtNome.getText());
			m.setCurso(txtCurso.getText());
			m.setVeterano(chbVeterano.isSelected());
			MoradorDao mbd = new MoradorDao();
			mbd.atualizar(m);
			atualizaTable();
			
		}
		if(alvo == btnExclui) {
			Morador m = new Morador();
			m.setCpf(Integer.parseInt(txtCPF.getText()));
			MoradorDao mbd = new MoradorDao();
			mbd.excluir(m);
			atualizaTable();
		}
		
	}
	
	public void atualizaTable() {
		DefaultTableModel model = (DefaultTableModel) tblMoradores.getModel();
		model.setRowCount(0); // apaga tudo
		List<Morador> moradores;
		MoradorDao mbd2 = new MoradorDao();
		moradores = mbd2.ler();
		for(Morador morador: moradores) {
			model.addRow(new Object[]{morador.getCpf(), morador.getNome(), morador.getCurso(), morador.getVeterano()});//insere todas as coisas lidas
		}
		
	}
	
	
}
