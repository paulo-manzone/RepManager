package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import model.Morador;
import model.Organizacao;
import model.Responsavel;
import modelDao.MoradorDao;
import modelDao.OrganizacaoDao;
import modelDao.ResponsavelDao;

public class cadastraOrganizador extends JFrame implements ActionListener{
	//variavel necessária
		private int festira;
		
		//Componentes utilizados
		private JTextField txtCPF = new JTextField("CPF");
		private JButton btnAdd = new JButton("+");
		private JButton btnRemove = new JButton("-");
		private JTable tblOrganizadores;
		
		
		public cadastraOrganizador(int festaSelecionada){
			//Mudando propriedades do JFrame
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setSize(650, 300);
			this.setVisible(true); // deixa visível
			this.setLayout(new FlowLayout());
			this.setLocationRelativeTo(null);
			this.setTitle("Cadastrar Organizadores");
			this.setResizable(false);
			DefaultTableModel dataModel = new DefaultTableModel();
			festira = festaSelecionada;
			
			//criando JTable
			dataModel.addColumn("CPF");
			dataModel.addColumn("Nº da Festa");
			tblOrganizadores = new JTable(dataModel);
			tblOrganizadores.getColumnModel().getColumn(0).setHeaderValue("CPF");
			tblOrganizadores.getColumnModel().getColumn(1).setHeaderValue("Nº da Festa");

			JScrollPane scrollpane = new JScrollPane(tblOrganizadores);
			
			//adicionando propriedades aos componentes
			btnAdd.setSize(20,10);
			btnRemove.setSize(20,10);
			
			//adicionando listeners aos componentes
			btnAdd.addActionListener(this);
			btnRemove.addActionListener(this);
			tblOrganizadores.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
					
					@Override
					public void valueChanged(ListSelectionEvent e) {
					        if (tblOrganizadores.getSelectedRow() > -1) {
					        	int i = tblOrganizadores.getSelectedRow();
								Object x = tblOrganizadores.getValueAt(i, 0);
								txtCPF.setText(String.valueOf(x));		
					        }
					}
				});
			
			//adicionando componentes ao JFrame
		      this.add(txtCPF);
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
				Organizacao o = new Organizacao();
				o.setCpf(Integer.parseInt(txtCPF.getText()));
				o.setNumFesta(festira);
				OrganizacaoDao obd = new OrganizacaoDao();
				if(validaVeterano(o.getCpf())) {
					obd.adicionar(o);
				}else {
					JOptionPane.showMessageDialog(null, "Erro: Apenas veteranos podem organizar festas!");
				}
				txtCPF.setText("CPF");
				atualizaTable();
			}
			if(alvo == btnRemove) {
				Organizacao o = new Organizacao();
				o.setCpf(Integer.parseInt(txtCPF.getText()));
				OrganizacaoDao obd = new OrganizacaoDao();
				obd.excluir(o);
				atualizaTable();
			}
			
		}
		
		public void atualizaTable() {
			DefaultTableModel model = (DefaultTableModel) tblOrganizadores.getModel();
			model.setRowCount(0); // apaga tudo
			List<Organizacao> orgs;
			OrganizacaoDao obd2 = new OrganizacaoDao();
			orgs = obd2.ler(festira);
			for(Organizacao o: orgs) {
				model.addRow(new Object[]{o.getCpf(),o.getNumFesta()});//insere todas as coisas lidas
			}
			
		}
		
		private boolean validaVeterano(int cpf) {
			MoradorDao mdb = new MoradorDao();
			Morador m = mdb.lerCpf(cpf);
			if(m.getVeterano() == true) {
				return true;
			}else return false;
			
		}
}
