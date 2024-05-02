package com.cxw.finddeliciousfood.web;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class PageResult<T> {

    private int pageNum = 0;
    private int pageSize = 20;
    private int totalPages;
    private long total;

    private List<T> content = Collections.emptyList();
}
