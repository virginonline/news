package com.virginonline.news.service;

import com.virginonline.news.model.NewsType;
import com.virginonline.news.payload.NewTypePayload;
import java.util.List;

public interface NewsTypeService {
  List<NewsType> getAll();
  NewsType create(NewTypePayload payload);
  NewsType update(Long id,NewTypePayload payload);
  void delete(Long id);
}
