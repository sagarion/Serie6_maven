/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.odi.moviemanager.presentation.bean;

import ch.hearc.ig.odi.moviemanager.business.Movie;
import ch.hearc.ig.odi.moviemanager.business.Person;
import ch.hearc.ig.odi.moviemanager.services.Services;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;

import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author thibault.daucourt
 */
@Named("personBean")
@RequestScoped
public class PersonBean implements Serializable{
    
    @Inject
    Services services;
    
    List<Person> people;
    Person currentPerson;
    private long currentCustomerID;
    
    List<Movie> movies;
    
    
    
    /**
     * Creates a new instance of PersonBean
     */
    public PersonBean() {
    }
    
    public List<Person> getPeople() {
        return people;
    }

    public List<Movie> getMovies() {
        return movies;
    }
    
    /**
     * Initialise la liste de personne ayant vu un film
     */
    public void initList() {
        this.people = services.getPeopleList();
        this.movies = services.getMoviesList();
    }
    
    
    
    
}
