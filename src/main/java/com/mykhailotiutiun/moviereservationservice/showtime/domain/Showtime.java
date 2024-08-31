package com.mykhailotiutiun.moviereservationservice.showtime.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Showtime {

    private Long id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public Showtime() {
    }

    private Showtime(ShowtimeBuilder builder) {
        this.id = builder.id;
        this.date = builder.date;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public static ShowtimeBuilder builder() {
        return new ShowtimeBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Showtime showtime = (Showtime) o;
        return Objects.equals(id, showtime.id) && date.equals(showtime.date) && startTime.equals(showtime.startTime) && endTime.equals(showtime.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, startTime, endTime);
    }

    public static class ShowtimeBuilder {
        private Long id;
        private LocalDate date;
        private LocalTime startTime;
        private LocalTime endTime;

        public ShowtimeBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ShowtimeBuilder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public ShowtimeBuilder startTime(LocalTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public ShowtimeBuilder endTime(LocalTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Showtime build() {
            return new Showtime(this);
        }
    }
}
