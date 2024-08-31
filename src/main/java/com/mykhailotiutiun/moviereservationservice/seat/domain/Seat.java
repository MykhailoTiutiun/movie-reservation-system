package com.mykhailotiutiun.moviereservationservice.seat.domain;

import java.util.Objects;

public class Seat {

    private Long id;
    private String name;
    private Boolean availability;

    public Seat() {
    }

    private Seat(SeatBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.availability = builder.availability;
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

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public static SeatBuilder builder() {
        return new SeatBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return Objects.equals(id, seat.id) && name.equals(seat.name) && Objects.equals(availability, seat.availability);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, availability);
    }

    public static class SeatBuilder {
        private Long id;
        private String name;
        private Boolean availability;


        public SeatBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public SeatBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SeatBuilder availability(Boolean availability) {
            this.availability = availability;
            return this;
        }


        public Seat build() {
            return new Seat(this);
        }
    }
}
