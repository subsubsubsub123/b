package com.example.shoppinglist.controller;

import com.example.shoppinglist.model.ShoppingItem;
import com.example.shoppinglist.repository.ShoppingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ShoppingController {

    @Autowired
    private ShoppingItemRepository repository;

    @GetMapping("/")
    public String listItems(Model model) {
        model.addAttribute("items", repository.findAll());
        return "index";
    }

    @PostMapping("/add")
    public String addItem(@RequestParam String name, @RequestParam int quantity) {
        ShoppingItem item = new ShoppingItem();
        item.setName(name);
        item.setQuantity(quantity);
        repository.save(item);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }
}
