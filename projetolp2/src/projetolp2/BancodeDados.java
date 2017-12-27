package projetolp2;

import java.util.ArrayList;

public class BancodeDados {

	private ArrayList<Aluno> alunos = new ArrayList<>();
	private ArrayList<Professor> professores = new ArrayList<>();
	private ArrayList<Turma> turmas = new ArrayList<>();
	private ArrayList<Disciplina> disciplinas = new ArrayList<>();

	public BancodeDados() {
		Aluno a1 = new Aluno("João");
		Professor p1 = new Professor("Geraldo");
		Disciplina d1 = new Disciplina("Matemática");

		alunos.add(a1);
		professores.add(p1);
		disciplinas.add(d1);
	}

	public void addAluno(Aluno aluno) {

		alunos.add(aluno);

	}

	public Aluno getAluno(int matricula) {

		return alunos.get(matricula);

	}

	// Mostra todos os alunos cadastrados
	public void mostrarAlunos() {
		for (Aluno aluno : alunos) {
			System.out.println("    Matricula: " + aluno.getMatricula() + " - Nome: " + aluno.getNome());
		}
	}

	// Recebe um valor inteiro(matricula) e remove o aluno correspondente a esta matricula
	public void removeAluno(int matricula) {
		boolean removido = false;
		for( Aluno aluno : alunos){
			if (aluno.getMatricula() == matricula) {
				if (aluno.getTurmas().size() == 0)  {
					alunos.remove(aluno);
					System.out.println("Aluno removido com sucesso!\n");
					removido = true;
					break;
				}
				else {
					for (int j = 0; j < aluno.getTurmas().size(); j++) {
						aluno.getTurmas().get(j).getAlunos().remove(aluno);
						removido = true;
					}
					alunos.remove(aluno);
					removido = true;
					System.out.println("Aluno removido com sucesso!");
					break;
				}
			}
		}
		if (removido == false) {
			System.out.println(" |!!| Aluno não encontrado!\n");
		}
	}
		
	// --------------------------------------------------------------------- PROFESSOR
	public Professor getProfessor(int matricula) {

		for (Professor prof : professores) {
			if (prof.getMatricula() == matricula) {
				return prof;
			}
		}
		System.out.println(" |!!| Não há professor com esta matricula!\n");
		return null;

	}

	public ArrayList<Professor> getProfessor() {
		return this.professores;
	}

	public void addProfessor(Professor professor) {

		professores.add(professor);

	}

	// Mostra todos os professores cadastrados
	public void mostrarProfessores() {
		for (Professor professor : professores) {
			System.out.println("     " + professor.getMatricula() + " - " + professor.getNome());
		}
	}

	// Recebe um valor inteiro(matricula) e remove o professor correspondente a matricula
	public void removeProfessor(int matricula) {
		Professor professor = null;
		boolean existe = false;

		// verufica se existe professor criado
		if (professores.size() > 0) {
			for (Professor prof : professores) {
				if (prof.getMatricula() == matricula) {
					professor = prof;
					break;
				}
			}

			// verifica se o professor estar em alguma turma
			if (professor.getQuantTurma() > 0) {
				for (Turma turma : professor.getTurmas()) {
					for (Aluno aluno : turma.getAlunos()) {
						if(aluno.getDisciplina_Media().get(turma.getDisciplina()) == -1) {
							existe = true;
							break;
						}
					}
				}

				if (existe) {
					System.out.println(" |!!| Há aluno(s) sem nota");
				} else {
					for(Turma turma : professor.getTurmas()) {
						Menu.banco.encerrarTurma(turma.getId());
					}
					
					professores.remove(professor);
					System.out.println("Professor removido.");

				}
			} else {
				System.out.println(" |!!| Não há turmas vinculadas a professor!");
				professores.remove(professor);
				System.out.println("Professor removido.");
			}
		} else {
			System.out.println(" |!!| Não há professores cadastrados!");
		}

	}

	// ---------------------------------------------------------------------- DISCIPLINA
	public void addDisciplina(Disciplina disciplina) {

		disciplinas.add(disciplina);

	}

	public Disciplina getDisciplina(int id) {
		for (int i = 0; i < disciplinas.size(); i++) {
			if (disciplinas.get(i).getId() == id) {
				return disciplinas.get(i);
			}
		}

		System.out.println(" |!!| Não existe disciplina com esse id");
		return null;

	}

	public void mostrarDisciplinas() {
		for (Disciplina disciplina : disciplinas) {
			System.out.println("     " + disciplina.getId() + " - " + disciplina.getNome());
		}
	}

