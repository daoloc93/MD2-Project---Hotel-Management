package service;

import java.util.List;

public interface IServiceGeneric <T>{
    List<T> findAll();
    void save(T t);
//    T findByName(F f);
}
