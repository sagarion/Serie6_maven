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
public class Movie {
    private Long id;
    private String name;
    private String producer;
    
    private HashMap<Long,Person> people;

    
    /**
     * Constructeur paramétré pour la classe Movie. Obligatoire. Représente
     * un filme qui est regardé par des personnes, on initialise la collection 
     * qui contiendra les perosnnes qui ont vu par ce film
     *
     * @param id Le numéro unique d'identification de la personne
     * @param name Le nom du film
     * @param producer Le nom du réalisateur du film
     */
    public Movie(Long id, String name, String producer) {
        this.id = id;
        this.name = name;
        this.producer = producer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
    
    public ArrayList<Map.Entry<Long, Person>> getPeople() {
        return new ArrayList<>(people.entrySet());
    }
    
    /**
     * méthode paramétré pour la classe Movie. 
     * Ajoute une personne à la "liste des personnes" ayant vu ce film
     *
     * @param person La personne à ajouter à la "liste des personnes" ayant
     * vu ce film
     * @throws ch.hearc.ig.odi.moviemanager.exception.NullParameterException
     * si la valeur du paramètre person est null
     * @throws ch.hearc.ig.odi.moviemanager.exception.UniqueException
     * si la perosnne à ajouter existe déjà dans la liste des personnae ayant
     * déjà vu ce film
     */
    public void addPerson(Person person) throws NullParameterException, UniqueException{
        
        // vérification des données avant ajout
        if (person == null) {
            throw new NullParameterException();
        }
        
        if (people.containsValue(person)) {
            throw new UniqueException();
        }
        
        // ajout de la personne
        people.put(person.getId(), person);
        
    }
    
    /**
     * méthode paramétré pour la classe Movie. 
     * Supprime la personne placée en paramètre de "liste des personnes" ayant
     * vu ce film
     *
     * @param person La personne à supprimée de la "Liste des personnes" ayant
     * vu ce film
     * @throws ch.hearc.ig.odi.moviemanager.exception.NullParameterException
     * si la valeur du paramètre person est null
     * @throws ch.hearc.ig.odi.moviemanager.exception.InvalidParameterException
     * si la perosnne à supprimer n'existe pas dans la liste des personnae ayant
     * déjà vu ce film
     */
    public void removePerson(Person person) throws NullParameterException, InvalidParameterException{
        // vérification des données avant supppression
        if (person == null) {
            throw new NullParameterException();
        }
        
        if (!people.containsValue(person)) {
            throw new InvalidParameterException();
        }
        
        // supression de la personne 
        people.remove(person.getId());
    }
}
