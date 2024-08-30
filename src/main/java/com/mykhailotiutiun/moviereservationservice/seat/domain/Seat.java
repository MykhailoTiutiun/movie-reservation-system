package com.mykhailotiutiun.moviereservationservice.seat.domain;

import com.mykhailotiutiun.moviereservationservice.auditorium.domain.Auditorium;
import com.mykhailotiutiun.moviereservationservice.showtime.domain.Showtime;

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

    public static SeatBuilder builder(){
        return new SeatBuilder();
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


        public Seat build(){
            return new Seat(this);
        }
    }
}
