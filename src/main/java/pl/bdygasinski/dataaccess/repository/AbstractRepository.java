package pl.bdygasinski.dataaccess.repository;

import jakarta.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pl.bdygasinski.dataaccess.entity.AbstractEntity;
import pl.bdygasinski.dataaccess.repository.api.Repository;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public abstract class AbstractRepository <T extends AbstractEntity> implements Repository<T> {

    private Class<T> clazz;

    protected abstract EntityManager getEntityManager();

    @Override
    public T save(T entity) {
        EntityManager em = getEntityManager();

        if (entity.getId() == null) {
            em.persist(entity);
            return entity;
        }

        return em.merge(entity);
    }

    @Override
    public void delete(T entity) {
        EntityManager em = getEntityManager();
        em.remove(em.merge(entity));
    }

    @Override
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(getEntityManager().find(clazz, id));
    }

    @Override
    public List<T> findAll() {
        String tableName = clazz.getName();;

        return getEntityManager()
                .createQuery("SELECT entity FROM %s entity".formatted(tableName), clazz)
                .getResultList();
    }
}
