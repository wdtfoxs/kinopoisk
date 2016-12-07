package ru.springproject.dz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;
import ru.springproject.core.dz.services.ReviewService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/review")
public class ReviewController {

    private final HttpServletRequest request;
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(HttpServletRequest request, ReviewService reviewService) {
        this.request = request;
        this.reviewService = reviewService;
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RedirectView addReview() {
        reviewService.addReview(request.getParameter("review"),
                Long.valueOf(request.getParameter("userid")),
                Long.valueOf(request.getParameter("movieid")));

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/movie/" + request.getParameter("movieid"));
        return redirectView;
    }

}
