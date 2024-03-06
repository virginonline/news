package com.virginonline.news.rest;

import com.virginonline.news.model.News;
import com.virginonline.news.payload.NewNewsPayload;
import com.virginonline.news.service.NewsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/news")
@RequiredArgsConstructor
public class NewsController {

  private final NewsService newsService;

  @GetMapping
  public List<News> fetchAll(@RequestParam(required = false) String type) {
    return newsService.getAllByType(type);
  }

  @GetMapping("/{id}")
  public News fetchById(@PathVariable Long id) {
    return newsService.getById(id);
  }

  @PatchMapping("/{id}")
  public News updateNewsById(@PathVariable Long id, @RequestBody NewNewsPayload payload) {
    return newsService.update(id, payload);
  }

  @DeleteMapping("/{id}")
  public void deleteNewsById(@PathVariable Long id) {
    newsService.delete(id);
  }
}
