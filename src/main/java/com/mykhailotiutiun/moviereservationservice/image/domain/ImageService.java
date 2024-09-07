package com.mykhailotiutiun.moviereservationservice.image.domain;

public interface ImageService {

    Image getById(Long id);

    Image create(Image image);
    void deleteById(Long id);
}
