package com.rearwave.blog.model;

import lombok.Data;

import java.util.List;

/**
 * @author sunyi
 */

@Data
public class Page<T> {

    private int pageNum = 1;

    private int pageSize = 10;

    private long total = 0L;

    private int pages = 0;

    private List<T> rows;
}
