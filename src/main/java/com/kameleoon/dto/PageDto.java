package com.kameleoon.dto;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Data
public class PageDto {
    public int pageNumber=0;
    public int pageSize=10;
    public Pageable pageable() {
        return PageRequest.of(pageNumber, pageSize);
    }
}
