package se.ya.videobutik.temp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Film {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "film_id", nullable = false)
    private Object filmId;
    @Basic
    @Column(name = "title", nullable = false, length = 128)
    private String title;
    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;
    @Basic
    @Column(name = "release_year", nullable = true)
    private Object releaseYear;
    @Basic
    @Column(name = "language_id", nullable = false)
    private Object languageId;
    @Basic
    @Column(name = "original_language_id", nullable = true)
    private Object originalLanguageId;
    @Basic
    @Column(name = "rental_duration", nullable = false)
    private Object rentalDuration;
    @Basic
    @Column(name = "rental_rate", nullable = false, precision = 2)
    private BigDecimal rentalRate;
    @Basic
    @Column(name = "length", nullable = true)
    private Object length;
    @Basic
    @Column(name = "replacement_cost", nullable = false, precision = 2)
    private BigDecimal replacementCost;
    @Basic
    @Column(name = "rating", nullable = true)
    private Object rating;
    @Basic
    @Column(name = "special_features", nullable = true)
    private Object specialFeatures;
    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;
    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "language_id", nullable = false)
    private Language languageByLanguageId;
    @ManyToOne
    @JoinColumn(name = "original_language_id", referencedColumnName = "language_id")
    private Language languageByOriginalLanguageId;
    @OneToMany(mappedBy = "filmByFilmId")
    private Collection<FilmActor> filmActorsByFilmId;
    @OneToMany(mappedBy = "filmByFilmId")
    private Collection<FilmCategory> filmCategoriesByFilmId;
    @OneToMany(mappedBy = "filmByFilmId")
    private Collection<Inventory> inventoriesByFilmId;

    public Object getFilmId() {
        return filmId;
    }

    public void setFilmId(Object filmId) {
        this.filmId = filmId;
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

    public Object getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Object releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Object getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Object languageId) {
        this.languageId = languageId;
    }

    public Object getOriginalLanguageId() {
        return originalLanguageId;
    }

    public void setOriginalLanguageId(Object originalLanguageId) {
        this.originalLanguageId = originalLanguageId;
    }

    public Object getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(Object rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }

    public Object getLength() {
        return length;
    }

    public void setLength(Object length) {
        this.length = length;
    }

    public BigDecimal getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }

    public Object getRating() {
        return rating;
    }

    public void setRating(Object rating) {
        this.rating = rating;
    }

    public Object getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(Object specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(filmId, film.filmId) && Objects.equals(title, film.title) && Objects.equals(description, film.description) && Objects.equals(releaseYear, film.releaseYear) && Objects.equals(languageId, film.languageId) && Objects.equals(originalLanguageId, film.originalLanguageId) && Objects.equals(rentalDuration, film.rentalDuration) && Objects.equals(rentalRate, film.rentalRate) && Objects.equals(length, film.length) && Objects.equals(replacementCost, film.replacementCost) && Objects.equals(rating, film.rating) && Objects.equals(specialFeatures, film.specialFeatures) && Objects.equals(lastUpdate, film.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId, title, description, releaseYear, languageId, originalLanguageId, rentalDuration, rentalRate, length, replacementCost, rating, specialFeatures, lastUpdate);
    }

    public Language getLanguageByLanguageId() {
        return languageByLanguageId;
    }

    public void setLanguageByLanguageId(Language languageByLanguageId) {
        this.languageByLanguageId = languageByLanguageId;
    }

    public Language getLanguageByOriginalLanguageId() {
        return languageByOriginalLanguageId;
    }

    public void setLanguageByOriginalLanguageId(Language languageByOriginalLanguageId) {
        this.languageByOriginalLanguageId = languageByOriginalLanguageId;
    }

    public Collection<FilmActor> getFilmActorsByFilmId() {
        return filmActorsByFilmId;
    }

    public void setFilmActorsByFilmId(Collection<FilmActor> filmActorsByFilmId) {
        this.filmActorsByFilmId = filmActorsByFilmId;
    }

    public Collection<FilmCategory> getFilmCategoriesByFilmId() {
        return filmCategoriesByFilmId;
    }

    public void setFilmCategoriesByFilmId(Collection<FilmCategory> filmCategoriesByFilmId) {
        this.filmCategoriesByFilmId = filmCategoriesByFilmId;
    }

    public Collection<Inventory> getInventoriesByFilmId() {
        return inventoriesByFilmId;
    }

    public void setInventoriesByFilmId(Collection<Inventory> inventoriesByFilmId) {
        this.inventoriesByFilmId = inventoriesByFilmId;
    }
}
