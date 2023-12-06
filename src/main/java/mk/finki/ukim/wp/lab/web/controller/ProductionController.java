package mk.finki.ukim.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.wp.lab.model.User;
import mk.finki.ukim.wp.lab.service.MovieService;
import mk.finki.ukim.wp.lab.service.ProductionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/productions")
public class ProductionController {
    public final ProductionService productionService;

    public ProductionController ( ProductionService productionService) {
        this.productionService = productionService;
    }

    @GetMapping("/add")
    public String addProductionForm(HttpServletRequest req) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        return "addProduction";
    }

    @PostMapping("/add")
    public String addProduction(@RequestParam String productionName,
                                @RequestParam String productionCountry,
                                @RequestParam String productionAddress) {
        productionService.save(productionName, productionCountry, productionAddress);
        return "redirect:/movies";
    }
}
