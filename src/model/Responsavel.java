package model;

import java.util.Calendar;

public class Responsavel {
	
	//Atributos do modelo
	private int numTarefa;
	private int cpf;
	private Calendar data;
	
	//Getters e Setters
	public int getNumTarefa() {
		return numTarefa;
	}
	public void setNumTarefa(int numTarefa) {
		this.numTarefa = numTarefa;
	}
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	
	
	

}
