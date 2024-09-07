package com.mykhailotiutiun.moviereservationservice.image.domain;

import java.util.Optional;

public interface ImageRepository {

    Optional<Image> findById(Long id);
    Image create(Image image);
    void deleteById(Long id);
}
