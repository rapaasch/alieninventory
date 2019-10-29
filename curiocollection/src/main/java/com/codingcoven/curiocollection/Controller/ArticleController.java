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
import java.util.ArrayList;
import java.util.List;

@Controller
public class ArticleController {
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

    @GetMapping("/add_article")
    public String addArticle(Model model) {
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
        return "add_article";
    }

    @GetMapping("/searchArticles")
    public String searchArticles(Model model) {
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
        return "searchArticles";
    }

    @GetMapping("deleteArticle")
    public String deleteArticle(Integer id) {
        articles.deleteById(id);
        return "redirect:/search_collection_admin";
    }

    @GetMapping("delete")
    public String delete(Integer id) {
        articles.deleteById(id);
        return "redirect:/searchArticles";
    }

   @GetMapping("preview_article")
    public String previewArticles(Integer id, Model model) {
        Article a = articles.getOne(id);
        model.addAttribute("article", a);
        return "preview_article";
    }

    @GetMapping("viewArticle")
    public String viewArticles(Integer id, Model model) {
        Article a = articles.getOne(id);
        model.addAttribute("article", a);
        return "viewArticle";
    }

    @GetMapping("edit_article")
    public String editArticles(Integer id, Model model) {
        Article a = articles.getOne(id);
        List<Author> h = authors.findAll();
        List<Items> i = items.findAll();
        model.addAttribute("items", i);
        model.addAttribute("authors", h);
        model.addAttribute("article", a);
        return "edit_article";
    }

    @PostMapping("add_article")
    public String addArticlePreview(Article a, HttpServletRequest request) {
        String authorId = request.getParameter("author");
        String articleTitle = request.getParameter("articleTitle");
        String articleText = request.getParameter("articleText");
        String[] itemArray = request.getParameterValues("items");
        List<Items> articleItems = new ArrayList<>();
        for (String s : itemArray) {
            articleItems.add(items.getOne(Integer.parseInt(s)));
        }
        a.setArticleText(articleText);
        a.setArticleTitle(articleTitle);
        a.setAuthor(authors.getOne(Integer.parseInt(authorId)));
        articles.save(a);
        int artId = a.getArticleId();
        return "redirect:/preview_article?id=" + artId;
    }

    @PostMapping("edit_article")
    public String performEditArticle(Article a, HttpServletRequest request) {
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        a = articles.getOne(articleId);
        String authorId = request.getParameter("author");
        String articleTitle = request.getParameter("articleTitle");
        String articleText = request.getParameter("articleText");
        String[] itemArray = request.getParameterValues("items");
        List<Items> articleItems = new ArrayList<>();
        for (String s : itemArray) {
            articleItems.add(items.getOne(Integer.parseInt(s)));
        }
        a.setArticleText(articleText);
        a.setArticleTitle(articleTitle);
        a.setArticleId(articleId);
        a.setAuthor(authors.getOne(Integer.parseInt(authorId)));
        articles.save(a);
        return "redirect:/search_collection_admin";
    }

}
