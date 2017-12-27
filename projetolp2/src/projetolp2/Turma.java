package projetolp2;

import java.util.ArrayList;

public class Turma {

	private Professor professor;
	private ArrayList<Aluno> alunos = new ArrayList<>();
	static private int idCont = 0;
	private int id;

	private boolean status = true;
	private Disciplina disciplina;

	public Turma(Professor professor, Disciplina disciplina) {
		id = idCont++;
		this.professor = professor;
		this.disciplina = disciplina;

	}

	public void addAluno(Aluno aluno) {
		this.alunos.add(aluno);
	}
	
	public void mostrarAlunos() {
		for (Aluno aluno : alunos) {
			System.out.println("     Matricula: " + aluno.getMatricula() + " - Nome: " + aluno.getNome());
		}
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}


	public Aluno getAluno(int matricula) {

		return alunos.get(matricula);
	}

	public boolean isStatus() {
		return status;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public ArrayList<Aluno> getAlunos() {

		return alunos;
	}

}
