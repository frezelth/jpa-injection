package eu.europa.ec.agri.workshop.jpasqlinjection.persistence;

import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @RestResource
    public Book findByName(String name) {
        Query query = entityManager.createNativeQuery("SELECT b.* FROM Book as b " +
                "WHERE b.name = " + name, Book.class);

        return (Book)query.getSingleResult();
    }

    @Override
    @RestResource
    public List<Book> findAllOrderByProperty(String property) {
        Query query = entityManager.createNativeQuery("SELECT b.* FROM Book as b " +
                "ORDER BY " + property, Book.class);

        return query.getResultList();
    }
}
