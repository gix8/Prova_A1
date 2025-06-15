package model;
import java.util.ArrayList;
import java.util.List;


public class Modulo {


    private String titulo;
    private String descricao;
    private List<Aula> aulas;

    // Construtores padrão
     public Modulo(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.aulas = new ArrayList<>();
    }

    // Getters e Setters padrão
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public List<Aula> getAulas() { return aulas; }
    public void setAulas(List<Aula> aulas) { this.aulas = aulas; }

    // Método para cadastrar uma aula
    public void adicionarAula(Aula aula) {
        aulas.add(aula);
    }

}

