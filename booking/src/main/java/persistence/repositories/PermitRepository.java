
package persistence.repositories;

import core.Permit;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 *
 * @author Youssef Shehab
 */
public interface PermitRepository extends GraphRepository<Permit>{
    
}
