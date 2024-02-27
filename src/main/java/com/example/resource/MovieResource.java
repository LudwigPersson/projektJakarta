package com.example.resource;

import com.example.dto.MovieDto;
import com.example.dto.Movies;
import com.example.repository.MovieRepository;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;

@Path("/movies")
public class MovieResource {

    private MovieRepository movieRepository;

    public MovieResource() {
    }

    @Inject
    public MovieResource(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Movies all() {
        return new Movies(
            movieRepository.getAll().stream().map(MovieDto::map).toList(),
            LocalDateTime.now());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{uuid}")
    public MovieDto one(@PathParam("uuid") UUID uuid) {
        var movie = movieRepository.findByUuid(uuid);
        if (movie == null)
            throw new NotFoundException("Invalid id " + uuid);
        return MovieDto.map(movie);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid MovieDto movieDto){
        var movie = movieRepository.add(MovieDto.map(movieDto));
        return Response.created(URI.create("movies/" + movie.getUuid())).build();
    }


}