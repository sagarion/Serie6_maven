/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.odi.moviemanager.presentation.bean;

import ch.hearc.ig.odi.moviemanager.business.Movie;
import ch.hearc.ig.odi.moviemanager.business.Person;
import ch.hearc.ig.odi.moviemanager.exception.InvalidParameterException;
import ch.hearc.ig.odi.moviemanager.exception.NullParameterException;
import ch.hearc.ig.odi.moviemanager.exception.UniqueException;
import ch.hearc.ig.odi.moviemanager.services.Services;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author thibault.daucourt
 */
@Named("personBean")
@ViewScoped
public class PersonBean implements Serializable {

    @Inject
    Services services;

    List<Person> people;
    private Person currentPerson;
    private long currentPersonID;

    List<Movie> movies;
    Movie movieToAdd;

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

    public Movie getMovieToAdd() {
        return movieToAdd;
    }

    public void setMovieToAdd(Movie movieToAdd) {
        this.movieToAdd = movieToAdd;
    }
    
    

    /**
     * Initialise la liste de personne enregistré Initialise la liste des films
     * enregistré
     */
    public void initList() {
        this.people = services.getPeopleList();
        this.movies = services.getMoviesList();
    }

    /**
     * Récupère la personne correspondant au paramètre id de la requette. Place
     * la valeur -1 dans l'attribut currentPrsonID si en mode nouvelle personne.
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
        } else {
            currentPerson = new Person();
            currentPersonID = -1;
        }
    }

    /**
     * Récupère les information saisie par l'utilisateur et agis en fonction. Si
     * l'identifiant existe fait une mise à jour. Si l'identifiant n'existe pas,
     * ajoute la nouvelle personne.
     *
     * @return retourne la chaine de caractère définissant la navigation
     */
    public String sauver() {
        try {
 
            services.savePerson(currentPerson);
        } catch (NullParameterException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));
        }
        return "detailsPerson.xhtml?id=" + currentPerson.getId() + "&faces-redirect=true";
    }
    
    /**
     * Supprime le film placé en paramètre de la personne Courante. 
     *
     * @param movie le film a supprimé de la liste de film de la personne courante.
     * @return retourne la chaine de caractère définissant la navigation.
     * 
     * @throws ch.hearc.ig.odi.moviemanager.exception.InvalidParameterException
     * pas gêrer car ne devrait jamais exister.
     */    
    public String deleteMovie(Movie movie) throws  InvalidParameterException{
        try {
 
            services.removeMovieFromPerson(currentPerson, movie);
        } catch (NullParameterException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));
        }
        return "detailsPerson.xhtml?id=" + currentPerson.getId() + "&faces-redirect=true";
    }
    
    /**
     * Retourne la liste des films que la personne n'a pas encore vu.
     * @return la liste contenant les films pas encore vu par cette personne.
     */
    public List<Movie> getMoviesViewable() {
        List<Movie> moviesNotAdded = services.getMoviesList();
        moviesNotAdded.removeAll(currentPerson.getMovies());
        return moviesNotAdded;
    }
    
    /**
     * ajoute un film à la liste des films vus de la personne courante
     *
     * @return retourne la chaine de caractère définissant la navigation.
     * 
     */    
    public String addMovie(){
        try {

            services.addMovieToPerson(currentPerson, movieToAdd);
        } catch (NullParameterException | UniqueException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));
        }
        return "detailsPerson.xhtml?id=" + currentPerson.getId() + "&faces-redirect=true";
    }

}
