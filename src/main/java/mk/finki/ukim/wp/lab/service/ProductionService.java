package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Movie;
import mk.finki.ukim.wp.lab.model.Production;

import java.util.List;

public interface ProductionService {
    public List<Production> findAll();

    Production findById(Long id);

    Production findByName(String name);
}
