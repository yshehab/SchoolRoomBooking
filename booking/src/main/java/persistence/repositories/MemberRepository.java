package persistence.repositories;

import core.Member;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 *
 * @author Youssef Shehab
 */
public interface MemberRepository extends GraphRepository<Member> {

}
