package projetolp2;

import java.util.Scanner;

public class Criar {

	static Scanner scanner = new Scanner(System.in);

	public static void matricularAluno() {
		String nome;
		Aluno aluno;

		System.out.print("Digite o nome do Aluno: ");
		nome = scanner.nextLine();
		aluno = new Aluno(nome);
		Menu.banco.addAluno(aluno);
		System.out.println("\nAluno matriculado com sucesso!\n");
	}

	public static void matricularProfessor() {
		String nome;
		Professor professor;

		System.out.print("Digite o nome do Professor: ");
		nome = scanner.nextLine();
		professor = new Professor(nome);
		Menu.banco.addProfessor(professor);
		System.out.println("\nProfessor matriculado com sucesso!\n");
	}

	public static void criarDisciplina() {
		String nome;
		Disciplina disciplina;
		boolean existe = false;

		System.out.print("Digite o nome da Disciplina: ");
		nome = scanner.nextLine();

		// Procura se a disciplina já existe
		for (int i = 0; i < Menu.banco.getDisciplinas().size(); i++) {
			if (Menu.banco.getDisciplinas().get(i).getNome().equals(nome)) {
				existe = true;
			}
		}

		if (existe) {
			System.out.println(" |!!| A disciplina já existe.\n");
		} else {
			disciplina = new Disciplina(nome);
			Menu.banco.addDisciplina(disciplina);
			System.out.println("\nDisciplina criada com sucesso!\n");
		}

	}

	public static void criarTurma() {
		Disciplina disciplina;
		Professor professor = null;
		int matri, id;
		// Testa se existe disciplina e professores, que o necessario pra criação da turma
		if (Menu.banco.getDisciplinas().size() > 0 && Menu.banco.getProfessor().size() > 0) {
			System.out.println("Professores(as):");
			Menu.banco.mostrarProfessores();
			System.out.print("Digite a matrícula do Professor para esta Turma: ");
			matri = Integer.parseInt(Menu.recebeNumero());
			professor = Menu.banco.getProfessor(matri);
			
			if (professor != null) {
				Menu.banco.mostrarDisciplinas();
				System.out.print("Digite o id da Disciplina para esta Turma: ");
				id = Integer.parseInt(Menu.recebeNumero());
				disciplina = Menu.banco.getDisciplina(id);
				
				if (disciplina != null) {
					// Testa se o professor tem menos que 2 turmas associadas a ele
					if (professor.getQuantTurma() < 2) {
						Turma turma = new Turma(professor, disciplina);
						professor.setTurma(turma);
						Menu.banco.addTurma(turma);
						System.out.println("\nTurma criada com sucesso!\b");
					} else {
						System.out.println(" |!!| O professor só pode ter até 2 turmas!\n");
					}
				}
			}

		} else {
			System.out.println(" |!!| Não foi criado(a) um(a) Professor(a) ou uma Disciplina!\n");
		}

	}
	
}
