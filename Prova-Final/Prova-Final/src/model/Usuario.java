package model;
// Super-classe usuario
public abstract class Usuario {

    //atributos da super-classe usuario
    protected String nome;
    protected String gmail;
    protected String senha;
    
    //construtores da super classe
    public Usuario(String nome, String gmail, String senha) {
        this.nome = nome;
        this.gmail = gmail;
        this.senha = senha;
    }

    // Getters e setters da super classe
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getGmail() {return gmail;}
    public void setGmail(String gmail) {this.gmail = gmail;}

    public String getSenha() {return senha;}
    public void setSenha(String senha) {this.senha = senha;}

}

//super classe aparentemente está feita, corrija se tiver errado plis
