package com.virginonline.news.repository;

import com.virginonline.news.model.NewsType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NewsTypeRepository extends JpaRepository<NewsType, Long> {

  Boolean existsByTitle(String title);

  @Query(value = """  
      SELECT * FROM type t where lower(t.title) = lower(:title)
      """, nativeQuery = true)
  Optional<NewsType> findByTitle(String title);
}
