package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Movie;
import mk.finki.ukim.wp.lab.model.Production;

import java.util.List;
import java.util.Optional;

public interface ProductionService {
    public List<Production> findAll();
    Optional<Production> findById(Long id);
    Production save(String name,String country, String address);
    Optional<Production> edit(Long id,String name,String country,String address);
    void deleteById(Long id);

}
