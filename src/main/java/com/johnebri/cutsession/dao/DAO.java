package com.johnebri.cutsession.dao;

import java.util.List;
import java.util.Optional;

/**
 * @author John on 12/1/22
 */
public interface DAO<T> {

    List<T> list();

    void create(T t);

    Optional<T> get(int id);

    void update (T t, int id);

    void delete(int id);

}
