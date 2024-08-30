package com.mykhailotiutiun.moviereservationservice.movie.domain;

public class Movie {

    private Long id;
    private String title;
    private String description;

    public Movie() {
    }

    private Movie(MovieBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static MovieBuilder builder(){
        return new MovieBuilder();
    }

    public static class MovieBuilder {
        private Long id;
        private String title;
        private String description;

        public MovieBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public MovieBuilder title(String title) {
            this.title = title;
            return this;
        }

        public MovieBuilder description(String description) {
            this.description = description;
            return this;
        }

        public Movie build(){
            return new Movie(this);
        }
    }
}
