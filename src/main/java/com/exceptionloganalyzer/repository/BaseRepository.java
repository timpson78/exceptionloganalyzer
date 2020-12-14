package com.exceptionloganalyzer.repository;

import java.util.List;

public interface BaseRepository<T> {

    T save(T entity);

    // false if not found
    boolean delete(int id);

    // null if not found
    T get(int id);

    List<T> getAll();

}
