/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.odi.moviemanager.business;

import ch.hearc.ig.odi.moviemanager.exception.InvalidParameterException;
import ch.hearc.ig.odi.moviemanager.exception.NullParameterException;
import ch.hearc.ig.odi.moviemanager.exception.UniqueException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author thibault.daucourt
 */
public class Person {
    private Long id;
    private String firstName;
    private String lastName;
    
    private HashMap<Long,Movie> movies;

    /**
     * Constructeur paramétré pour la classe Person. Obligatoire. Représente
     * un personne qui regarde des films, on initialise la collection qui
     * contiendra les films vu par cette personne
     *
     * @param id Le numéro unique d'identification de la personne
     * @param firstName Le prénom de la personne
     * @param lastName Le nom de famille de la personne
     */
    public Person(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        movies = new HashMap();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<Map.Entry<Long, Movie>> getMovies() {
        return new ArrayList<>(movies.entrySet());
    }
    
    /**
     * méthode paramétré pour la classe Person. 
     * Ajoute un film à la "liste de films vus" pour cette personne
     *
     * @param movie Le film à ajouter à la "liste de films vus" par 
     * cette personne
     * @throws ch.hearc.ig.odi.moviemanager.exception.NullParameterException
     * si la valeur du paramètre movie est null
     * @throws ch.hearc.ig.odi.moviemanager.exception.UniqueException
     * si le film paramètré est déjà dans la "liste de films vus"
     */
    public void addMovie(Movie movie) throws NullParameterException, UniqueException{
        
        // vérification des données avant ajout
        if (movie == null) {
            throw new NullParameterException();
        }
        
        if (movies.containsValue(movie)) {
            throw new UniqueException();
        }
        
        // ajout du film
        movies.put(movie.getId(), movie);
        movie.addPerson(this);
        
    }
    
    /**
     * méthode paramétré pour la classe Person. 
     * Supprime le film placé en paramètre de "liste de films vus" 
     * pour cette personne
     *
     * @param movie Le film à supprimer à la "liste de films vus" 
     * par cette personne
     * @throws ch.hearc.ig.odi.moviemanager.exception.NullParameterException
     * si la valeur du paramètre movie est null
     * @throws ch.hearc.ig.odi.moviemanager.exception.InvalidParameterException
     * si le film paramètré n'est pas dans la "liste de films vus"
     */
    public void removeMovie(Movie movie) throws NullParameterException, InvalidParameterException{
        // vérification des données avant suppresion
        if (movie == null) {
            throw new NullParameterException();
        }
        
        if (!movies.containsValue(movie)) {
            throw new InvalidParameterException();
        }
        
        // supression du film 
        movies.remove(movie.getId());
        movie.removePerson(this);
    }
    
    
    
}
