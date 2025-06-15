package model;
// classe aluno que estende super-classe usuario

public class Aluno extends Usuario{

    //atributos da classe aluno
    private String rgm;
    private String curso;

    // construtores da classe aluno
    public Aluno(String nome, String gmail, String senha, String rgm, String curso) {
        super(nome, gmail, senha);
        this.rgm = rgm;
        this.curso = curso;
    }
    
    // Getters e Setters da classe aluno
    public String getRgm() {return rgm;}
    public void setRgm(String rgm) {this.rgm = rgm;}

    public String getCurso() {return curso;}
    public void setCurso(String curso) {this.curso = curso;}

    
    
}
// aqui também precisa acabar xis, mas já ta meio caminho andado