
package persistence.repositories;

import core.Organization;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 *
 * @author Youssef Shehab
 */
public interface OrganizationRepository extends GraphRepository<Organization> {
    
}
