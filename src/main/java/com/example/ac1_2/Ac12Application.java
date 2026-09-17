package com.example.ac1_2;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ac1_2.model.Diretor;
import com.example.ac1_2.model.Filme;
import com.example.ac1_2.repository.DiretorRepository;
import com.example.ac1_2.repository.FilmeRepository;

@SpringBootApplication
public class Ac12Application {

    public static void main(String[] args) {
        SpringApplication.run(Ac12Application.class, args);
    }

    @Bean
    CommandLineRunner testar(
            FilmeRepository filmeRepository,
            DiretorRepository diretorRepository) {

        return args -> {

            // ==========================================
            // CRIANDO OS DIRETORES
            // ==========================================
            Diretor diretor1 = new Diretor();
            diretor1.setNome("James Cameron");

            Diretor diretor2 = new Diretor();
            diretor2.setNome("Christopher Nolan");

            // ==========================================
            // CRIANDO OS FILMES DO JAMES CAMERON
            // ==========================================
            Filme filme1 = new Filme();
            filme1.setTitulo("Avatar");
            filme1.setDuracao(162);
            filme1.setDiretor(diretor1);

            Filme filme2 = new Filme();
            filme2.setTitulo("Titanic");
            filme2.setDuracao(195);
            filme2.setDiretor(diretor1);

            // ==========================================
            // CRIANDO OS FILMES DO CHRISTOPHER NOLAN
            // ==========================================
            Filme filme3 = new Filme();
            filme3.setTitulo("Oppenheimer");
            filme3.setDuracao(180);
            filme3.setDiretor(diretor2);

            Filme filme4 = new Filme();
            filme4.setTitulo("Interestelar");
            filme4.setDuracao(169);
            filme4.setDiretor(diretor2);

            // ==========================================
            // ASSOCIANDO OS FILMES AOS DIRETORES
            // ==========================================
            diretor1.getFilmes().add(filme1);
            diretor1.getFilmes().add(filme2);

            diretor2.getFilmes().add(filme3);
            diretor2.getFilmes().add(filme4);

            // ==========================================
            // SALVANDO OS DIRETORES
            // ==========================================
            diretorRepository.save(diretor1);
            diretorRepository.save(diretor2);

            // ==========================================
            // TESTE DOS DIRETORES
            // ==========================================
            System.out.println();
            System.out.println("========================================");
            System.out.println("DIRETORES CADASTRADOS");
            System.out.println("========================================");

            diretorRepository.findAll().forEach(diretor -> {

                System.out.println("Diretor: " + diretor.getNome());

                diretor.getFilmes().forEach(filme -> {

                    System.out.println(
                            "  Filme: " + filme.getTitulo()
                            + " | Duração: " + filme.getDuracao()
                    );

                });

            });

            // ==========================================
            // TESTE findByNomeStartingWith
            // ==========================================
            System.out.println();
            System.out.println("========================================");
            System.out.println("DIRETORES QUE COMEÇAM COM 'J'");
            System.out.println("========================================");

            List<Diretor> diretoresComJ
                    = diretorRepository.findByNomeStartingWith("J");

            diretoresComJ.forEach(diretor -> {

                System.out.println(
                        "ID: " + diretor.getId()
                        + " | Nome: " + diretor.getNome()
                );

            });

            // ==========================================
            // TESTE FILMES COM DURAÇÃO > 170
            // ==========================================
            System.out.println();
            System.out.println("========================================");
            System.out.println("FILMES COM DURAÇÃO MAIOR QUE 170");
            System.out.println("========================================");

            List<Filme> filmesMaiores
                    = filmeRepository.findByDuracaoGreaterThan(170);

            filmesMaiores.forEach(filme -> {
                System.out.println(
                        filme.getTitulo()
                        + " - "
                        + filme.getDuracao()
                        + " minutos"
                        + " - Diretor: "
                        + filme.getDiretor().getNome()
                );
            });

            // ==========================================
            // TESTE FILMES COM DURAÇÃO <= 170
            // ==========================================
            System.out.println();
            System.out.println("========================================");
            System.out.println("FILMES COM DURAÇÃO MENOR OU IGUAL A 170");
            System.out.println("========================================");

            List<Filme> filmesMenoresOuIguais
                    = filmeRepository.findByDuracaoLessThanEqual(170);

            filmesMenoresOuIguais.forEach(filme -> {
                System.out.println(
                        filme.getTitulo()
                        + " - "
                        + filme.getDuracao()
                        + " minutos"
                        + " - Diretor: "
                        + filme.getDiretor().getNome()
                );
            });

            // ==========================================
            // TESTE FILMES QUE COMEÇAM COM "O"
            // ==========================================
            System.out.println();
            System.out.println("========================================");
            System.out.println("FILMES QUE COMEÇAM COM 'O'");
            System.out.println("========================================");

            List<Filme> filmesComO
                    = filmeRepository.findByTituloStartingWith("O");

            filmesComO.forEach(filme -> {
                System.out.println(
                        filme.getTitulo()
                        + " - Diretor: "
                        + filme.getDiretor().getNome()
                );
            });
        };
    }
}
