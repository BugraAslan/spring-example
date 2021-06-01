package com.learning.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationDTO implements DTOInterface {
    private int page;
    private int size;
    private int totalPage;
    private Long totalDataCount;
    private boolean lastPage;
}
