package com.virginonline.news.service;

import com.virginonline.news.model.News;
import com.virginonline.news.payload.NewNewsPayload;
import java.util.List;
import org.springframework.data.domain.Page;

public interface NewsService {

  List<News> getAll();

  Page<News> getAllByPageable(Integer pageNumber, Integer pageSize, String sort);

  List<News> getAllByType(String type);

  News create(NewNewsPayload payload);

  void delete(Long newsId);

  News update(Long newsId, NewNewsPayload payload);

  News getById(Long id);
}
