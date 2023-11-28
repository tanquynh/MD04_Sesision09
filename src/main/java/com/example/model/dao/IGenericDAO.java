package com.example.model.dao;

import java.util.List;

public interface IGenericDAO<Entity , Id> {
    List<Entity> findAll();
    boolean save(Entity entity);
    Entity findById(Id id);
    boolean update(Entity entity, Id id);
    boolean delete(Id id);
}
