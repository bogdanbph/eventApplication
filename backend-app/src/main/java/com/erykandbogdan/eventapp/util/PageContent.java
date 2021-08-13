package com.erykandbogdan.eventapp.util;

import com.erykandbogdan.eventapp.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public final class PageContent<D extends BaseEntity> {

    private Long numberOfPages;
    private List<D> currentPage;

    public Long getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Long numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public List<D> getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(List<D> currentPage) {
        this.currentPage = currentPage;
    }
}
