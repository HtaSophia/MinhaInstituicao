package projetolp2;

import java.util.ArrayList;

public class Professor {
	
	private int matricula;
	private String nome;
	private static int contMatricula = 1;
	private int contTurmas = 0;
	private ArrayList<Turma> turmas;
	
	public Professor(String nome) {
		this.setNome(nome);
		matricula = Integer.parseInt("2017" + contMatricula);
		turmas = new ArrayList<>();
		contMatricula++;
	}
	
	public int getQuantTurma() {
		return this.contTurmas;
	}
	
	public void setTurma(Turma turma) {
		turmas.add(turma);
		this.contTurmas++;
	}
	
	public ArrayList<Turma> getTurmas(){
		return this.turmas;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
