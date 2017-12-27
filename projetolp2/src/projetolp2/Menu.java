package projetolp2;

/***
 * @author Aghata Sophia & Kamila Albuquerque
 */

import java.util.Scanner;

public class Menu {
	private int op;
	Scanner scanner = new Scanner(System.in);
	int matricula, id;

	static BancodeDados banco;

	public Menu() {
		banco = new BancodeDados();
		while (op != 5) {

			System.out.println(
					"--------------------------------------\n\t\tMENU\n--------------------------------------\n");
			System.out.println("1 - Ver membros da instituição");
			System.out.println("2 - Ver disciplinas ofertadas");
			System.out.println("3 - Ver turmas");
			System.out.println("4 - Mostrar Histórico");
			System.out.println("5 - Sair do programa");
			System.out.print(">> Opção: ");
			op = Integer.parseInt(recebeNumero());
			System.out.println();

			switch (op) {
			case 1:
				verMembros();
				break;
			case 2:
				verDisciplinas();
				break;
			case 3:
				verTurmas();
				break;
			case 4:
				// testa se exista alunos criados
				if (banco.getAlunos().size() > 0) {
					System.out.println("Alunos:");
					banco.mostrarAlunos();
					System.out.print("Digite a matricula do aluno para mostrar o histórico: ");
					matricula = Integer.parseInt(recebeNumero());
					for (int i = 0; i < banco.getAlunos().size(); i++) {
						if (banco.getAlunos().get(i).getMatricula() == matricula) {
							System.out.println(banco.getAlunos().get(i).getHistorico());
						}
					}
				} else {
					System.out.println(" |!!| Não há alunos cadastrados.\n");
				}
				break;
			case 5:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println(" |!!| Opção inválida!\n");
				break;
			}
		}
	}

	public void verMembros() {
		int ope = 0;
		while (ope != 7) {
			System.out.println("----------------OPÇÕES------------------\n");
			System.out.println("1 - Ver professores da instituição");
			System.out.println("2 - Ver alunos da Instituição");
			System.out.println("3 - Matricular novo aluno da instituição ");
			System.out.println("4 - Desmatricular aluno da instituição");
			System.out.println("5 - Matricular novo professor na instituição");
			System.out.println("6 - Desmatricular professor da instituição");
			System.out.println("7 - Voltar");
			System.out.print(">> Opção: ");
			ope = Integer.parseInt(recebeNumero());
			System.out.println();

			switch (ope) {
			case 1:
				System.out.println("Professore(s):");
				banco.mostrarProfessores();
				break;
			case 2:
				System.out.println("Aluno(s):");
				banco.mostrarAlunos();
				break;
			case 3:
				Criar.matricularAluno();
				break;
			case 4:
				banco.mostrarAlunos();
				System.out.print("Informe a matrícula do aluno(a): ");
				matricula = Integer.parseInt(recebeNumero());
				banco.removeAluno(matricula);
				break;
			case 5:
				Criar.matricularProfessor();
				break;
			case 6:
				banco.mostrarProfessores();
				System.out.print("Informe a matrícula do professor: ");
				matricula = Integer.parseInt(recebeNumero());
				banco.removeProfessor(matricula);
				break;
			case 7:
				break;
			default:
				System.out.println(" |!!| Opção inválida!\n");
				break;
			}
		}
	}

	public void verDisciplinas() {
		int ope = 0;
		while (ope != 5) {
			System.out.println("----------------OPÇÕES------------------\n");
			System.out.println("1 - Cria nova disciplina");
			System.out.println("2 - Remover disciplina existente ");
			System.out.println("3 - Mostrar Disciplinas");
			System.out.println("4 - Mostrar Disciplinas cursadas no momento");
			System.out.println("5 - Voltar");
			System.out.print(">> Opção: ");
			ope = Integer.parseInt(recebeNumero());
			System.out.println();

			switch (ope) {
			case 1:
				Criar.criarDisciplina();
				break;
			case 2:
				System.out.println("Disciplina(s):");
				banco.mostrarDisciplinas();
				System.out.print("Informe o id da Disciplina: ");
				id = Integer.parseInt(recebeNumero());
				banco.removeDiciplina(id);
				break;
			case 3:
				System.out.println("Disciplina(s):");
				banco.mostrarDisciplinas();
				break;
			case 4:
				System.out.println("Alunos(as):");
				banco.mostrarAlunos();
				System.out.print("Informe a matricula do aluno: ");
				matricula = Integer.parseInt(recebeNumero());
				banco.mostrarDisciplinasAluno(matricula);
			case 5:
				break;
			default:
				System.out.println(" |!!| Opção inválida!\n");
				break;
			}
		}
	}

