package projetolp2;

public class Disciplina {
	
	private String nome;
	private int id;
	private static int contId = 0;
	
	public Disciplina(String nome) {
		this.setNome(nome);
		this.id = contId++;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
