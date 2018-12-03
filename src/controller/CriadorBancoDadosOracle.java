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
			st.execute("CREATE TYPE morador_ty");

			st.execute("CREATE TYPE responsavel_ty");

			st.execute("CREATE TYPE mora_ty");

			st.execute("CREATE TYPE tarefa_ty");

			st.execute("CREATE TYPE quarto_ty");

			st.execute("CREATE TYPE festa_ty");

			st.execute("CREATE TYPE festa_nt AS TABLE OF REF festa_ty");
			
			st.execute("CREATE TYPE morador_ty AS OBJECT(cpf INTEGER, nome VARCHAR2(30), curso VARCHAR2(30), veterano NUMBER(1), organizaFesta festa_nt)");
			st.execute("CREATE TYPE responsavel_ty AS OBJECT(morador REF MORADOR_TY, tarefa REF TAREFA_TY, data DATE)");
			st.execute("CREATE TYPE tarefa_ty AS OBJECT(numTarefa INTEGER, descricao VARCHAR(50))");
			st.execute("CREATE TYPE mora_ty AS OBJECT(morador REF MORADOR_TY, quarto REF QUARTO_TY, custo integer, data DATE)");
			st.execute("CREATE TYPE quarto_ty AS OBJECT(numQuarto integer, capacidade integer)");
			st.execute("CREATE TYPE festa_ty AS OBJECT(NumFesta integer, DataFesta DATE, LocalFesta VARCHAR2(15))");
			//tabelas
			st.execute("CREATE TABLE morador OF morador_ty (PRIMARY KEY (cpf)) nested table organizaFesta store as organizaFesta_ST");
			st.execute("CREATE TABLE tarefa OF tarefa_ty (PRIMARY KEY (numTarefa))");
			st.execute("CREATE TABLE responsavel OF responsavel_ty");
			st.execute("CREATE TABLE mora OF mora_ty");
			st.execute("CREATE TABLE quarto OF quarto_ty (PRIMARY KEY (numQuarto))");
					
			con.close();
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	
	// Comandos:
	/* -- Criação dos tipos -- */
	/*
	CREATE TYPE morador_ty;

	CREATE TYPE responsavel_ty;

	CREATE TYPE mora_ty;

	CREATE TYPE tarefa_ty;

	CREATE TYPE quarto_ty;

	CREATE TYPE festa_ty;

	CREATE TYPE festa_nt AS TABLE OF REF festa_ty;

	 fazendo Morador 
	CREATE TYPE morador_ty AS OBJECT(cpf INTEGER, nome VARCHAR2(30), curso VARCHAR2(30), veterano NUMBER(1), organizaFesta festa_nt);

	 fazendo Responsavel 
	CREATE TYPE responsavel_ty AS OBJECT(morador REF MORADOR_TY, tarefa REF TAREFA_TY, data DATE);

	 fazendo Tarefa
	CREATE TYPE tarefa_ty AS OBJECT(numTarefa INTEGER, descricao VARCHAR(50));

	 fazendo Mora 
	CREATE TYPE mora_ty AS OBJECT(morador REF MORADOR_TY, quarto REF QUARTO_TY, custo integer, data DATE);

	fazendo Quarto
	CREATE TYPE quarto_ty AS OBJECT(numQuarto integer, capacidade integer);

	 fazendo Festa 
	CREATE TYPE festa_ty AS OBJECT(NumFesta integer, DataFesta DATE, LocalFesta VARCHAR2(15));


	 -- Criação das tabelas -- 

	CREATE TABLE morador OF morador_ty (PRIMARY KEY (cpf)) nested table organizaFesta store as organizaFesta_ST;

	CREATE TABLE tarefa OF tarefa_ty (PRIMARY KEY (numTarefa));

	CREATE TABLE responsavel OF responsavel_ty;

	CREATE TABLE mora OF mora_ty;

	CREATE TABLE quarto OF quarto_ty (PRIMARY KEY (numQuarto));
	*/
}