	// Recebe um inteiro(matricula) e mostra as disciplinas que o aluno com esta matricula esta cursando
	public void mostrarDisciplinasAluno(int matricula) {
		boolean existe = false;
		for (Aluno aluno : alunos) {
			if (aluno.getMatricula() == matricula) {
				if (aluno.getDisciplina_Media().size() == 0) {
					System.out.println("Aluno não esta cursando nenhuma disciplina.\n");
				}
				else {
					System.out.println("     " + aluno.getDisciplinaAluno());
					existe = true;}
			}
		}
		if (!existe) {
			System.out.println(" |!!| Aluno não matriculado!");
		}
	}

	// Recebe um inteiro(id) e remove a disciplina com o id correspondente
	public void removeDiciplina(int id) {
		if (disciplinas.size() > 0) {
			for (int i = 0; i < disciplinas.size(); i++) {
				if (disciplinas.get(i).getId() == id) {
					disciplinas.remove(i);
					System.out.println("Disciplina removida com sucesso!");
					break;
				}
			}
		} else {
			System.out.println(" |!!| Não há disciplinas cadastradas!");
		}
	}
	

	// ------------------------------------------------------------------------------- TURMA
	public void addTurma(Turma turma) {

		turmas.add(turma);

	}

	public Turma getTurma(int id) {

		return turmas.get(id);

	}

	public ArrayList<Turma> getTurma() {

		return turmas;

	}

	// Mostra todas as turmas criadas
	public void mostrarTurmas() {
		for (Turma turma : turmas) {
			System.out.println("Turma " + turma.getId() + ":  Disciplina: " + turma.getDisciplina().getNome() + " - Professor: " + turma.getProfessor().getNome() );
			for (Aluno aluno : turma.getAlunos()) {
				System.out.println("  Aluno:    Matricula: " + aluno.getMatricula() + " - Nome: " + aluno.getNome());
			}
			System.out.println();
		}
	}

	// Recebe um inteiro(id) e remove a turma correspondente a este id
	public void removeTurma(int id) {
		for (Turma turma : turmas) {
			if (turma.getId() == id) {
				turmas.remove(turma);
			}
		}
	}
	
	public void encerrarTurma(int id) {
		boolean alunoNota = false;
		Turma t = null;

		// verifica se existe algum aluno com media -1, que no caso significa que não
		// foi atribuido media a ele
		for (Turma turma : turmas) {
			if (turma.getId() == id) {
				t = turma;
				for (Aluno aluno : turma.getAlunos()) {
					if (aluno.getDisciplina_Media().get(turma.getDisciplina()) == -1) {
						alunoNota = true;
						break;
					}
				}
			}
		}

		if (t == null) {
			System.out.println(" |!!| Não existe turma com esse ID!");
		} else {
			if (alunoNota) {
				System.out.println(" |!!| Essa turma contém alunos sem nota.");
			} else {
				for (Aluno aluno : alunos) {
					aluno.setHistorico(t.getDisciplina());
				}
				Menu.banco.getTurma().remove(t);
				System.out.println("Turma encerrada!\n");
			}
		}

	}

	// Recebe dois inteiros(matricula e id) e adiciona o aluno com esta matricula na turma com este id
	public void addAlunoTurma(int matricula, int id) {
		boolean existe = false;

		if (alunos.size() > 0) {
			for(Turma turma : turmas){
                if(turma.getId() == id){
                    for(Aluno aluno : turma.getAlunos()){
                    	// Verifica se o aluno já está matrículado na turma
                        if(aluno.getMatricula() == matricula){
                        	existe = true;
                            System.out.println(" |!!| Este aluno já esta matriculado nesta turma!\n");
                            break;
                        }
                    }
                    
                    if(!existe) {
                    	for(Aluno aluno : alunos) {
							if (aluno.getMatricula() == matricula) {
								existe = true;
								// Caso não esteja matriculado ainda, o aluno é adicionado a turma
								
								if (aluno.getTurmas().size() < 3) {
									turma.addAluno(aluno);
									aluno.getTurmas().add(turma);
									aluno.setDisciplina(turma.getDisciplina());
									System.out.println("Aluno adicionado com sucesso!\n");
									break;
								} else {
									System.out.println(" |!!| Aluno já está matriculado em 3 turmas!\n");
									break;
								}
							}
                    	}
                    	break;
                	}
            	}
			}
			if (!existe) {System.out.println(" |!!| Não há alunos com esta matrícula!\n");}
		}
		else {System.out.println(" |!!| Não há alunos para serem adicionados!\n");}
	}
	
	public void adicionaMedia(int id) {
        double media;
        Turma t = this.getTurma(id);
        for (Aluno aluno : t.getAlunos()) {
            System.out.println("Digite a média para o aluno(a): " + aluno.getMatricula() + " - " + aluno.getNome());
            System.out.print("Nota: ");
            media = Double.parseDouble(Menu.recebeNumero());
            aluno.setMedia(t.getDisciplina(), media);
            System.out.println("Média cadastrada com sucesso!");
        }
    }

	public ArrayList<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public ArrayList<Aluno> getAlunos() {
		return this.alunos;
	}

}