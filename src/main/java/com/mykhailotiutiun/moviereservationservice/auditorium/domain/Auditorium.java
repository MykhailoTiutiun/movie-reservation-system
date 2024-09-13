package com.mykhailotiutiun.moviereservationservice.auditorium.domain;

import java.util.Objects;

public class Auditorium {

    private Long id;
    private String name;
    private String description;

    public Auditorium() {
    }

    private Auditorium(AuditoriumBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static AuditoriumBuilder builder() {
        return new AuditoriumBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auditorium that = (Auditorium) o;
        return Objects.equals(id, that.id) && name.equals(that.name) && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    public static class AuditoriumBuilder {
        private Long id;
        private String name;
        private String description;

        public AuditoriumBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AuditoriumBuilder name(String name) {
            this.name = name;
            return this;
        }

        public AuditoriumBuilder description(String description) {
            this.description = description;
            return this;
        }

        public Auditorium build() {
            return new Auditorium(this);
        }
    }
}
