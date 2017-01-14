
package persistence.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import utilities.Address;

/**
 *
 * @author Youssef Shehab
 */
    public interface AddressRepository extends GraphRepository<Address> {

    }