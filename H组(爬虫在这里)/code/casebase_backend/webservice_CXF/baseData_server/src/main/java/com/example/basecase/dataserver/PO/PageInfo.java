package com.example.basecase.dataserver.PO;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageInfo<T> {
    private long total;
    private List<T> records;
}