package com.fullcycle.admin.catalogo.infrastructure.category.models;

import com.fullcycle.admin.catalogo.JacksonTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.time.Instant;

@JacksonTest
public class CategoryResponseTest {

    @Autowired
    private JacksonTester<CategoryResponse> json;

    @Test
    public void testMarshall() throws IOException {
        final var expectedId = "123";
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = false;
        final var expectedUpdatedAt = Instant.now();
        final var expectedCreatedAt = Instant.now();
        final var expectedDeletedAt = Instant.now();

        final var response = new CategoryResponse(
                expectedId,
                expectedName,
                expectedDescription,
                expectedIsActive,
                expectedCreatedAt,
                expectedUpdatedAt,
                expectedDeletedAt
        );

        final var actualJson = this.json.write(response);

        Assertions.assertThat(actualJson)
                .hasJsonPath("$.id", expectedId)
                .hasJsonPath("$.name", expectedName)
                .hasJsonPath("$.description", expectedDescription)
                .hasJsonPath("$.is_active", expectedIsActive)
                .hasJsonPath("$.created_at", expectedCreatedAt.toString())
                .hasJsonPath("$.updated_at", expectedUpdatedAt.toString())
                .hasJsonPath("$.deleted_at", expectedDeletedAt.toString());

    }

    @Test
    public void testUnmarshall() throws IOException {
        final var expectedId = "123";
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = false;
        final var expectedUpdatedAt = Instant.now();
        final var expectedCreatedAt = Instant.now();
        final var expectedDeletedAt = Instant.now();

        final var json = """
                     {
                        "id": "%s",
                        "name": "%s",
                        "description": "%s",
                        "is_active": "%s",
                        "created_at": "%s",
                        "updated_at": "%s",
                        "deleted_at": "%s"
                     }
                """.formatted(
                expectedId,
                expectedName,
                expectedDescription,
                expectedIsActive,
                expectedCreatedAt.toString(),
                expectedUpdatedAt.toString(),
                expectedDeletedAt.toString()
        );

        final var actualJson = this.json.parse(json);

        Assertions.assertThat(actualJson)
                .hasFieldOrPropertyWithValue("id", expectedId)
                .hasFieldOrPropertyWithValue("name", expectedName)
                .hasFieldOrPropertyWithValue("description", expectedDescription)
                .hasFieldOrPropertyWithValue("active", expectedIsActive)
                .hasFieldOrPropertyWithValue("createdAt", expectedCreatedAt)
                .hasFieldOrPropertyWithValue("updatedAt", expectedUpdatedAt)
                .hasFieldOrPropertyWithValue("deletedAt", expectedDeletedAt);
    }
}
