package com.virginonline.news.rest;

import com.virginonline.news.model.NewsType;
import com.virginonline.news.payload.NewTypePayload;
import com.virginonline.news.service.NewsTypeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/news-types")
@RequiredArgsConstructor
public class TypeController {

  private final NewsTypeService typeService;

  @GetMapping
  public List<NewsType> fetchAll() {
    return typeService.getAll();
  }

  @PostMapping("/create")
  public NewsType create(@RequestBody NewTypePayload payload) {
    return typeService.create(payload);
  }

  @PatchMapping("/{id}")
  public NewsType update(@PathVariable Long id, @RequestBody NewTypePayload payload) {
    return typeService.update(id, payload);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    typeService.delete(id);
  }
}
  