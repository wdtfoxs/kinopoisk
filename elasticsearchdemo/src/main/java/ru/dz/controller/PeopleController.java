package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.dz.aspects.annotation.IncludeUser;
import ru.dz.entity.People;
import ru.dz.repository.PeopleRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/actor")
public class PeopleController {

    private final PeopleRepository peopleRepository;
    private final HttpServletRequest request;

    @Autowired
    public PeopleController(PeopleRepository peopleRepository, HttpServletRequest request) {
        this.peopleRepository = peopleRepository;
        this.request = request;
    }

    @IncludeUser
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getPage(@PathVariable("id") Long id){
        People people = peopleRepository.findOne(id);
        request.setAttribute("people", people);
        return "actor";
    }
}
