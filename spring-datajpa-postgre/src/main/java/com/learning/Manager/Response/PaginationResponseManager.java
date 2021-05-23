package com.learning.Manager.Response;

import com.learning.DTO.PaginationDTO;
import com.learning.Model.Response.PaginationResponse;

abstract class PaginationResponseManager {
    public PaginationResponse buildPaginationResponse(PaginationDTO paginationDTO) {
        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setPage(paginationDTO.getPage());
        paginationResponse.setSize(paginationDTO.getSize());
        paginationResponse.setTotalDataCount(paginationDTO.getTotalDataCount());
        paginationResponse.setTotalPage(paginationDTO.getTotalPage());
        paginationResponse.setLastPage(paginationDTO.isLastPage());

        return paginationResponse;
    }
}
