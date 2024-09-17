package com.mykhailotiutiun.moviereservationservice.genre.domain;

import java.util.Objects;

public class Genre {

    private Long id;
    private String name;

    public Genre() {
    }

    public Genre(GenreBuilder genreBuilder) {
        this.id = genreBuilder.id;
        this.name = genreBuilder.name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(id, genre.id) && name.equals(genre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public static GenreBuilder builder(){
        return new GenreBuilder();
    }

    public static class GenreBuilder {
        private Long id;
        private String name;

        public GenreBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public GenreBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Genre build() {
            return new Genre(this);
        }
    }
}
