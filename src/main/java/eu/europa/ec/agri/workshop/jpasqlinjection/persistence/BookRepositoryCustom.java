package eu.europa.ec.agri.workshop.jpasqlinjection.persistence;

import java.util.List;

public interface BookRepositoryCustom {

    List<Book> findAllOrderByProperty(String property);

    Book findByName(String name);

}
