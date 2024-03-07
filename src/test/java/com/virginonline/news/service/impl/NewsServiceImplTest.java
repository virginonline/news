package com.virginonline.news.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.virginonline.news.config.TestConfig;
import com.virginonline.news.domain.model.News;
import com.virginonline.news.domain.model.NewsType;
import com.virginonline.news.payload.NewNewsPayload;
import com.virginonline.news.repository.NewsRepository;
import com.virginonline.news.repository.NewsTypeRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import(TestConfig.class)
@ExtendWith(MockitoExtension.class)
class NewsServiceImplTest {

  @MockBean
  private NewsTypeRepository newsTypeRepository;
  @MockBean
  private NewsRepository newsRepository;

  @Autowired
  private NewsServiceImpl newsService;

  @Test
  public void test_getAll() {
    // Arrange
    List<News> expectedNewsList = new ArrayList<>();
    expectedNewsList.add(new News(1L, "Title 1", "Summary 1", "Description 1", null));
    expectedNewsList.add(new News(2L, "Title 2", "Summary 2", "Description 2", null));

    when(newsRepository.findAll()).thenReturn(expectedNewsList);

    // Act
    List<News> actualNewsList = newsService.getAll();

    // Assert
    assertEquals(expectedNewsList, actualNewsList);
  }

  @Test
  public void test_create_news_with_valid_payload_and_existing_type() {
    // Arrange
    NewNewsPayload payload = new NewNewsPayload("Title", "Summary", "Description", "Type");
    NewsType type = new NewsType();
    when(newsTypeRepository.findByTitle(payload.title())).thenReturn(Optional.of(type));
    when(newsRepository.save(any(News.class))).thenReturn(new News());

    // Act
    News result = newsService.create(payload);

    // Assert
    assertNotNull(result);
    verify(newsTypeRepository).findByTitle(payload.title());
    verify(newsRepository).save(any(News.class));
  }

  @Test
  public void test_returns_news_object_with_given_id_if_exists() {
    // Arrange
    Long id = 1L;
    News expectedNews = new News();
    expectedNews.setId(id);
    when(newsRepository.findById(id)).thenReturn(Optional.of(expectedNews));

    // Act
    News actualNews = newsService.getById(id);

    // Assert
    assertEquals(expectedNews, actualNews);
  }

  @Test
  public void test_throws_exception_if_id_parameter_is_null() {
    // Arrange
    Long id = null;

    // Act & Assert
    assertThrows(RuntimeException.class, () -> newsService.getById(id));
  }

  @Test
  public void test_valid_type() {
    // Arrange
    String type = "validType";
    NewsType newsType = NewsType.builder()
        .id(1L)
        .title(type)
        .color("red")
        .build();
    List<News> expectedNewsList = Arrays.asList(
        News.builder()
            .id(1L)
            .title("News 1")
            .summary("Summary 1")
            .description("Description 1")
            .type(newsType)
            .build(),
        News.builder()
            .id(2L)
            .title("News 2")
            .summary("Summary 2")
            .description("Description 2")
            .type(newsType)
            .build()
    );
    when(newsTypeRepository.findByTitle(type)).thenReturn(Optional.of(newsType));
    when(newsRepository.getAllByTypeId(newsType.getId())).thenReturn(expectedNewsList);

    // Act
    List<News> result = newsService.getAllByType(type);

    // Assert
    assertEquals(expectedNewsList, result);
  }
}