package com.example.libros;

import com.example.libros.models.Libro;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.example.libros.services.ServicioEditoriales;
import com.example.libros.services.ServicioLibros;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.time.LocalDate;
import java.util.Locale;

@SpringBootApplication
public class LibrosApplication {
	@Autowired
	ServicioLibros servicioLibros;
	@Autowired
	ServicioEditoriales servicioEditoriales;

	public static void main(String[] args) {

		//AQUI PONGO XAMPP1 PORQUE ES MI RUTA Y EN MI PC SE LLAMA XAMPP1
		try {
			//comando para iniciar Apache
			String startApacheCmd = "c:\\xampp1\\apache_start.bat";

			//comando para iniciar MySQL
			String startMySqlCmd = "c:\\xampp1\\mysql_start.bat";

			//Esto ejecuta los comandos
			Runtime.getRuntime().exec(startApacheCmd);
			Runtime.getRuntime().exec(startMySqlCmd);

		} catch (Exception e) {
			e.printStackTrace();
		}
		SpringApplication.run(LibrosApplication.class, args);
	}


	@Bean
	CommandLineRunner ponLibros(){
		return args -> {

			Faker faker = new Faker(new Locale("es-ES"));
			//si tenemos menos de 5, generamos otros 5
			if(servicioLibros.findAll().size()<5) {
				for (int i = 0; i < 5; i++) {
					Libro l = new Libro();
					l.setTitulo(faker.book().title());
					l.setResumen(faker.lorem().characters(100));
					l.setNacionalidad(faker.country().name());
					l.setFecha(LocalDate.now());
					l.setAutor(faker.book().author());
					l.setEditorial(servicioEditoriales.findById(1));
					l.setImagen("hp4.jpg");
					//l.setTrailer("https://www.youtube.com/embed/a_426RiwST8?si=BDxxgic4pJzkwbv5");

					servicioLibros.save(l);

					/*
					for(int ii=0; ii<3; ii++){
						Comentario c=new Comentario();
						c.setTitulo(faker.backToTheFuture().character());
						c.setContenido(faker.backToTheFuture().quote());
						c.setFecha(LocalDate.now());
						c.setPelicula(p);

						repositorioComentarios.save(c);
					}
					*/

				}
			}

		};
	}

	//======IMPORTAR CON JSON CON JACKSON============
	/*
	@Bean
	CommandLineRunner importarLibrosJson(){
		return args -> {
			// 1. Leer el archivo JSON
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			List<Libro> listaLibros = mapper.readValue(new File("jsonLibro.json"), new TypeReference<List<Libro>>(){});

			// 2. Guardar los objetos en la base de datos
			for (Libro libro : listaLibros) {
				servicioLibros.save(libro);
			}
		};
	}
	 */


	//======IMPORTAR XML============
/*
	@Bean
	CommandLineRunner importarLibrosXml(){
		return args -> {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			Document documento = null;

			try {
				DocumentBuilder builder = factory.newDocumentBuilder();
				documento = builder.parse(new File("xmlLibro.xml"));

				NodeList libros = documento.getElementsByTagName("libro");
				for (int i = 0; i < libros.getLength(); i++) {
					Node libroNode = libros.item(i);
					if (libroNode.getNodeType() == Node.ELEMENT_NODE) {
						Element libroElement = (Element) libroNode;

						String titulo = libroElement.getElementsByTagName("titulo").item(0).getTextContent();
						String resumen = libroElement.getElementsByTagName("resumen").item(0).getTextContent();
						LocalDate fecha = LocalDate.parse(libroElement.getElementsByTagName("fecha").item(0).getTextContent(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
						String nacionalidad = libroElement.getElementsByTagName("nacionalidad").item(0).getTextContent();
						String imagen = libroElement.getElementsByTagName("imagen").item(0).getTextContent();
						String autor = libroElement.getElementsByTagName("autor").item(0).getTextContent();

						Libro libro = new Libro();
						libro.setTitulo(titulo);
						libro.setResumen(resumen);
						libro.setFecha(fecha);
						libro.setNacionalidad(nacionalidad);
						libro.setImagen(imagen);
						libro.setAutor(autor);

						// Guardar el objeto en la base de datos
						servicioLibros.save(libro);
					}
				}

			} catch (ParserConfigurationException | IOException | SAXException e) {
				e.printStackTrace();
			}
		};
	}
*/
	//======IMPORTAR CSV============
/*
	@Bean
	CommandLineRunner importarLibrosCsv(){
		return args -> {
			try {
				BufferedReader br = new BufferedReader(new FileReader("csvLibro.csv"));
				String linea = br.readLine(); // Saltar la primera línea (cabecera del CSV)
				linea = br.readLine(); // Leer la segunda línea

				while(linea != null) {
					String[] datos = linea.split(",");
					String titulo = datos[0];
					String resumen = datos[1];
					LocalDate fecha = LocalDate.parse(datos[2], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					String nacionalidad = datos[3];
					String imagen = datos[4];
					String autor = datos[5];

					Libro libro = new Libro();
					libro.setTitulo(titulo);
					libro.setResumen(resumen);
					libro.setFecha(fecha);
					libro.setNacionalidad(nacionalidad);
					libro.setImagen(imagen);
					libro.setAutor(autor);

					servicioLibros.save(libro);

					linea = br.readLine();
				}

			} catch(IOException ioe){
				ioe.printStackTrace();
			}
		};
	}

 */

}
