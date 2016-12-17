package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.dz.aspects.annotation.IncludeUser;
import ru.dz.entity.Award;
import ru.dz.repository.AwardRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/award")
public class AwardController {

    private final HttpServletRequest request;
    private final AwardRepository awardRepository;

    @Autowired
    public AwardController(HttpServletRequest request, AwardRepository awardRepository) {
        this.request = request;
        this.awardRepository = awardRepository;
    }

    @IncludeUser
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getPage(@PathVariable("id") Long id){
        Award award = awardRepository.findOne(id);
        request.setAttribute("award", award);
        return "award";
    }

}
