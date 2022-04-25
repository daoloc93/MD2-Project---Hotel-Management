package service;

import java.util.List;

public interface IServiceGeneric <T>{
    void edit (int id, T t);
    void delete (int id, T t);
    List<T> findAll();
    void save(T t);
    T findById (int id);
}
