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
     * Récupère la personne correspondant au paramètre id de la requette. Place
     * la valeur -1 dans l'attribut currentPrsonID si en mode nouvelle personne.
     * Charge les films lié à cette personne.
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

}
