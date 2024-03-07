package com.virginonline.news.service.impl;

import com.virginonline.news.domain.exception.ResourceNotFoundException;
import com.virginonline.news.domain.model.News;
import com.virginonline.news.payload.NewNewsPayload;
import com.virginonline.news.repository.NewsTypeRepository;
import com.virginonline.news.repository.NewsRepository;
import com.virginonline.news.service.NewsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

  private final NewsRepository newsRepository;
  private final NewsTypeRepository newTypeRepository;

  @Override
  public List<News> getAll() {
    return newsRepository.findAll();
  }

  @Override
  public List<News> getAllByType(String type) {
    if (type == null || type.isEmpty()) {
      return this.getAll();
    }
    var foundedType = newTypeRepository.findByTitle(type)
        .orElseThrow(() -> new ResourceNotFoundException("Type not found"));
    return newsRepository.getAllByTypeId(foundedType.getId());
  }

  @Override
  public News create(NewNewsPayload payload) {
    var type = newTypeRepository.findByTitle(payload.title())
        .orElseThrow(() -> new ResourceNotFoundException("Type not found"));
    return newsRepository.save(
        News.builder()
            .title(payload.title())
            .summary(payload.summary())
            .description(payload.description())
            .type(type)
            .build()
    );
  }

  @Override
  public void delete(Long newsId) {
    var news = this.getById(newsId);
    newsRepository.delete(news);
  }

  @Override
  public News update(Long newsId, NewNewsPayload payload) {
    var news = this.getById(newsId);
    var type = newTypeRepository.findByTitle(payload.title())
        .orElseThrow(() -> new ResourceNotFoundException("Type not found"));

    return newsRepository.save(News.builder()
        .id(news.getId())
        .title(payload.title())
        .summary(payload.summary())
        .description(payload.description())
        .type(type)
        .build());

  }

  @Override
  public News getById(Long id) {
    return newsRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("News not found"));
  }
}
