package com.example.raiseTechcoursetask10.service;

import com.example.raiseTechcoursetask10.entity.Skiresort;

import java.util.List;

public interface SkiresortService {
    // skiresortテーブルを全部取得する
    List<Skiresort> findAll();
}
