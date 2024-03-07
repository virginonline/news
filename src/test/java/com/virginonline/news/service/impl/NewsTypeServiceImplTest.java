package com.virginonline.news.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.virginonline.news.config.TestConfig;
import com.virginonline.news.domain.exception.ResourceNotFoundException;
import com.virginonline.news.domain.model.NewsType;
import com.virginonline.news.payload.NewTypePayload;
import com.virginonline.news.repository.NewsTypeRepository;
import java.util.Collections;
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
class NewsTypeServiceImplTest {

  @MockBean
  private NewsTypeRepository mockNewsTypeRepository;
  @Autowired
  private NewsTypeServiceImpl newsTypeService;

  @Test
  void testGetAll() {
    // Arrange
    final List<NewsType> expectedResult = List.of(NewsType.builder()
        .id(0L)
        .title("title")
        .color("color")
        .build());
    final List<NewsType> newsTypes = List.of(NewsType.builder()
        .id(0L)
        .title("title")
        .color("color")
        .build());
    when(mockNewsTypeRepository.findAll()).thenReturn(newsTypes);

    // Act
    final List<NewsType> result = newsTypeService.getAll();

    // Assert
    assertThat(result).isEqualTo(expectedResult);
  }

  @Test
  void testGetAll_NewsTypeRepositoryReturnsNoItems() {
    // Arrange
    when(mockNewsTypeRepository.findAll()).thenReturn(Collections.emptyList());

    // Act
    final List<NewsType> result = newsTypeService.getAll();

    // Assert
    assertThat(result).isEqualTo(Collections.emptyList());
  }

  @Test
  void testCreate() {
    // Arrange
    final NewTypePayload payload = new NewTypePayload("title", "color");
    final NewsType expectedResult = NewsType.builder()
        .id(0L)
        .title("title")
        .color("color")
        .build();
    when(mockNewsTypeRepository.existsByTitle("title")).thenReturn(false);
    final NewsType newsType = NewsType.builder()
        .id(0L)
        .title("title")
        .color("color")
        .build();
    when(mockNewsTypeRepository.save(newsType)).thenReturn(newsType);

    // Act
    final NewsType result = newsTypeService.create(payload);

    // Assert
    assertThat(result).isEqualTo(expectedResult);
  }

  @Test
  void testUpdate() {
    // Arrange
    final NewTypePayload payload = new NewTypePayload("title", "color");
    final NewsType expectedResult = NewsType.builder()
        .id(0L)
        .title("title")
        .color("color")
        .build();
    final Optional<NewsType> newsType = Optional.of(NewsType.builder()
        .id(0L)
        .title("title")
        .color("color")
        .build());
    when(mockNewsTypeRepository.findById(0L)).thenReturn(newsType);
    final NewsType newsType1 = NewsType.builder()
        .id(0L)
        .title("title")
        .color("color")
        .build();
    when(mockNewsTypeRepository.save(newsType1)).thenReturn(newsType1);

    // Act
    final NewsType result = newsTypeService.update(0L, payload);

    // Assert
    assertThat(result).isEqualTo(expectedResult);
  }

  @Test
  void testUpdate_NewsTypeRepositoryFindByIdReturnsAbsent() {
    // Arrange
    final NewTypePayload payload = new NewTypePayload("title", "color");
    when(mockNewsTypeRepository.findById(0L)).thenReturn(Optional.empty());

    // Act & Assert
    assertThatThrownBy(() -> newsTypeService.update(0L, payload))
        .isInstanceOf(ResourceNotFoundException.class);
  }

  @Test
  void testDelete() {
    // Arrange

    // Act
    newsTypeService.delete(0L);

    // Assert
    verify(mockNewsTypeRepository).deleteById(0L);
  }
}

