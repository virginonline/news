package com.virginonline.news.repository;

import com.virginonline.news.model.News;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NewsRepository extends JpaRepository<News, Long> {


  List<News> getAllByTypeId(Long typeId);
}
