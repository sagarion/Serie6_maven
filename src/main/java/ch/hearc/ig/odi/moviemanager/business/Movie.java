/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.odi.moviemanager.business;

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
}
