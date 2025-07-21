package io.github.andreynicollas.api_libary;

import io.github.andreynicollas.api_libary.model.Autor;
import io.github.andreynicollas.api_libary.repository.AutorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		var context = SpringApplication.run(Application.class, args);
		AutorRepository repository = context.getBean(AutorRepository.class);

		exemploSalvarRegistro(repository);
	}

	public static void exemploSalvarRegistro(AutorRepository autorRepository) {
		Autor autor = new Autor();
		autor.setNome("Andrey Nicollas");
		autor.setNacionalidade("Brasileiro");
		autor.setDataNascimento(LocalDate.of(2000, 4, 27));

		var autorSalvo = autorRepository.save(autor);
		System.out.println("Autor salvo: " + autorSalvo);
	}
}
