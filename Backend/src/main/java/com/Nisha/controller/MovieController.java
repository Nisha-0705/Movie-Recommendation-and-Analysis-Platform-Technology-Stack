package com.Nisha.controller;

import com.Nisha.model.Movie;
import com.Nisha.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "*")
 
 
public class MovieController {
    @Autowired
     
    private MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

   @PostMapping
public Movie addMovie(@RequestBody Movie movie) {
    return movieService.addMovie(movie);
}
     

@GetMapping("/{id}")
public Movie getMovieById(@PathVariable String id) {
    return movieService.getMovieById(id);
}

@PutMapping("/{id}")
public Movie updateMovie(@PathVariable String id,
                         @RequestBody Movie movie) {
    return movieService.updateMovie(id, movie);
}

@DeleteMapping("/{id}")
public String deleteMovie(@PathVariable String id) {
    movieService.deleteMovie(id);
    return "Movie deleted successfully!";
}
 
}