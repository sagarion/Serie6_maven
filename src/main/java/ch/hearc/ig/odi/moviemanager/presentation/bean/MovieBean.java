/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.odi.moviemanager.presentation.bean;

import ch.hearc.ig.odi.moviemanager.business.Movie;
import ch.hearc.ig.odi.moviemanager.business.Person;
import ch.hearc.ig.odi.moviemanager.exception.NullParameterException;
import ch.hearc.ig.odi.moviemanager.services.Services;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author thibault.daucourt
 */
@Named("movieBean")
@ViewScoped
public class MovieBean implements Serializable {
    
    @Inject
    Services services;
    
    List<Person> people;
    private Movie currentMovie;
    private long currentMovieID;

    /**
     * Creates a new instance of MovieBean
     */
    public MovieBean() {
    }

    public List<Person> getPeople() {
        return people;
    }

    public Movie getCurrentMovie() {
        return currentMovie;
    }

    public void setCurrentMovie(Movie currentMovie) {
        this.currentMovie = currentMovie;
    }

    public long getCurrentMovieID() {
        return currentMovieID;
    }

    public void setCurrentMovieID(long currentMovieID) {
        this.currentMovieID = currentMovieID;
    }
    
    /**
     * Récupère le film correspondant au paramètre id de la requette. Place
     * la valeur -1 dans l'attribut currentMovieID si en mode nouveau film.
     * Charge les personnes lié à ce film.
     *
     */
    public void initMovie() {
        String idParam = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap().get("id");
        if (!(idParam == null || idParam.isEmpty())) {
            currentMovieID = Integer.parseInt(idParam);
            currentMovie = services.getMovieWithId(currentMovieID);
            people = currentMovie.getPeople();
        } else {
            currentMovie = new Movie();
            currentMovieID = -1;
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
 
            services.saveMovie(currentMovie);
        } catch (NullParameterException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));
        }
        return "detailsMovie.xhtml?id=" + currentMovie.getId() + "&faces-redirect=true";
    }

}
