package model;

public class Morador {

	//Atributos do modelo
	private int cpf;
	private String nome;
	private String curso;
	private Boolean veterano;
	
	//Getters e Setters
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public Boolean getVeterano() {
		return veterano;
	}
	public void setVeterano(Boolean veterano) {
		this.veterano = veterano;
	}
	
	
}
