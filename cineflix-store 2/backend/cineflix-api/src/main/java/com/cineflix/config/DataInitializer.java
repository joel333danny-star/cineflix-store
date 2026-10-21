package com.cineflix.config;

import com.cineflix.model.*;
import com.cineflix.repository.GeneroRepository;
import com.cineflix.repository.PeliculaRepository;
import com.cineflix.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UsuarioRepository usuarioRepository;
    private final GeneroRepository generoRepository;
    private final PeliculaRepository peliculaRepository;

    @Value("${cineflix.seed.enabled:true}")
    private boolean seedEnabled;

    @Override
    public void run(String... args) {
        if (!seedEnabled) return;

        if (usuarioRepository.findByEmail("cliente@cineflix.com").isEmpty()) {
            usuarioRepository.save(Usuario.builder()
                    .nombre("Cliente Demo")
                    .email("cliente@cineflix.com")
                    .password("123456")
                    .rol(Rol.CLIENTE)
                    .build());
        }
        if (usuarioRepository.findByEmail("admin@cineflix.com").isEmpty()) {
            usuarioRepository.save(Usuario.builder()
                    .nombre("Administrador")
                    .email("admin@cineflix.com")
                    .password("admin123")
                    .rol(Rol.ADMIN)
                    .build());
        }

        Map<String, Genero> generos = Map.of(
                "Acción", genero("Acción"),
                "Comedia", genero("Comedia"),
                "Terror", genero("Terror"),
                "Ciencia ficción", genero("Ciencia ficción"),
                "Drama", genero("Drama"),
                "Romance", genero("Romance"),
                "Animación", genero("Animación"),
                "Suspenso", genero("Suspenso"),
                "Documental", genero("Documental")
        );

        if (peliculaRepository.count() == 0) {
            crear("Bohemian Rhapsody", "Historia musical inspirada en el ascenso de una legendaria banda de rock.", "Drama", TipoProducto.PELICULA, 2018, "Bryan Singer", "Rami Malek", "bohemian.svg", "https://www.youtube.com/embed/mP0VHJYFOAU", 149, 49, 8.1, 7000, false, generos);
            crear("Avengers Endgame", "Los héroes restantes preparan una última misión para restaurar el universo.", "Acción", TipoProducto.PREMIUM, 2019, "Anthony Russo", "Robert Downey Jr.", "avengers.svg", "https://www.youtube.com/embed/TcMBFSGVi1c", 199, 59, 7.6, 18900, true, generos);
            crear("Animales Fantásticos", "Un magizoólogo se enfrenta a fuerzas oscuras en una aventura llena de criaturas mágicas.", "Ciencia ficción", TipoProducto.PELICULA, 2018, "David Yates", "Eddie Redmayne", "fantastic.svg", "https://www.youtube.com/embed/VYZ3U1inHA4", 129, 45, 6.9, 4500, false, generos);
            crear("Viudas", "Cuatro mujeres toman el control de un plan criminal tras perder a sus esposos.", "Suspenso", TipoProducto.PELICULA, 2018, "Steve McQueen", "Viola Davis", "viudas.svg", "https://www.youtube.com/embed/nN2yBBSRC78", 119, 39, 6.5, 839, false, generos);
            crear("Infiltrado en el Klan", "Un policía afroamericano se infiltra en una organización extremista.", "Drama", TipoProducto.PELICULA, 2018, "Spike Lee", "John David Washington", "klan.svg", "https://www.youtube.com/embed/pFc6I0rgmgY", 129, 49, 7.6, 2800, false, generos);
            crear("Hunter Killer", "Un submarino estadounidense intenta evitar un conflicto internacional.", "Acción", TipoProducto.PELICULA, 2018, "Donovan Marsh", "Gerard Butler", "hunter.svg", "https://www.youtube.com/embed/6zN2g0udQkA", 99, 35, 6.2, 596, false, generos);
            crear("Campeones", "Un entrenador debe dirigir a un equipo muy especial y aprende una lección de vida.", "Comedia", TipoProducto.PELICULA, 2018, "Javier Fesser", "Javier Gutiérrez", "campeones.svg", "https://www.youtube.com/embed/C0p5-b3YwIM", 99, 35, 7.4, 261, false, generos);
            crear("Jurassic World", "Un parque de dinosaurios se sale de control cuando una nueva criatura escapa.", "Ciencia ficción", TipoProducto.PELICULA, 2015, "Colin Trevorrow", "Chris Pratt", "jurassic.svg", "https://www.youtube.com/embed/RFinNxS5KN4", 139, 45, 6.5, 5700, false, generos);
            crear("Spider-Man Homecoming", "Un joven héroe intenta demostrar su valor mientras protege su ciudad.", "Acción", TipoProducto.PREMIUM, 2017, "Jon Watts", "Tom Holland", "spiderman.svg", "https://www.youtube.com/embed/rk-dF1lIbIg", 169, 55, 7.4, 11000, true, generos);
            crear("Mamma Mia", "Una historia musical de familia, romance y canciones inolvidables.", "Romance", TipoProducto.PELICULA, 2008, "Phyllida Lloyd", "Meryl Streep", "mamma.svg", "https://www.youtube.com/embed/8RBNHdG35WY", 89, 29, 7.3, 1600, false, generos);
            crear("Wonder", "Un niño con diferencias faciales enfrenta el reto de integrarse a la escuela.", "Drama", TipoProducto.PELICULA, 2017, "Stephen Chbosky", "Jacob Tremblay", "wonder.svg", "https://www.youtube.com/embed/ngiK1gQKgK8", 119, 39, 8.2, 3800, false, generos);
            crear("Coco", "Un niño viaja al mundo de los muertos para descubrir la historia de su familia.", "Animación", TipoProducto.PELICULA, 2017, "Lee Unkrich", "Anthony Gonzalez", "coco.svg", "https://www.youtube.com/embed/Rvr68u6k5sI", 159, 49, 8.1, 8400, false, generos);
            crear("Venom", "Un periodista se fusiona con una entidad alienígena y obtiene poderes incontrolables.", "Acción", TipoProducto.PREMIUM, 2018, "Ruben Fleischer", "Tom Hardy", "venom.svg", "https://www.youtube.com/embed/u9Mv98Gr5pY", 169, 55, 6.6, 5700, true, generos);
            crear("El Grinch", "Una criatura verde planea robar la Navidad, pero descubre algo inesperado.", "Animación", TipoProducto.PELICULA, 2018, "Yarrow Cheney", "Benedict Cumberbatch", "grinch.svg", "https://www.youtube.com/embed/vjnqABgxfO0", 99, 29, 6.4, 3100, false, generos);
            crear("Toy Story", "Un grupo de juguetes vive grandes aventuras cuando los humanos no miran.", "Animación", TipoProducto.PELICULA, 1995, "John Lasseter", "Tom Hanks", "toystory.svg", "https://www.youtube.com/embed/KYz2wyBy3kc", 119, 39, 8.3, 9600, false, generos);
            crear("Cómo Entrenar a tu Dragón", "Un joven vikingo forma una amistad inesperada con un dragón legendario.", "Animación", TipoProducto.PELICULA, 2010, "Dean DeBlois", "Jay Baruchel", "dragon.svg", "https://www.youtube.com/embed/oKiYuIsPxYk", 129, 39, 8.1, 7400, false, generos);
            crear("Planeta Azul", "Documental sobre océanos, vida marina y conservación del planeta.", "Documental", TipoProducto.DOCUMENTAL, 2020, "Equipo CineFlix", "Narrador", "documental.svg", "https://www.youtube.com/embed/kAphgHhlteM", 79, 25, 7.9, 1250, false, generos);
            crear("Corto Neon", "Cortometraje experimental sobre una ciudad futurista iluminada por neón.", "Ciencia ficción", TipoProducto.CORTOMETRAJE, 2024, "CineFlix Lab", "Actriz Principal", "corto.svg", "https://www.youtube.com/embed/dQw4w9WgXcQ", 39, 15, 7.1, 550, false, generos);
        }
    }

    private Genero genero(String nombre) {
        return generoRepository.findByNombreIgnoreCase(nombre)
                .orElseGet(() -> generoRepository.save(Genero.builder().nombre(nombre).build()));
    }

    private void crear(String titulo, String descripcion, String genero, TipoProducto tipo, int anio,
                       String director, String actor, String poster, String trailer,
                       double compra, double renta, double rating, int vistas, boolean premium,
                       Map<String, Genero> generos) {
        peliculaRepository.save(Pelicula.builder()
                .titulo(titulo)
                .descripcion(descripcion)
                .genero(generos.get(genero))
                .tipoProducto(tipo)
                .anio(anio)
                .director(director)
                .actorPrincipal(actor)
                .posterUrl("/posters/" + poster)
                .trailerUrl(trailer)
                .precioCompra(BigDecimal.valueOf(compra))
                .precioRenta(BigDecimal.valueOf(renta))
                .rating(rating)
                .vistas(vistas)
                .premium(premium)
                .build());
    }
}
