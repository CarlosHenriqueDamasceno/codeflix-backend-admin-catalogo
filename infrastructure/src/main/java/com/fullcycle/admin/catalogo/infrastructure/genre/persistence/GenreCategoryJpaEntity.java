package com.fullcycle.admin.catalogo.infrastructure.genre.persistence;

import com.fullcycle.admin.catalogo.domain.category.CategoryID;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "genres_categories")
public class GenreCategoryJpaEntity {

    @EmbeddedId
    private GenreCategoryID id;

    @ManyToOne
    @MapsId("genreId")
    private GenreJpaEntity genre;

    public GenreCategoryJpaEntity() {
    }

    private GenreCategoryJpaEntity(final GenreJpaEntity aGenre, final CategoryID aCategoryID) {
        this.id = GenreCategoryID.from(aGenre.getId(), aCategoryID.getValue());
        this.genre = aGenre;
    }

    public static GenreCategoryJpaEntity from(final GenreJpaEntity aGenre, final CategoryID aCategoryId) {
        return new GenreCategoryJpaEntity(aGenre, aCategoryId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreCategoryJpaEntity that = (GenreCategoryJpaEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getGenre(), that.getGenre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getGenre());
    }

    public GenreCategoryID getId() {
        return id;
    }

    public void setId(GenreCategoryID id) {
        this.id = id;
    }

    public GenreJpaEntity getGenre() {
        return genre;
    }

    public void setGenre(GenreJpaEntity genre) {
        this.genre = genre;
    }
}
