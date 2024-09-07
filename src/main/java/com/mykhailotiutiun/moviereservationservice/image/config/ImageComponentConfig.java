package com.mykhailotiutiun.moviereservationservice.image.config;

import com.mykhailotiutiun.moviereservationservice.image.datasource.ImageRepositoryImpl;
import com.mykhailotiutiun.moviereservationservice.image.domain.ImageRepository;
import com.mykhailotiutiun.moviereservationservice.image.domain.ImageService;
import com.mykhailotiutiun.moviereservationservice.image.domain.ImageServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImageComponentConfig {

    private final static String FILENAME_PATTERN = "%d.jpeg";
    private final String directoryPath = System.getProperty("user.dir") + "/images";

    @Bean
    public ImageService imageService(ImageRepository imageRepository){
        return new ImageServiceImpl(imageRepository);
    }

    @Bean
    public ImageRepository imageRepository(){
        return new ImageRepositoryImpl(directoryPath, FILENAME_PATTERN);
    }
}
