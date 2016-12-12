package ru.springproject.dz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.springproject.dz.valid.RatingBean;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RatingController {

    private static final String RATING_FORM = "ratingForm";
    private final HttpServletRequest request;

    @Autowired
    public RatingController(HttpServletRequest request) {
        this.request = request;
    }

    @RequestMapping(value = "/rating", method = RequestMethod.POST)
    public String ratingFilm(@Valid @ModelAttribute(RATING_FORM)RatingBean ratingBean){
        System.out.println(ratingBean.getRating());
//        RedirectView redirectView = new RedirectView();
//        redirectView.setUrl("/movie/" + request.getParameter("movieid"));
        return "redirect:/movie/" + request.getParameter("movieid");
    }
}
