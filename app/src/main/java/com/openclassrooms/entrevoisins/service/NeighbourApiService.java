package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * Get a list of favorite neighbours
     * @return {@link List}
     */
    // Ajouté pour afficher la liste de favoris
    List<Neighbour> getFavorites();

    /**
     * Returns a neighbour from its id
     * @param id
     * @return {@link Neighbour}
     */
    // Ajouté pour afficher les détails d’un voisin - TODO Chercher une meilleure manière de faire
    Neighbour getFromId(long id);
}
