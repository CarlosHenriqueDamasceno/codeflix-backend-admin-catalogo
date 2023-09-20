package com.fullcycle.admin.catalogo.application.category.delete;

import com.fullcycle.admin.catalogo.application.UseCaseTest;
import com.fullcycle.admin.catalogo.domain.category.Category;
import com.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import com.fullcycle.admin.catalogo.domain.category.CategoryID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class DeleteCategoryUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultDeleteCategoryUseCase useCase;

    @Mock
    private CategoryGateway categoryGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(categoryGateway);
    }

    @Test
    public void givenAValidId_whenCallDeleteCategory_shouldBeOk() {
        final var aCategory = Category.newCategory("Filmes", "A categoria mais assistida", true);
        final var expectedCategoryId = aCategory.getId();

        doNothing()
                .when(categoryGateway).deleteById(eq(expectedCategoryId));

        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedCategoryId.getValue()));

        verify(categoryGateway, times(1)).deleteById(eq(expectedCategoryId));
    }

    @Test
    public void givenAInvalidId_whenCallDeleteCategory_shouldBeOk() {
        final var expectedCategoryId = CategoryID.from("123");

        doNothing()
                .when(categoryGateway).deleteById(eq(expectedCategoryId));

        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedCategoryId.getValue()));
        verify(categoryGateway, times(1)).deleteById(eq(expectedCategoryId));
    }

    @Test
    public void givenAValidId_whenGatewayThrowsError_shouldReturnException() {
        final var expectedCategoryId = CategoryID.from("123");

        doThrow(new IllegalStateException("Gateway error"))
                .when(categoryGateway).deleteById(eq(expectedCategoryId));

        Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(expectedCategoryId.getValue()));
        verify(categoryGateway, times(1)).deleteById(eq(expectedCategoryId));
    }
}
