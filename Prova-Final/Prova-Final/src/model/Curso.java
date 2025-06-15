package model;
import java.util.List;
import java.util.ArrayList;

// classe curso
public class Curso {

    //atributos
    private String nome;
    private String descricao;
    private Professor professorResponsavel;
    private List<Modulo> modulos;
    private List<Aluno> alunosMatriculados;

    // construtores
    public Curso(String nome, String descricao, Professor professorResponsavel) {
        this.nome = nome;
        this.descricao = descricao;
        this.professorResponsavel = professorResponsavel;
        this.modulos = new ArrayList<>();
        this.alunosMatriculados = new ArrayList<>();
    }

    // Getters e setters
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getDescricao() {return descricao;}
    public void setDescricao(String descricao) {this.descricao = descricao;}

    public Professor getProfessorResponsavel() {return professorResponsavel;}
    public void setProfessorResponsavel(Professor professorResponsavel) {this.professorResponsavel = professorResponsavel;}

    public List<Modulo> getModulos() {return modulos;}
    public void setModulos(List<Modulo> modulos) {this.modulos = modulos;}

    public List<Aluno> getAlunosMatriculados() {return alunosMatriculados;}
    public void setAlunosMatriculados(List<Aluno> alunosMatriculados) {this.alunosMatriculados = alunosMatriculados;}

    // Métodos para manipular a lista de módulos e alunos
    public void adicionarModulo(Modulo modulo) { 
        this.modulos.add(modulo);
    }

    public void matricularAluno(Aluno aluno) { 
        this.alunosMatriculados.add(aluno); 
    }
}
// não tenho certeza se precisa de algo mais aqui