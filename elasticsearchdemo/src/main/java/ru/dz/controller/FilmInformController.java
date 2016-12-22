package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.dz.aspects.annotation.IncludeUser;
import ru.dz.entity.Comment;
import ru.dz.entity.Movie;
import ru.dz.entity.Rating;
import ru.dz.entity.User;
import ru.dz.repository.CommentRepository;
import ru.dz.repository.MovieRepository;
import ru.dz.repository.RatingRepository;
import ru.dz.repository.UserRepository;

import java.util.List;

/**
 * Created by Vlad.M on 08.12.2016.
 */
@Controller
@RequestMapping(value = "/movie")
public class FilmInformController {

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final RatingRepository ratingRepository;

    @Autowired
    public FilmInformController(MovieRepository movieRepository, UserRepository userRepository, CommentRepository commentRepository, RatingRepository ratingRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.ratingRepository = ratingRepository;
    }

    @IncludeUser
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String loadFilm(@PathVariable("id") Long id, ModelMap modelMap) {
        Movie movie = movieRepository.findOne(id);
        if (movie != null) {
            modelMap.addAttribute("comments", movieRepository.findOne(id).getComments());
            if (SecurityContextHolder.getContext().getAuthentication() != null &&
                    SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
                modelMap.addAttribute("access", 0);
                modelMap.addAttribute("canvote", 0);
            } else {
                modelMap.addAttribute("access", 1);
                String name = SecurityContextHolder.getContext().getAuthentication().getName();
                User current_user = userRepository.findByUsername(name);
                List<Rating> rating = ratingRepository.findByMovie(movie);
                if (rating != null) {
                    for (Rating r : rating) {
                        if (r.getUser() == current_user) {
                            modelMap.addAttribute("movie", movieRepository.findOne(id));
                            modelMap.addAttribute("canvote", 0);
                            return "movie";
                        }
                    }
                }

                modelMap.addAttribute("canvote", 1);
            }
        }
        modelMap.addAttribute("movie", movieRepository.findOne(id));

        return "movie";
    }

    @RequestMapping(value = "/{id}/comment", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
//    @Secured("ROLE_USER")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public Comment addComment(@PathVariable("id") Long id, @RequestParam("content") String text) {
        System.out.println(text);
        Comment comment = new Comment();
        comment.setContent(text);
        Movie movie = movieRepository.findOne(id);
        comment.setMovie(movie);
        comment.setUser(userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        Comment added_comment = commentRepository.save(comment);
        movie.getComments().add(added_comment);
        movieRepository.saveAndFlush(movie);
        return added_comment;
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}/rate")
    public @ResponseBody float rate(@PathVariable("id") Long id, @RequestParam("value") Integer value,ModelMap modelMap) {
        Movie movie = movieRepository.findOne(id);
        User current_user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Rating rating = null;
        modelMap.addAttribute("canvote",0);
        modelMap.addAttribute("access",1);
        //TODO: should be here any check, if user had already rated??
        if (ratingRepository.findByMovie(movie).isEmpty()){
            movie.setVoted_number(1);
            movie.setRating_num(value);
            rating = new Rating();
            rating.setUser(current_user);
            rating.setRating(value);
            rating.setMovie(movie);
            movie.getRatings().add(ratingRepository.saveAndFlush(rating));
            modelMap.addAttribute("movie",movieRepository.saveAndFlush(movie));
            return (float) (value);
        }
        else {
            List<Rating> ratings = ratingRepository.findByMovie(movie);
            rating = new Rating();
            rating.setUser(current_user);
            rating.setMovie(movie);
            ratingRepository.saveAndFlush(rating);
            movie.getRatings().add(rating);
            modelMap.addAttribute("movie",movieRepository.saveAndFlush(movie));
            movie.setRating_num(movie.getRating_num() + value);
            movie.setVoted_number(movie.getVoted_number() + 1);
            return (float) (movie.getRating_num() / movie.getVoted_number());
        }
    }
}

