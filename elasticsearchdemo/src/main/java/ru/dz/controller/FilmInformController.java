package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.dz.entity.Comment;
import ru.dz.entity.Movie;
import ru.dz.entity.MyUser;
import ru.dz.entity.Rating;
import ru.dz.repository.CommentRepository;
import ru.dz.repository.MovieRepository;
import ru.dz.repository.RatingRepository;
import ru.dz.repository.UserRepository;

import java.util.Date;

/**
 * Created by Vlad.M on 08.12.2016.
 */
@Controller
@RequestMapping(value = "/movie")
public class FilmInformController {
    @Autowired
    MovieRepository movieRepository;
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String loadFilm(@PathVariable("id") Long id, ModelMap modelMap){
        Movie movie = movieRepository.findOne(id);
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken){
        modelMap.addAttribute("access",0);
        modelMap.addAttribute("canvote",0);
        }
        else{
            MyUser user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            if(ratingRepository.findByMovie(movie).contains(user)) modelMap.addAttribute("canvote","0");
            else modelMap.addAttribute("canvote",1);
            modelMap.addAttribute("access",1);

        }
        modelMap.addAttribute("comments",movieRepository.findOne(id).getComments());
        modelMap.addAttribute("movie",movieRepository.findOne(id));
        return "movie";
    }
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;
    @RequestMapping(value = "/{id}/comment", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
//    @Secured("ROLE_USER")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public Comment addComment(@PathVariable("id") Long id,@RequestParam("content") String text){
        System.out.println(text);
        Comment comment = new Comment();
        comment.setDate(new Date());
        comment.setContent(text);
        Movie movie = movieRepository.findOne(id);
        comment.setMovie(movie);
        comment.setUser(userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        commentRepository.save(comment);

        movie.getComments().add(comment);
        movieRepository.saveAndFlush(movie);
        return comment;
    }
    @Autowired
    RatingRepository ratingRepository;
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}/rate")
    public Float rate(@PathVariable("id") Long id, @RequestParam("value") Integer value){
        Rating rating = new Rating();
        Movie movie = movieRepository.findOne(id);
        rating.setUser(userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        rating.setMovie(movie);
        rating.setRating(value);
        rating = ratingRepository.saveAndFlush(rating);
        movie.getRatings().add(rating);
        movie.setRating_num(movie.getRating_num()+value);
        movie.setVoted_number(movie.getVoted_number()+1);
        return (float) (movie.getRating_num() / movie.getVoted_number());
    }
}

