package com.fullcycle.admin.catalogo.infrastructure.category.presenters;

import com.fullcycle.admin.catalogo.application.category.retrieve.get.CategoryOutput;
import com.fullcycle.admin.catalogo.application.category.retrieve.list.CategoryListOutput;
import com.fullcycle.admin.catalogo.infrastructure.category.models.CategoryListResponse;
import com.fullcycle.admin.catalogo.infrastructure.category.models.CategoryResponse;

public interface CategoryApiPresenter {
    static CategoryResponse present(final CategoryOutput categoryOutput) {
        return new CategoryResponse(
                categoryOutput.id().getValue(),
                categoryOutput.name(),
                categoryOutput.description(),
                categoryOutput.isActive(),
                categoryOutput.createdAt(),
                categoryOutput.updatedAt(),
                categoryOutput.deletedAt()
        );
    }

    static CategoryListResponse present(final CategoryListOutput categoryListOutput) {
        return new CategoryListResponse(
                categoryListOutput.id().getValue(),
                categoryListOutput.name(),
                categoryListOutput.description(),
                categoryListOutput.isActive(),
                categoryListOutput.createdAt(),
                categoryListOutput.deletedAt()
        );
    }
}
