package com.mykhailotiutiun.moviereservationservice.image;

import com.mykhailotiutiun.moviereservationservice.exception.NotFoundException;
import com.mykhailotiutiun.moviereservationservice.image.domain.Image;
import com.mykhailotiutiun.moviereservationservice.image.domain.ImageRepository;
import com.mykhailotiutiun.moviereservationservice.image.domain.ImageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ImageDomainTest {

    @Mock
    private ImageRepository imageRepository;
    @InjectMocks
    private ImageServiceImpl imageService;

    @Test
    public void getByIdTest(){
        Image image = Image.builder().id(1L).build();
        when(imageRepository.findById(image.getId())).thenReturn(Optional.of(image));
        assertEquals(image, imageService.getById(image.getId()));

        when(imageRepository.findById(image.getId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> imageService.getById(image.getId()));
    }

    @Test
    public void createTest(){
        Image image = Image.builder().id(1L).build();
        imageService.create(image);
        verify(imageRepository).create(image);
    }

    @Test
    public void deleteByIdTest(){
        long id = 1L;
        imageService.deleteById(id);
        verify(imageRepository).deleteById(id);
    }

}
