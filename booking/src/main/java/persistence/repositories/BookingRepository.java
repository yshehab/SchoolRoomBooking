
package persistence.repositories;

import core.Booking;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 *
 * @author Youssef Shehab
 */
public interface BookingRepository extends GraphRepository<Booking>{
    
}
