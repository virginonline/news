package com.virginonline.news.config;

import com.virginonline.news.repository.NewsRepository;
import com.virginonline.news.repository.NewsTypeRepository;
import com.virginonline.news.service.NewsTypeService;
import com.virginonline.news.service.impl.NewsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
@RequiredArgsConstructor
public class TestConfig {

  @Bean
  @Primary
  public NewsServiceImpl newsService(final NewsRepository newsRepository,
      final NewsTypeRepository newsTypeRepository) {
    return new NewsServiceImpl(newsRepository, newsTypeRepository);
  }

  @Bean
  public NewsRepository newsRepository() {
    return Mockito.mock(NewsRepository.class);
  }

  @Bean

  public NewsTypeRepository newsTypeRepository() {
    return Mockito.mock(NewsTypeRepository.class);
  }
}
