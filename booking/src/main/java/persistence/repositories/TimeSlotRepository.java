
package persistence.repositories;

import dateTime.TimeSlot;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 *
 * @author Youssef Shehab
 */
public interface TimeSlotRepository extends GraphRepository<TimeSlot> {
    
}
