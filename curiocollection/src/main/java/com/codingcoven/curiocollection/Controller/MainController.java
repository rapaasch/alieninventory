package com.codingcoven.curiocollection.Controller;

import com.codingcoven.curiocollection.DAO.*;
import com.codingcoven.curiocollection.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    ArticleRepository articles;

    @Autowired
    LocationRepository locations;

    @Autowired
    CategoriesRepository categories;

    @Autowired
    ItemsRepository items;

    @Autowired
    AuthorRepository authors;

    @GetMapping({"/index", "/"})
    public String displayIndex(Model model) {
        List<Article> a = articles.findAll();
        List<Location> l = locations.findAll();
        List<Author> h = authors.findAll();
        List<Categories> c = categories.findAll();
        List<Items> i = items.findAll();
        model.addAttribute("article", a);
        model.addAttribute("location", l);
        model.addAttribute("categories", c);
        model.addAttribute("items", i);
        model.addAttribute("author", h);
        return "index";
    }

    @GetMapping("/login")
    public String displayLogin(){
        return "login";
    }
}
