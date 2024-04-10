package iti.jets.services;

import iti.jets.dtos.BaseDto;

import java.util.List;

public interface BaseService<T> {
    boolean create(T dto);

    T findById(int id);

    List<T> findAll();

    boolean update(T dto, int id);

    boolean delete(int id);

    boolean deleteAll();
}
