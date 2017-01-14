package persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import persistence.repositories.MemberRepository;
/**
 * 
 * @author Youssef Shehab
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DbServiceTest {

    @Configuration
    @EnableSpringConfigured
    @ComponentScan(basePackages = {"core", "utilities", "persistence"})
    @EnableNeo4jRepositories(basePackages = {"persistence"})
    static class DbConfigTest extends Neo4jConfiguration {

        private static final String storeDirectory = "data/graphstore.test";

        @Bean(destroyMethod = "shutdown")
        @Scope(value = "singleton")
        public GraphDatabaseService graphDatabaseService() {
            return new GraphDatabaseFactory().newEmbeddedDatabase(storeDirectory);
        }

    }

    @Autowired
    GraphDatabaseService graphDatabaseService;

    @Autowired
    Neo4jTemplate neo4jTemplate;

    @Autowired
    MemberRepository memberRepository;

    /**
     * Tests the OGM bean-factory engine.
     */
    @Test
    public void mainObjectsTest() {
        assert (neo4jTemplate != null) : "template should not be null.";
        assert (graphDatabaseService != null) : "graphDbService should not be null.";
        assert (memberRepository != null) : "memberRepository should not be null.";
        assertEquals("GraphDatabaseService should be the same", neo4jTemplate.getGraphDatabaseService(), graphDatabaseService);
        System.out.println(memberRepository.toString());
    }

}
