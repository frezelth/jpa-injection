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

    // 1) PUSH data with "common" names: sa, admin... then
    // 2) http://localhost:8080/books/search/findByName?name=current_user()
    @Override
    @RestResource
    public Book findByName(String name) {
        Query query = entityManager.createNativeQuery("SELECT b.* FROM Book as b " +
                "WHERE b.name = " + name, Book.class);

        return (Book)query.getSingleResult();
    }

    //http://localhost:8080/books/search/findBookProperty?propertyName=current_user()&id=1
    @Override
    @RestResource
    public String findBookProperty(String propertyName, Long id){
        Query query = entityManager.createNativeQuery("SELECT "+propertyName+" FROM Book WHERE Book.id ="+id);

        return (String)query.getSingleResult();
    }

    //http://localhost:8080/books/search/findAllOrderByProperty?property=case%20when%20substring(h2version(),1,1)=%271%27%20then%20name%20else%20author%20end
    //look on which property the books are sorted, allows you to find out the version of h2 that the server is using
    @Override
    @RestResource
    public List<Book> findAllOrderByProperty(String property) {
        Query query = entityManager.createNativeQuery("SELECT b.* FROM Book as b " +
                "ORDER BY " + property, Book.class);

        return query.getResultList();
    }
}
