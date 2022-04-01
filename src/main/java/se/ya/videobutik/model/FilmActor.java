package se.ya.videobutik.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "film_actor")
public class FilmActor {
    @EmbeddedId
    private FilmActorId id;
    @MapsId("filmId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id")
    private Film film;

    @MapsId("actorId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "actor_id", nullable = false)
    private Actor actor;

    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

    public Instant getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Instant lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public FilmActorId getId() {
        return id;
    }

    public void setId(FilmActorId id) {
        this.id = id;
    }

    //TODO Reverse Engineering! Migrate other columns to the entity
}