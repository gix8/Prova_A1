package model;
public class Aula {

    
    private String titulo;
    private String conteudo;
    private int duracao; // duração em minutos

    
    public Aula(String titulo, String conteudo, int duracao) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.duracao = duracao;
    }


    public String getTitulo() {return titulo;}

    public void setTitulo(String titulo) {this.titulo = titulo;}

    public String getConteudo() {return conteudo;}

    public void setConteudo(String conteudo) {this.conteudo = conteudo;}

    public int getDuracao() {return duracao;}

    public void setDuracao(int duracao) {this.duracao = duracao;}

}
