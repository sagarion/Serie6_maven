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
import javax.faces.context.FacesContext;
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
    private Person currentPerson;
    private long currentPersonID;
    
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

    public long getCurrentPersonID() {
        return currentPersonID;
    }

    public void setCurrentPersonID(long currentPersonID) {
        this.currentPersonID = currentPersonID;
    }

    public Person getCurrentPerson() {
        return currentPerson;
    }

    public void setCurrentPerson(Person currentPerson) {
        this.currentPerson = currentPerson;
    }
    
    
    
    
    /**
     * Initialise la liste de personne enregistré
     * Initialise la liste des films enregistré
     */
    public void initList() {
        this.people = services.getPeopleList();
        this.movies = services.getMoviesList();
    }
    
    /**
     * Récupère la personne correspondant au paramètre id de la requette.
     * Place la valeur -1 dans l'attribut currentPrsonID si en mode nouvelle
     * personne.
     * Charge les films lié à cette personne.
     *
     */
    public void initPerson() {
        String idParam = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap().get("id");
        if (!(idParam == null || idParam.isEmpty())) {
            currentPersonID = Integer.parseInt(idParam);
            currentPerson = services.getPersonWithId(currentPersonID);
            movies = currentPerson.getMovies();
        }else {
            currentPerson = new Person();
            currentPersonID = -1;
        }
    }
    
    
}
