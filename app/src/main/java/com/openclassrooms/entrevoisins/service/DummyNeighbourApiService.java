package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getFavorites() {
        // Parcourt la liste de voisins et renvoie une liste de ceux avec le statut favori
        List<Neighbour> favorites = new ArrayList<Neighbour>();
        for (Neighbour n: neighbours){
            if (n.isFavorite()) {
                favorites.add(n);
            }
        }
        return favorites;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Neighbour getFromId(long id) {
        // Parcourt la liste de voisins et renvoie celui qui correspond à l’id ou null si aucun trouvé
        for (Neighbour n: neighbours) {
            if (id == n.getId()) {
                return n;
            }
        }
        return null;
    }
}
