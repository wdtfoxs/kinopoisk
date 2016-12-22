package ru.dz.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.dz.entity.Award;
import ru.dz.entity.Country;
import ru.dz.entity.Movie;
import ru.dz.entity.People;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AutoCompite {
    private static final ObjectMapper mapper = new ObjectMapper();
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public List<Movie> loadMoviesFromJson() {
        List<Movie> movies = null;
        try {
            movies = mapper.readValue(getResource("static/resources/movies.json"), mapper.getTypeFactory().constructCollectionType(List.class, Movie.class));
//            Award award = awardRepo.findOne(1L);
//            List<Award> awards = new ArrayList<>();
//            awards.add(award);
//            for (Movie m: movies) {
//                m.setAwards(awards);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public List<Country> loadCountryFromJson() {
        List<Country> countries = null;
        try {
            countries = mapper.readValue(getResource("static/resources/country.json"), mapper.getTypeFactory().constructCollectionType(List.class, Country.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countries;
    }

    public List<People> loadPeopleFromJson() {
        List<People> people = null;
        try {
            people = mapper.readValue(getResource("static/resources/actors.json"), mapper.getTypeFactory().constructCollectionType(List.class, People.class));
//            List<Award> awards = new ArrayList<>();
//            for (People p: people) {
//                Award award = new Award();
//                award.setName("Best actor");
//                award.setDescription("Award for the best role");
//                award.setYear(2014);
//                award.setPeople(p);
//                award.setMovie(m);
//                awards.add(award);
//                p.setAwards(awards);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return people;
    }

//    public List<Award> loadAwardsFromJson() {
//        List<Award> awards = null;
//        try {
//            awards = mapper.readValue(getResource("static/resources/awards.json"), mapper.getTypeFactory().constructCollectionType(List.class, Award.class));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return awards;
//    }

    public List<Award> createRelationships(List<Movie> movies, List<People> peoples){
        List<Award> awards = new ArrayList<>();
        Random r = new Random();
        int j = 0;
        for (Movie m: movies) {
            for (int i = 0; i<r.nextInt(peoples.size()); i++) {
                Award award = new Award();
                award.setName("Best film " + j);
                award.setDescription("Award for the best film");
                award.setYear(2014);
                award.setMovie(m);
                award.setPeople(peoples.get(r.nextInt(peoples.size())));
                awards.add(award);
                j++;
            }
        }

        return awards;
    }

    private String getResource(String file) {
        URL url = Resources.getResource(file);
        try {
            return Resources.toString(url, Charsets.UTF_8);
        } catch (IOException e) {
            return "";
        }
    }
}
