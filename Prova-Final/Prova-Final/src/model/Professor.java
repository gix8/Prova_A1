package model;
public class Professor extends Usuario{

    private String cursoM;

    public Professor(String nome, String gmail, String senha, String cursoM) {
        super(nome, gmail, senha);
        this.cursoM = cursoM;
    }

    public String getCursoM() {return cursoM;}
    public void setCursoM(String cursoM) {this.cursoM = cursoM;}

    

}
//é para estar pronto também, mas confere pfv.