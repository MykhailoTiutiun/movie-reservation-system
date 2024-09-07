package com.mykhailotiutiun.moviereservationservice.image.datasource;

import com.mykhailotiutiun.moviereservationservice.image.domain.Image;
import com.mykhailotiutiun.moviereservationservice.image.domain.ImageRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

public class ImageRepositoryImpl implements ImageRepository {

    private final String directory;
    private final String filenamePattern;
    private final File idFile;

    public ImageRepositoryImpl(String directory, String filenamePattern) {
        this.directory = directory;
        this.filenamePattern = filenamePattern;
        this.idFile = new File(directory + "/" + "lastId");
    }

    @Override
    public Optional<Image> findById(Long id) {
        try {
            byte[] data = Files.readAllBytes(new File(directory + "/" + String.format(filenamePattern, id)).toPath());
            return Optional.ofNullable(Image.builder()
                    .id(id)
                    .data(data)
                    .build());
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    private Long incrementAndGetLastId() {
        synchronized (idFile) {
            try {
                long id = Long.parseLong(Files.readString(idFile.toPath()));
                id++;
                Files.writeString(idFile.toPath(), Long.toString(id));
                return id;
            } catch (IOException e) {
                try {
                    if(!idFile.createNewFile()){
                        throw new RuntimeException();
                    }
                    Files.writeString(idFile.toPath(), Long.toString(1L));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                return 1L;
            }
        }
    }

    @Override
    public Image create(Image image) {
        File folder = new File(directory);
        if(!folder.exists()){
            if(!folder.mkdirs()){
                throw new RuntimeException();
            }
        }

        image.setId(incrementAndGetLastId());

        File file = new File(directory + "/" + String.format(filenamePattern, image.getId()));
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(image.getData());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    @Override
    public void deleteById(Long id) {
        File file = new File(directory + "/" + String.format(filenamePattern, id));
        if (!file.delete()) {
            throw new RuntimeException();
        }
    }
}
