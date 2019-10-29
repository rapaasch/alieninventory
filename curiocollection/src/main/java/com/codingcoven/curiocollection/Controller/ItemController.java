package com.codingcoven.curiocollection.Controller;

import com.codingcoven.curiocollection.DAO.*;
import com.codingcoven.curiocollection.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ItemController {
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

    @GetMapping("/view_all_collection")
    public String displayItems(Model model) {
        List<Article> a = articles.findAll();
        List<Location> l = locations.findAll();
        List<Author> h = authors.findAll();
        List<Categories> c = categories.findAll();
        List<Items> i = items.findAll();
        model.addAttribute("articles", a);
        model.addAttribute("locations", l);
        model.addAttribute("categories", c);
        model.addAttribute("items", i);
        model.addAttribute("authors", h);
        return "view_all_collection";
    }

    @GetMapping("/searchCollection")
    public String searchCollection(Model model) {
        List<Article> a = articles.findAll();
        List<Location> l = locations.findAll();
        List<Author> h = authors.findAll();
        List<Categories> c = categories.findAll();
        List<Items> i = items.findAll();
        model.addAttribute("articles", a);
        model.addAttribute("locations", l);
        model.addAttribute("categories", c);
        model.addAttribute("items", i);
        model.addAttribute("authors", h);
        return "searchCollection";
    }

    @GetMapping("/search_collection_admin")
    public String searchCollectionAdmin(Model model) {
        List<Article> a = articles.findAll();
        List<Location> l = locations.findAll();
        List<Author> h = authors.findAll();
        List<Categories> c = categories.findAll();
        List<Items> i = items.findAll();
        model.addAttribute("articles", a);
        model.addAttribute("locations", l);
        model.addAttribute("categories", c);
        model.addAttribute("items", i);
        model.addAttribute("authors", h);
        return "search_collection_admin";
    }

    @GetMapping("/add_item")
    public String addItems(Model model) {
        List<Article> a = articles.findAll();
        List<Location> l = locations.findAll();
        List<Author> h = authors.findAll();
        List<Categories> c = categories.findAll();
        List<Items> i = items.findAll();
        model.addAttribute("articles", a);
        model.addAttribute("locations", l);
        model.addAttribute("categories", c);
        model.addAttribute("items", i);
        model.addAttribute("authors", h);
        return "add_item";
    }

    @GetMapping("deleteItem")
    public String deleteItem(Integer id) {
        items.deleteById(id);
        return "redirect:/search_collection_admin";
    }

    @GetMapping("edit_item")
    public String edit_item(Integer id, Model model) {
        Items i = items.findById(id).orElse(null);
        List<Location> l = locations.findAll();
        List<Categories> c = categories.findAll();
        model.addAttribute("categories", c);
        model.addAttribute("locations", l);
        model.addAttribute("item", i);
        return "edit_item";
    }

    @GetMapping("view_item_details")
    public String viewDetails(Integer id, Model model) {
        Items i = items.findById(id).orElse(null);
        List<Location> l = locations.findAll();
        List<Categories> c = categories.findAll();
        model.addAttribute("categories", c);
        model.addAttribute("locations", l);
        model.addAttribute("item", i);
        return "view_edit_items";
    }

    @PostMapping("edit_item")
    public String performEditItem(Items i, HttpServletRequest request) {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        i = items.getOne(itemId);
        String itemName = request.getParameter("itemName");
        int quantity = Integer.parseInt(request.getParameter("itemQty"));
        BigDecimal value = new BigDecimal(request.getParameter("itemValue"));
        String itemAbout = request.getParameter("itemDescription");
        String[] itemCats = request.getParameterValues("itemCats");
        List<Categories> cats = new ArrayList<>();
        for (String s : itemCats) {
            cats.add(categories.getOne(Integer.parseInt(s)));
        }
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        String locId = request.getParameter("loc");
        i.setCategories(cats);
        i.setItemAbout(itemAbout);
        i.setItemId(itemId);
        i.setItemName(itemName);
        i.setLocation(locations.getOne(Integer.parseInt(locId)));
        i.setQuantity(quantity);
        i.setValue(value);
        i.setDateFound(date);
        items.save(i);
        return "redirect:/search_collection_admin";
    }

    @PostMapping("add_item")
    public String performAddItem(Items i, HttpServletRequest request) {
        String itemName = request.getParameter("itemName");
        int quantity = Integer.parseInt(request.getParameter("itemQty"));
        BigDecimal value = new BigDecimal(request.getParameter("itemValue"));
        String itemAbout = request.getParameter("itemDescription");
        String[] itemCats = request.getParameterValues("itemCategories");
        List<Categories> cats = new ArrayList<>();
        for (String s : itemCats) {
            cats.add(categories.getOne(Integer.parseInt(s)));
        }
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        String locId = request.getParameter("loc");
        i.setCategories(cats);
        i.setItemAbout(itemAbout);
        i.setItemName(itemName);
        i.setLocation(locations.getOne(Integer.parseInt(locId)));
        i.setQuantity(quantity);
        i.setValue(value);
        i.setDateFound(date);
        items.save(i);
        return "redirect:/search_collection_admin";
    }

}