	public void verTurmas() {
		int ope = 0;
		while (ope != 7) {
			System.out.println("----------------OPÇÕES------------------\n");
			System.out.println("1 - Criar uma nova turma");
			System.out.println("2 - Encerrar turma ");
			System.out.println("3 - Remover turma existente ");
			System.out.println("4 - Adicionar alunos na turma");
			System.out.println("5 - Atribuir notas aos alunos");
			System.out.println("6 - Mostrar turmas");
			System.out.println("7 - Voltar");
			System.out.print(">> Opção: ");
			ope = Integer.parseInt(recebeNumero());
			System.out.println();

			switch (ope) {
			case 1:
				Criar.criarTurma();
				break;
			case 2:
				boolean existe = false;
				System.out.println("Turma(s):");
				banco.mostrarTurmas();
				System.out.print("Informe o id da Turma: ");
				id = Integer.parseInt(recebeNumero());
				for(Turma turma : banco.getTurma()) {
					if(turma.getId() == id) {
						banco.encerrarTurma(id);
						existe = true;
						break;}
				}
				if(existe == false) {System.out.println(" |!!| Não há Turma com esse ID!\n");}
				break;
			case 3:
				existe = false;
				System.out.println("Turma(s):");
				banco.mostrarTurmas();
				System.out.println("Insira a turma que deseja remover: ");
				id = Integer.parseInt(recebeNumero());
				for(Turma turma : banco.getTurma()) {
					if(turma.getId() == id) {
						banco.removeTurma(id);
						existe = true;
						break;}
				}
				if(existe == false) {System.out.println(" |!!| Não há Turma com esse ID!\n");}
				break;
			case 4:
				int i = 0;
				existe = false;
				System.out.println("Turma(s):");
				banco.mostrarTurmas();
				System.out.print("Digite id da turma: ");
				int idTurma = Integer.parseInt(recebeNumero());
				
				for(int j = 0; j < banco.getTurma().size(); j++) {
					if(banco.getTurma().get(j).getId() == idTurma) {
						existe = true;
						System.out.println("");

						while (i != 2) {
							System.out.println("Aluno(s):");
							banco.mostrarAlunos();
							System.out.print("\nDigite matricula do aluno: ");
							matricula = Integer.parseInt(recebeNumero());
							System.out.println("");

							banco.addAlunoTurma(matricula, idTurma);
							System.out.println("\t\t| 1 - Continuar		2 - Sair |");
							System.out.print("\n>> Opção: ");
							i = Integer.parseInt(recebeNumero());
							System.out.println("");
						}
						break;
						}
					}
				if(existe == false) {System.out.println(" |!!| Não há Turma com esse ID!\n");}
				break;
				
			case 5:
				existe = false;
                System.out.println("Turma(s) cadastrada(s): ");
                banco.mostrarTurmas();
                System.out.print("Informe o id da Turma: ");
                id = Integer.parseInt(recebeNumero());
                for (Turma turma : banco.getTurma()) {
                    if (turma.getId() == id) {
                        banco.adicionaMedia(id);
                        existe = true;
                        break;
                    }
                }
                if (existe == false) { System.out.println(" |!!| Não há Turma com esse ID!\n");}
                break;
			case 6:
				System.out.println("Turma(s):");
				banco.mostrarTurmas();
				break;
			case 7:
				break;
			default:
				System.out.println(" |!!| Opção inválida!\n");
				break;
			}
		}
	}
	
	// Esse metodo retorna uma string de tal modo que seja possivel comverter para inteiro ou double
	static public String recebeNumero() {
		String saida = "";
		Scanner scanner = new Scanner(System.in);
		String entrada = "";

		while (true) {
			entrada = scanner.nextLine();
			try {
				Integer i = new Integer(entrada);
				return saida += i;
			} catch (Exception e) {
				try {
					Double d = new Double(entrada);
					return saida += d;
				} catch (Exception r) {
					System.out.println(" |!!| Entrada inválida, digite novamente!");
					System.out.print("Opção: ");
				}
			}
		}
	}

}
