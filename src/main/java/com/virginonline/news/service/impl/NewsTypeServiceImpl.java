package com.virginonline.news.service.impl;

import com.virginonline.news.model.NewsType;
import com.virginonline.news.payload.NewTypePayload;
import com.virginonline.news.repository.NewsTypeRepository;
import com.virginonline.news.service.NewsTypeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsTypeServiceImpl implements NewsTypeService {

  private final NewsTypeRepository newsTypeRepository;

  @Override
  public List<NewsType> getAll() {
    return newsTypeRepository.findAll();
  }

  @Override
  public NewsType create(NewTypePayload payload) {
    var contains = newsTypeRepository.existsByTitle(payload.title());
    if (contains) {
      throw new RuntimeException("Type already exist");
    }
    return newsTypeRepository.save(NewsType.builder()
        .title(payload.title())
        .color(payload.color())
        .build());
  }

  @Override
  public NewsType update(Long id, NewTypePayload payload) {
    var type = newsTypeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("News type with id " + id + " not found"));

    return newsTypeRepository.save(NewsType.builder()
        .id(type.getId())
        .title(payload.title())
        .color(payload.color())
        .build());
  }

  @Override
  public void delete(Long id) {
    newsTypeRepository.deleteById(id);
  }
}
