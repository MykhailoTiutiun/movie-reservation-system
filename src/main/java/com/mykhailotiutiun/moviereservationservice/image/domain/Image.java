package com.mykhailotiutiun.moviereservationservice.image.domain;

import java.util.Arrays;
import java.util.Objects;

public class Image {

    private Long id;
    private byte[] data;

    public Image(){
    }

    public Image(ImageBuilder imageBuilder){
        id = imageBuilder.id;
        data = imageBuilder.data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(id, image.id) && Arrays.equals(data, image.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    public static ImageBuilder builder(){
        return new ImageBuilder();
    }

    public static class ImageBuilder {

        private Long id;
        private byte[] data;

        public ImageBuilder(){
        }

        public ImageBuilder id(Long id){
            this.id = id;
            return this;
        }

        public ImageBuilder data(byte[] data){
            this.data = data;
            return this;
        }

        public Image build(){
            return new Image(this);
        }
    }
}
