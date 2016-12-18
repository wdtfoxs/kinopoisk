package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dz.repository.CountryRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/add")
public class AddFilmController {

    private final CountryRepository countryRepository;
    private final HttpServletRequest request;

    @Autowired
    public AddFilmController(CountryRepository countryRepository, HttpServletRequest request) {
        this.countryRepository = countryRepository;
        this.request = request;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getPage(){
        request.setAttribute("country", countryRepository.findAll());
        return "addfilm";
    }

    @RequestMapping(value = "/film", method = RequestMethod.POST)
    public void addFilm(@RequestParam String name, @RequestParam String description,
                          @RequestParam int year, @RequestParam int age,
                          @RequestParam String image, @RequestParam String trailer,
                          @RequestParam String country){

        System.out.println(name +" : " + description + " : " +
        year + " : " + age + " : " + image + " : " + trailer + " : " +
        country);
    }
}
