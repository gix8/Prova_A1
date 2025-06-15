package controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Aluno;
import model.Professor;
import model.Curso;
import model.Modulo;
import model.Aula;

/**
 * ArquivoController é responsável por salvar os cadastros de alunos, professores e cursos em arquivos de texto.
 * Ele formata os dados de forma que possam ser facilmente lidos e processados posteriormente.
 */
public class ArquivoController {

    // Constantes para os nomes dos arquivos
    // Define os nomes dos arquivos onde os cadastros e cursos serão salvos
    private static final String FILE_CADASTROS = "Cadastros.txt";
    private static final String FILE_CURSOS = "Cursos.txt";

    
    public void salvarCadastros(List<Aluno> alunos, List<Professor> professores) {
        // try with resources para garantir que o BufferedWriter seja fechado automaticamente
        // Cria ou sobrescreve o arquivo Cadastros.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_CADASTROS))) {
           
            if (alunos != null) {
                for (Aluno aluno : alunos) {
                    // Formato: ALUNO;nome;gmail;rgm;curso
                    // sanitize o nome, gmail, rgm e curso para evitar problemas com ponto e vírgula
                    String line = String.format("ALUNO;%s;%s;%s;%s",
                            sanitize(aluno.getNome()),
                            sanitize(aluno.getGmail()),
                            sanitize(aluno.getRgm()),
                            sanitize(aluno.getCurso()));
                            // Verifica se o curso é nulo para evitar NullPointerException
                    // Se o curso for nulo, usa uma string vazia
                    writer.write(line);
                    writer.newLine();
                }
            }
           
            if (professores != null) {
                for (Professor prof : professores) {
                    // Formato: PROFESSOR;nome;gmail;cursoMinistrado
                    // sanitize o nome, gmail e curso ministrado para evitar problemas com ponto e vírgula
                    String line = String.format("PROFESSOR;%s;%s;%s",
                            sanitize(prof.getNome()),
                            sanitize(prof.getGmail()),
                            sanitize(prof.getCursoM()));
                            // Verifica se o curso ministrado é nulo para evitar NullPointerException
                    // Se o curso ministrado for nulo, usa uma string vazia
                    writer.write(line);
                    writer.newLine();
                }
            }
            System.out.println("Arquivo '" + FILE_CADASTROS + "' salvo com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo '" + FILE_CADASTROS + "': " + e.getMessage());
        }
    }

    public void salvarCursos(List<Curso> cursos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_CURSOS))) {
            if (cursos != null) {
                for (Curso curso : cursos) {
                    // formato: CURSO;nome;descricao;professorNome
                    // sanitize o nome, descrição e nome do professor para evitar problemas com ponto e vírgula
                    // Verifica se o professor responsável é nulo para evitar NullPointerException
                    String professorNome = curso.getProfessorResponsavel() != null
                            ? curso.getProfessorResponsavel().getNome()
                            // Se o professor for nulo, usa uma string vazia
                            // para evitar problemas de formatação
                            : "";
                    String cursoLine = String.format("CURSO;%s;%s;%s",
                            sanitize(curso.getNome()),
                            sanitize(curso.getDescricao()),
                            sanitize(professorNome));
                    writer.write(cursoLine);
                    writer.newLine();

                    List<Modulo> modulos = curso.getModulos();
                    if (modulos != null) {
                        for (Modulo modulo : modulos) {
                            // formato: MODULO;cursoNome;moduloTitulo;moduloDescricao
                            // sanitize o título e descrição do módulo para evitar problemas com ponto e vírgula
                            String moduloLine = String.format("MODULO;%s;%s;%s",
                                    sanitize(curso.getNome()),
                                    sanitize(modulo.getTitulo()),
                                    sanitize(modulo.getDescricao()));
                            writer.write(moduloLine);
                            writer.newLine();

                            List<Aula> aulas = modulo.getAulas();
                            if (aulas != null) {
                                for (Aula aula : aulas) {
                                    // formato: AULA;cursoNome;moduloTitulo;aulaTitulo;conteudo;duracao
                                    // sanitize o conteúdo para evitar problemas com ponto e vírgula
                                    String aulaLine = String.format("AULA;%s;%s;%s;%s;%d",
                                            sanitize(curso.getNome()),
                                            sanitize(modulo.getTitulo()),
                                            sanitize(aula.getTitulo()),
                                            sanitize(aula.getConteudo()),
                                            aula.getDuracao());
                                    writer.write(aulaLine);
                                    writer.newLine();
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("Arquivo '" + FILE_CURSOS + "' salvo com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo '" + FILE_CURSOS + "': " + e.getMessage());
        }
    }

    
    private String sanitize(String input) {
        // Substitui ponto e vírgula por vírgula para evitar problemas de formatação, método sugerido pelo BLACKBOX
        if (input == null) return "";
        return input.replace(";", ",");
    }

      public void listarCadastros() {
        List<Aluno> alunos = new ArrayList<>();
        List<Professor> professores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_CADASTROS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Divide a linha em partes usando ponto e vírgula como delimitador
                String[] parts = line.split(";", -1);
                if (parts.length > 0) {
                    // Verifica o tipo de cadastro (ALUNO ou PROFESSOR)
                    String tipo = parts[0].trim();
                    if ("ALUNO".equalsIgnoreCase(tipo)) {
                        // formato: ALUNO;nome;gmail;rgm;curso
                        // verifica se o numero de partes é de acordo com o formato do aluno
                        if (parts.length >= 5) {
                            Aluno aluno = new Aluno(parts[1], parts[2], "", parts[3], parts[4]);
                            alunos.add(aluno);
                            // Adiciona o aluno à lista de alunos para ser exibido na proxima etapa
                        }} else if ("PROFESSOR".equalsIgnoreCase(tipo)) {
                        // formatp: PROFESSOR;nome;gmail;cursoMinistrado
                        // verifica se o numero de partes é de acordo com o formato do professor
                        if (parts.length >= 4) {
                            Professor prof = new Professor(parts[1], parts[2], "", parts[3]);
                            professores.add(prof);
                            // Adiciona o professor à lista de professores para ser exibido na proxima etapa
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo '" + FILE_CADASTROS + "': " + e.getMessage() + "lembre-se de salvar os cadastros antes de listar");
            return;
        }
        System.out.println("\n=== Lista de Alunos Cadastrados ===");
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
        } else {
            for (Aluno aluno : alunos) {
                // Formata a saída para exibir os dados do aluno
                System.out.printf("Nome: %s | Gmail: %s | RGM: %s | Curso: %s%n",
                        aluno.getNome(), aluno.getGmail(), aluno.getRgm(), aluno.getCurso());
            }
            }
        System.out.println("\n=== Lista de Professores Cadastrados ===");
        if (professores.isEmpty()) {
            System.out.println("Nenhum professor cadastrado.");
        } else {
            for (Professor professor : professores) {
                // Formata a saída para exibir os dados do professor
                System.out.printf("Nome: %s | Gmail: %s | Curso ministrado: %s%n",
                        professor.getNome(), professor.getGmail(), professor.getCursoM());
            }
        }
    }

     public void listarCursos() {
        // Lê o arquivo de cursos e módulos, e exibe os cursos cadastrados
        // Cria uma lista para armazenar os cursos de modo que os módulos e aulas possam ser associados corretamente
        // Os modulos são associados aos cursos e as aulas aos módulos
        List<Curso> cursos = new ArrayList<>();
        Curso currentCurso = null;
        Modulo currentModulo = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_CURSOS))) {
            // try with resources para garantir que o BufferedReader seja fechado automaticamente
            // Lê o arquivo linha por linha
            String line;
            while ((line = reader.readLine()) != null) {
                // Remove os caracteres de quebra de linha
                String[] parts = line.split(";", -1);
                if (parts.length == 0) continue;
                String tipo = parts[0].trim();
                switch (tipo.toUpperCase()) {
                    case "CURSO":
                        // CURSO;nome;descricao;professorNome
                        // Verifica se o número de partes é de acordo com o formato do curso
                        if (parts.length >= 4) {
                            String nome = parts[1];
                            String descricao = parts[2];
                            String professorNome = parts[3];
                            // Cria um placeholder para o professor
                            Professor professor = new Professor(professorNome, "", "", "");
                            currentCurso = new Curso(nome, descricao, professor);
                            // Adiciona o curso à lista de cursos
                            cursos.add(currentCurso);
                            // Reseta o módulo atual
                            currentModulo = null;
                        }
                         break;
                    case "MODULO":
                        // MODULO;cursoNome;moduloTitulo;moduloDescricao
                        // Verifica se o número de partes é de acordo com o formato do módulo
                        if (parts.length >= 4 && currentCurso != null) {
                            String cursoNome = parts[1];
                            // Verifica se o curso corresponde ao atual
                            // Se o curso atual for nulo, não adiciona o módulo
                            if (cursoNome.equals(currentCurso.getNome())) {
                                String titulo = parts[2];
                                String descricao = parts[3];
                                // Cria um novo módulo
                                currentModulo = new Modulo(titulo, descricao);
                                // Adiciona o módulo ao curso atual
                                currentCurso.adicionarModulo(currentModulo);
                            }
                        }
                        break;
                    case "AULA":
                        // AULA;cursoNome;moduloTitulo;aulaTitulo;conteudo;duracao
                        // Verifica se o número de partes é de acordo com o formato da aula
                        if (parts.length >= 6 && currentCurso != null && currentModulo != null) {
                            String cursoNome = parts[1];
                            String moduloTitulo = parts[2];
                            // Verifica se o curso e o módulo correspondem ao atual
                            // Se o curso ou módulo atual for nulo, não adiciona a aula
                            if (cursoNome.equals(currentCurso.getNome()) && moduloTitulo.equals(currentModulo.getTitulo())) {
                                String titulo = parts[3];
                                String conteudo = parts[4];
                                int duracao = 0;
                                try {
                                    duracao = Integer.parseInt(parts[5]);
                                    // Verifica se a duração é um número válido
                                } catch (NumberFormatException e) {
                                    duracao = 0;
                                }
                                // Cria uma nova aula
                                Aula aula = new Aula(titulo, conteudo, duracao);
                                currentModulo.adicionarAula(aula);
                            }
                        }
                        break;
                    default:
                        
                        break;
                        }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo '" + FILE_CURSOS + "': " + e.getMessage() + "lembre-se de salvar os cursos antes de listar");
            return;
        }
        System.out.println("\n=== Lista de Cursos Cadastrados ===");
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso cadastrado.");
            return;
        }
        for (Curso curso : cursos) {
            // Formata a saída para exibir os dados do curso
            System.out.printf("Curso: %s%nDescrição: %s%nProfessor Responsável: %s%n",
                    curso.getNome(), curso.getDescricao(),
                    curso.getProfessorResponsavel() != null ? curso.getProfessorResponsavel().getNome() : "N/A");
            List<Modulo> modulos = curso.getModulos();
            // Verifica se o curso tem módulos cadastrados
            // Se não tiver módulos, exibe uma mensagem informando
            if (modulos == null || modulos.isEmpty()) {
                System.out.println("  Nenhum módulo cadastrado neste curso.");
            } else {
                System.out.println("  Módulos:");
                for (Modulo modulo : modulos) {
                    // Formata a saída para exibir os dados do módulo
                    System.out.printf("    - %s: %s%n", modulo.getTitulo(), modulo.getDescricao());
                    List<Aula> aulas = modulo.getAulas();
                    // Verifica se o módulo tem aulas cadastradas
                    if (aulas == null || aulas.isEmpty()) {
                        System.out.println("      Nenhuma aula cadastrada neste módulo.");
                    } else {
                        System.out.println("      Aulas:");
                        for (Aula aula : aulas) {
                            // Formata a saída para exibir os dados da aula
                            System.out.printf("        * %s (Duração: %d min)%n", aula.getTitulo(), aula.getDuracao());
                        }
                    }
                }
            }
            // Adiciona uma linha em branco para separar os cursos
            System.out.println();
        }
    }
    
}

