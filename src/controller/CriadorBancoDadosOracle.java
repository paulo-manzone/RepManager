package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class CriadorBancoDadosOracle {

	public CriadorBancoDadosOracle() {
		try {
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "123456");
			Statement st = con.createStatement();
			
			//Códigos de criação do banco
			
			//tipos
			/*st.execute("CREATE OR REPLACE TYPE morador_ti");

			st.execute("CREATE OR REPLACE TYPE responsavel_ti");

			st.execute("CREATE OR REPLACE TYPE mora_ty");

			st.execute("CREATE OR REPLACE TYPE tarefa_ty");

			st.execute("CREATE OR REPLACE TYPE quarto_ty");

			st.execute("CREATE OR REPLACE TYPE festa_ty");*/

			st.execute("CREATE OR REPLACE TYPE morador_ti AS OBJECT(cpf INTEGER, nome VARCHAR2(30), curso VARCHAR2(30), veterano NUMBER(1))");
			st.execute("CREATE OR REPLACE TYPE organizadores_nt AS TABLE OF REF morador_ti");
			st.execute("CREATE OR REPLACE TYPE tarefa_ti AS OBJECT(numTarefa INTEGER, descricao VARCHAR(50))");
			st.execute("CREATE OR REPLACE TYPE responsavel_ti AS OBJECT(morador REF MORADOR_TI, tarefa REF TAREFA_TI, data DATE)");
			st.execute("CREATE OR REPLACE TYPE quarto_ti AS OBJECT(numQuarto integer, capacidade integer)");
			st.execute("CREATE OR REPLACE TYPE mora_ti AS OBJECT(morador REF MORADOR_TI, quarto REF QUARTO_TI, custo integer, data DATE)");
			st.execute("CREATE OR REPLACE TYPE festa_ti AS OBJECT(NumFesta integer, DataFesta DATE, LocalFesta VARCHAR2(15),organizadores organizadores_nt)");
			//st.execute("CREATE OR REPLACE TYPE festa_nt AS TABLE OF REF festa_ty");
			//tabelas
			st.execute("CREATE TABLE morador OF morador_ti (PRIMARY KEY (cpf)) ");
			st.execute("CREATE TABLE tarefa OF tarefa_ti (PRIMARY KEY (numTarefa))");
			st.execute("CREATE TABLE responsavel OF responsavel_ti");
			st.execute("CREATE TABLE mora OF mora_ti");
			st.execute("CREATE TABLE quarto OF quarto_ti (PRIMARY KEY (numQuarto))");
			st.execute("CREATE TABLE festa of festa_ti(PRIMARY KEY (NUMFESTA) nested table organizadores store as organizadores_ST");
					
			con.close();
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	
	// Comandos:
	/* -- Criação dos tipos -- */
	/*
	CREATE OR REPLACE TYPE morador_ty;

	CREATE OR REPLACE TYPE responsavel_ty;

	CREATE OR REPLACE TYPE mora_ty;

	CREATE OR REPLACE TYPE tarefa_ty;

	CREATE OR REPLACE TYPE quarto_ty;

	CREATE OR REPLACE TYPE festa_ty;

	CREATE OR REPLACE TYPE festa_nt AS TABLE OF REF festa_ty;

	 fazendo Morador 
	CREATE OR REPLACE TYPE morador_ty AS OBJECT(cpf INTEGER, nome VARCHAR2(30), curso VARCHAR2(30), veterano NUMBER(1), organizaFesta festa_nt);

	 fazendo Responsavel 
	CREATE OR REPLACE TYPE responsavel_ty AS OBJECT(morador REF MORADOR_TY, tarefa REF TAREFA_TY, data DATE);

	 fazendo Tarefa
	CREATE OR REPLACE TYPE tarefa_ty AS OBJECT(numTarefa INTEGER, descricao VARCHAR(50));

	 fazendo Mora 
	CREATE OR REPLACE TYPE mora_ty AS OBJECT(morador REF MORADOR_TY, quarto REF QUARTO_TY, custo integer, data DATE);

	fazendo Quarto
	CREATE OR REPLACE TYPE quarto_ty AS OBJECT(numQuarto integer, capacidade integer);

	 fazendo Festa 
	CREATE OR REPLACE TYPE festa_ty AS OBJECT(NumFesta integer, DataFesta DATE, LocalFesta VARCHAR2(15));


	 -- Criação das tabelas -- 

	CREATE OR REPLACE TABLE morador OF morador_ty (PRIMARY KEY (cpf)) nested table organizaFesta store as organizaFesta_ST;

	CREATE OR REPLACE TABLE tarefa OF tarefa_ty (PRIMARY KEY (numTarefa));

	CREATE OR REPLACE TABLE responsavel OF responsavel_ty;

	CREATE OR REPLACE TABLE mora OF mora_ty;

	CREATE OR REPLACE TABLE quarto OF quarto_ty (PRIMARY KEY (numQuarto));
	*/
}
