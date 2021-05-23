package com.learning.Aggregator;

import com.learning.DTO.PaginationDTO;
import org.springframework.data.domain.Page;

abstract class PaginationAggregator {

    public PaginationDTO preparePaginationDTOByPage(Page<?> page) {
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPage(page.getNumber());
        paginationDTO.setSize(page.getSize());
        paginationDTO.setTotalPage(page.getTotalPages());
        paginationDTO.setTotalDataCount(page.getTotalElements());
        paginationDTO.setLastPage(page.isLast());

        return paginationDTO;
    }
}
