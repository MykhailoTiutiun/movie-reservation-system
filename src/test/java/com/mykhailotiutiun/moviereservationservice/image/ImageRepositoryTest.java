package com.mykhailotiutiun.moviereservationservice.image;

import com.mykhailotiutiun.moviereservationservice.exception.NotFoundException;
import com.mykhailotiutiun.moviereservationservice.image.datasource.ImageRepositoryImpl;
import com.mykhailotiutiun.moviereservationservice.image.domain.Image;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class ImageRepositoryTest {

    public static final String FILENAME_PATTERN = "%d.jpeg";
    private final String directory = System.getProperty("user.dir") + "/testImages";
    private final ImageRepositoryImpl imageRepository;

    public ImageRepositoryTest() {
        this.imageRepository = new ImageRepositoryImpl(directory, FILENAME_PATTERN);
    }

    @Test
    public void getById() throws IOException {
        long notExistedId = 2L;
        Image image = Image.builder()
                .id(1L)
                .data(new byte[]{1, 2})
                .build();

        File folder = new File(directory);
        File file = new File(directory + "/" + String.format(FILENAME_PATTERN, image.getId()));

        try {
            if (!folder.exists()) {
                if (!folder.mkdirs()) {
                    throw new RuntimeException();
                }
            }

            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                fileOutputStream.write(image.getData());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            assertEquals(image, imageRepository.findById(image.getId()).orElseThrow(NotFoundException::new));
            assertTrue(imageRepository.findById(notExistedId).isEmpty());

        } finally {
            FileUtils.deleteDirectory(folder);
        }
    }

    @Test
    public void createTest() throws IOException {
        Image image = Image.builder()
                .data(new byte[]{1, 2})
                .build();
        imageRepository.create(image);
        File file = new File(directory + "/" + String.format(FILENAME_PATTERN, image.getId()));
        try {
            assertTrue(file.exists());

            assertArrayEquals(image.getData(), Files.readAllBytes(file.toPath()));
        } finally {
            FileUtils.deleteDirectory(new File(directory));
        }
    }

    @Test
    public void deleteByIdTest() throws IOException {
        Image image = Image.builder()
                .id(1L)
                .data(new byte[]{1, 2})
                .build();

        File folder = new File(directory);
        File file = new File(directory + "/" + String.format(FILENAME_PATTERN, image.getId()));

        try {
            if (!folder.exists()) {
                if (!folder.mkdirs()) {
                    throw new RuntimeException();
                }
            }

            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                fileOutputStream.write(image.getData());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            imageRepository.deleteById(image.getId());

            assertFalse(file.exists());
        } finally {
            FileUtils.deleteDirectory(folder);
        }
    }
}
