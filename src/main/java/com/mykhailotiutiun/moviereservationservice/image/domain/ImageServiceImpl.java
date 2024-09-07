package com.mykhailotiutiun.moviereservationservice.image.domain;

import com.mykhailotiutiun.moviereservationservice.exception.NotFoundException;

public class ImageServiceImpl implements ImageService{

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image getById(Long id) {
         return imageRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Image create(Image image) {
        return imageRepository.create(image);
    }

    @Override
    public void deleteById(Long id) {
        imageRepository.deleteById(id);
    }
}
