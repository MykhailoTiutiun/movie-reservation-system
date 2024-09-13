package com.mykhailotiutiun.moviereservationservice.showtime.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Showtime {

    private Long id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long auditoriumId;

    public Showtime() {
    }

    private Showtime(ShowtimeBuilder builder) {
        this.id = builder.id;
        this.date = builder.date;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.auditoriumId = builder.auditoriumId;
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

    public Long getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(Long auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    public static ShowtimeBuilder builder() {
        return new ShowtimeBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Showtime showtime = (Showtime) o;
        return Objects.equals(id, showtime.id) && date.equals(showtime.date) && startTime.equals(showtime.startTime) && endTime.equals(showtime.endTime) && Objects.equals(auditoriumId, showtime.auditoriumId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, startTime, endTime, auditoriumId);
    }

    public static class ShowtimeBuilder {
        private Long id;
        private LocalDate date;
        private LocalTime startTime;
        private LocalTime endTime;
        private Long auditoriumId;

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

        public ShowtimeBuilder auditoriumId(Long auditoriumId){
            this.auditoriumId = auditoriumId;
            return this;
        }

        public Showtime build() {
            return new Showtime(this);
        }
    }
}
