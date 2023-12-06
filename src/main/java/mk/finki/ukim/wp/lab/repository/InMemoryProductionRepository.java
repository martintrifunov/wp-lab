package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Production;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryProductionRepository {
    public List<Production> productionList;

    public InMemoryProductionRepository() {
        productionList = new ArrayList<>();
        productionList.add(new Production("Warner Bros. Pictures", "United States", "4000 Warner Blvd, Burbank, CA 91522, USA"));
        productionList.add(new Production("Universal Pictures", "United States", "100 Universal City Plaza, Universal City, CA 91608, USA"));
        productionList.add(new Production("Ghibli", "Japan", "1-4-25 Kajino-cho, Koganei, Tokyo 184-0002, Japan"));
        productionList.add(new Production("Dharma Production", "Bollywood - s", "India"));
        productionList.add(new Production("Title Films", "United Kingdom", "26 Aybrook Street, London W1U 4AN, UK"));
    }
    public List<Production> findAll() {
        return productionList;
    }

    public Production findById(Long id){
        for (int i = 0; i < productionList.size(); i++){
            if(productionList.get(i).getId().equals(id)){
                return productionList.get(i);
            }
        }
        return null;
    }

    public Production findByName(String name){
        for (int i = 0; i < productionList.size(); i++){
            if(productionList.get(i).getName().equals(name)){
                return productionList.get(i);
            }
        }
        return null;
    }
}
