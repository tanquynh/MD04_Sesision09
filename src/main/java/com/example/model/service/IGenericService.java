package com.example.model.service;

import java.util.List;

public interface IGenericService <Entity , Id>{
    List<Entity> findAll();
    boolean save(Entity entity);
    Entity findById(Id id);
    boolean update(Entity entity, Id id);
    boolean delete(Id id);
}
