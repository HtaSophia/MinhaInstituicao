package projetolp2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Aluno {

    private final String nome;
    private final int matricula;
    private ArrayList<Turma> turmas; 
    private Map<Disciplina, Double> disciplinasMatriculadas, historico;
    static private int contMatricula = 1;
    
    public Aluno(String nome) {
        this.nome = nome;
        matricula = Integer.parseInt("1710" + contMatricula);
        disciplinasMatriculadas = new HashMap<>();
        historico = new HashMap<>();
        turmas = new ArrayList<>();
        contMatricula++;
    }
    
    //Retorna as disciplinas do aluno
    public String getDisciplinaAluno() { 
        String disciplinas = new String();
        
        Set<Disciplina> chaves = disciplinasMatriculadas.keySet();
        
        for (Disciplina disciplina : chaves) {
        	disciplinas += " | " + disciplina.getNome() + " |";
        } 
        return disciplinas;
    }
    
    //Retorna o historico do aluno
    public String getHistorico() { 
        String histo = new String();
        double media = 0.0;
        int cont = 0;

        Set<Disciplina> chaves = historico.keySet();

        for (Disciplina disciplina : chaves) {
            histo += "| " + disciplina.getNome() + " : " + historico.get(disciplina) + " |\n";
            media += historico.get(disciplina);
            cont++;
        }
        
        System.out.println("Media global: " + (media/cont));
        
        histo += ("Media global: " + (media/cont));
        return histo;
    }
    
    //Coloca uma disciplina no hashmap de discplinas cursadas
    public void setDisciplina(Disciplina disciplina) { 
    	double media = -1;
    	boolean existe = false;
    
        Set<Disciplina> chaves = historico.keySet();
        
        for (Disciplina hist : chaves) {
            if(hist.getNome().equals(disciplina.getNome()) ) {
            	media = historico.get(hist);
            }
        }	  
        
        Set<Disciplina> chavesM = disciplinasMatriculadas.keySet();
        for(Disciplina disc : chavesM) {
        	if(disciplina.getNome().equals(disc.getNome())) {
        		existe = true;
        	}

        }
        
        if(media < 5 && disciplinasMatriculadas.size() < 3 && existe == false) {
        	disciplinasMatriculadas.put(disciplina, -1D);
        }else {
        	System.out.println(" |!!| Não pode se matricular nessa disciplina");
        }
    	
        
    }
    
    // Recebe um objeto do tipo disciplina, um valor em double e altera o valor dessa disciplina no hashmap
    public void setMedia(Disciplina disciplina, double media) {
        disciplinasMatriculadas.put(disciplina, media);
    }
    
    
    // Recebe um objeto do tipo disciplina, adiciona essa disciplina ao historico e remove das disciplinas cursadas
    public void setHistorico(Disciplina disciplina) {
       
        Set<Disciplina> chaves = disciplinasMatriculadas.keySet();
        
        for (Disciplina disc : chaves) {
        	if(disc.getNome().equals(disciplina.getNome())) {
        		historico.put(disc, disciplinasMatriculadas.get(disc));
        		break;
        	}
        }
        
        disciplinasMatriculadas.remove(disciplina);
        
    }
    

    public String getNome() {
        return this.nome;
    }

    public ArrayList<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(ArrayList<Turma> turmas) {
		this.turmas = turmas;
	}

	public int getMatricula() {
        return this.matricula;
    }


	public Map<Disciplina, Double> getDisciplina_Media() {
		return disciplinasMatriculadas;
	}


	public void setDisciplina_Media(Map<Disciplina, Double> disciplina_Media) {
		this.disciplinasMatriculadas = disciplina_Media;
	}
	
	
    
}
