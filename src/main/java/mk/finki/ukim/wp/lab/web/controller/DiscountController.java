package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.model.Discount;
import mk.finki.ukim.wp.lab.model.Production;
import mk.finki.ukim.wp.lab.service.DiscountService;
import mk.finki.ukim.wp.lab.service.MovieService;
import mk.finki.ukim.wp.lab.service.ProductionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/discounts")
public class DiscountController {
    public final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping("/add")
    public String addDiscount(Model model) {
        List<Discount> productions = discountService.findAll();
        model.addAttribute("productions", productions);
        return"addDiscount";
    }

    @PostMapping("/add")
    public String submitAddedDiscount(@RequestParam String discountName,
                                   @RequestParam int discountPercent) {

        discountService.saveDiscount(discountName, discountPercent);
        return "redirect:/movies";
    }
}

