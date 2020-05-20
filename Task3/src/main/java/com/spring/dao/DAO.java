
package com.spring.dao;
import java.util.ArrayList;

public abstract class DAO<T> {
    public abstract void insertBatch(ArrayList<T> list);
    public abstract void insertPreparedStatement(T el);
    public abstract void insertStatement(T el);
    public abstract T getById(int id);
    public abstract void update(T el);
    public abstract void delete(int id);
}