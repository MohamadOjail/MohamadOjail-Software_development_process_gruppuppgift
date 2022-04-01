package se.ya.videobutik.temp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Category {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "category_id", nullable = false)
    private Object categoryId;
    @Basic
    @Column(name = "name", nullable = false, length = 25)
    private String name;
    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;
    @OneToMany(mappedBy = "categoryByCategoryId")
    private Collection<FilmCategory> filmCategoriesByCategoryId;

    public Object getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Object categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Category category = (Category) o;
        return Objects.equals(categoryId, category.categoryId) && Objects.equals(name, category.name) && Objects.equals(lastUpdate, category.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, name, lastUpdate);
    }

    public Collection<FilmCategory> getFilmCategoriesByCategoryId() {
        return filmCategoriesByCategoryId;
    }

    public void setFilmCategoriesByCategoryId(Collection<FilmCategory> filmCategoriesByCategoryId) {
        this.filmCategoriesByCategoryId = filmCategoriesByCategoryId;
    }
}
