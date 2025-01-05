package ru.repository;

import java.util.List;

interface CrudRepository<K> {

    K save(K k);

    K update(K k);

    List<K> getAll();

    K getOne(K k);

    K getById(String id);
}
