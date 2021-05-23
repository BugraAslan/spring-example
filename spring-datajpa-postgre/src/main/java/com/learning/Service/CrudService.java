package com.learning.Service;

import com.learning.DTO.DTOInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CrudService {

    DTOInterface update(DTOInterface dtoInterface);

    DTOInterface create(DTOInterface dtoInterface);

    List<DTOInterface> getAll();

    void deleteById(Long id);

    DTOInterface getById(Long id);
}
