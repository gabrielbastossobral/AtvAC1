package com.example.atividade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.atividade.model.Professor;
import com.example.atividade.model.Curso;
import com.example.atividade.repository.ProfessorRepository;
import com.example.atividade.repository.CursoRepository;

@SpringBootApplication
public class AtividadeApplication {

	@Bean
	public CommandLineRunner init(
			@Autowired CursoRepository cursoRepository,
			@Autowired ProfessorRepository professorRepository) {
		return args -> {
			System.out.println("*** TESTANDO INSERIR ***");
			Curso c = new Curso(
				(long) 0,
				"ADS",
				2200,
				"objetivos",
				"ementa",
				"datainicio",
				"datafim",
				"horarioinicio",
				"horariofim",
				"cidade",
				"estado",
				12345789
			);
			cursoRepository.inserir(c); // POST

			Professor professor = new Professor(
				(long) 0,
				"nome",
				"cpf",
				"rg",
				"endereco",
				"celular"
			);
			professorRepository.inserir(professor); // POST

			
			System.out.println("*** TESTANDO OBTER POR ID ***");
			c = cursoRepository.obterPorId(1); // GET
			System.out.println(c.getDescricao()); // GET


			System.out.println("*** TESTANDO EDITAR ***");
			c.setDescricao("Análise e Desenvolvimento de Sistemas");
			professor = professorRepository.obterTodos().get(0);
			c.setProfessor(professor);
			cursoRepository.editar(c); // PUT
			c = cursoRepository.obterPorId(1); // GET
			System.out.println(c.getDescricao()); // GET

			System.out.println("*** TESTANDO LISTAR TODOS ***");
			cursoRepository.inserir(new Curso(
				(long) 0,
				"ADS",
				2200,
				"objetivos",
				"ementa",
				"datainicio",
				"datafim",
				"horarioinicio",
				"horariofim",
				"cidade",
				"estado",
				12345789
			)); // POST
			List<Curso> cursos = cursoRepository.obterTodos();
			for (Curso curso : cursos) {
				System.out.println(curso.getDescricao());
			}
			
			System.out.println("*** TESTANDO POR NOME ***");
			cursos = cursoRepository.obterPorNome("Análise%");
			for (Curso curso : cursos) {
				System.out.println(curso.getDescricao());
			}
			
			System.out.println("*** TESTANDO EXCLUIR ***");
			cursoRepository.excluir(2); // DELETE
			cursos = cursoRepository.obterTodos();
			for (Curso curso : cursos) {
				System.out.println(curso.getDescricao());
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(AtividadeApplication.class, args);
	}

}
