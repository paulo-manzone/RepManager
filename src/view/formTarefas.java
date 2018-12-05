package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
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


import model.Tarefa;
import modelDao.TarefaDao;

public class formTarefas extends JFrame implements ActionListener{
	
	
	//componentes utilizados
	private JButton btnCriar = new JButton("Criar");
	private JButton btnAtualizar = new JButton("Atualizar");
	private JButton btnExcluir = new JButton("Excluir");
	private JTextField txtNumTarefa = new JTextField("Nºtarefa",8);
	private JTextField txtDescricao = new JTextField("Descricao",20);
	private JTable tblTarefas;
	/*private TableModel dataModel = new AbstractTableModel() {
        public int getColumnCount() { return 6; }
        public int getRowCount() { return 10;}
        public Object getValueAt(int row, int col) { return null; }
    };*/
	DefaultTableModel dataModel = new DefaultTableModel();
	
	public formTarefas() {
		//Mudando propriedades do JFrame
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(600, 450);
		this.setUndecorated(false); // tira bordas
		this.setVisible(true); // deixa visível
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);
		this.setTitle("Tarefas");
		this.setResizable(false);
		
		//props da tabela
		dataModel.addColumn("nº tarefas");
		dataModel.addColumn("desc");
	      tblTarefas = new JTable(dataModel);
	      tblTarefas.getColumnModel().getColumn(0).setHeaderValue("Nº Tarefa");
	      tblTarefas.getColumnModel().getColumn(1).setHeaderValue("Descricao");
	      JScrollPane scrollpane = new JScrollPane(tblTarefas);
	      
	      btnCriar.setSize(20,20);
	      btnAtualizar.setSize(20,20);
	      btnExcluir.setSize(20,20);
	      
	    //adicionando listeners aos componentes
		btnCriar.addActionListener(this);
		btnAtualizar.addActionListener(this);
		btnExcluir.addActionListener(this);
		tblTarefas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
			        if (tblTarefas.getSelectedRow() > -1) {
			        	int aux;
			        	int i = tblTarefas.getSelectedRow();
						Object x = tblTarefas.getValueAt(i, 0);
						txtNumTarefa.setText(String.valueOf(x));
						aux = Integer.parseInt(String.valueOf(x));
						x = tblTarefas.getValueAt(i, 1);
						txtDescricao.setText(String.valueOf(x));
						new cadastraResponsavel(aux);
			        }
				
			}
		}); 
		
		//adicionando componentes ao JFrame
		this.add(txtNumTarefa);
		this.add(txtDescricao);
		this.add(btnCriar);
		this.add(btnAtualizar);
		this.add(btnExcluir);
		this.add(scrollpane);
		
		//Lendo para a tabela
		atualizaTable();

	}
	

	//listener
	public void actionPerformed(ActionEvent e) {
		Object alvo = e.getSource();
		if(alvo == btnCriar) {
			Tarefa t = new Tarefa();
			t.setNumTarefa(Integer.parseInt(txtNumTarefa.getText()));
			t.setDescricao(txtDescricao.getText());
			TarefaDao tbd = new TarefaDao();
			tbd.adicionar(t);
			txtDescricao.setText("Descricao");
			txtNumTarefa.setText("NºTarefa");
			atualizaTable();
		}
		if(alvo == btnAtualizar) {
			Tarefa t = new Tarefa();
			t.setNumTarefa(Integer.parseInt(txtNumTarefa.getText()));
			t.setDescricao(txtDescricao.getText());
			TarefaDao tbd = new TarefaDao();
			tbd.atualizar(t);
			atualizaTable();

			
		}
		if(alvo == btnExcluir) {
			Tarefa t = new Tarefa();
			t.setNumTarefa(Integer.parseInt(txtNumTarefa.getText()));
			t.setDescricao(txtDescricao.getText());
			TarefaDao tbd = new TarefaDao();
			tbd.excluir(t);
			atualizaTable();
			txtDescricao.setText("");
			txtNumTarefa.setText("");
		}

		
	}
	
	public void atualizaTable() {
		DefaultTableModel model = (DefaultTableModel) tblTarefas.getModel();
		model.setRowCount(0); // apaga tudo
		List<Tarefa> tarefas;
		TarefaDao tbd2 = new TarefaDao();
		tarefas = tbd2.ler();
		for(Tarefa tarefa: tarefas) {
			model.addRow(new Object[]{tarefa.getNumTarefa(), tarefa.getDescricao()});//insere todas as coisas lidas
		}
		
	}
	
	
		
}
