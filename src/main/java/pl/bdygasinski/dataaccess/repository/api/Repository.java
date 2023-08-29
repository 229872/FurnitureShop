package pl.bdygasinski.dataaccess.repository.api;

import jakarta.ejb.Local;
import pl.bdygasinski.dataaccess.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

@Local
public interface Repository <T extends AbstractEntity> {

    T save(T entity);
    void delete(T entity);
    Optional<T> findById(Long id);
    List<T> findAll();
}
