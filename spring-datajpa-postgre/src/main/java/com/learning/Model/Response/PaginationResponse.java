package com.learning.Model.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationResponse {
    private int page;
    private int size;
    private int totalPage;
    private Long totalDataCount;
    private boolean lastPage;
}
