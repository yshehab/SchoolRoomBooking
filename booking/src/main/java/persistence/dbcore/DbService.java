package persistence.dbcore;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.support.Neo4jTemplate;

/**
 * A class to provide the main services of the graphDb by loading the
 * configurations and exposing access to the Beans provided by
 * spring-data-neo4j's beans factory.
 *
 * @author Youssef Shehab
 */
public class DbService {

    
    
    /**
     * A class for setting up spring-data-neo4j configurations for OGM.
     */
    @Configuration
    @EnableSpringConfigured
    @ComponentScan(basePackages = {"core", "utilities", "persistence", 
        "persistence.dbcore", "persistence.repositories"})
    @EnableNeo4jRepositories(basePackages = "persistence.repositories")
    static class DbConfig extends Neo4jConfiguration {

        private static final String storeDirectory = "data/graphstore";

        /**
         * Provides an implementation of GraphDatabaseService as a Bean
         * definition which is required by Neo4jConfiguration.
         *
         * @return GraphDatabaseService Bean.
         */
        @Bean(destroyMethod = "shutdown")
        @Scope(value = "singleton")
        GraphDatabaseService graphDatabaseService() {
            GraphDatabaseService graphDb
                    = new GraphDatabaseFactory().newEmbeddedDatabase(storeDirectory);
            registerShutdownHook(graphDb);
            return graphDb;
        }

        /**
         * Registers a shutdown hook for the Neo4j instance so that it shuts
         * down nicely when the VM exits even if the application is interrupted.
         */
        private static void registerShutdownHook(final GraphDatabaseService aGraphDb) {
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    aGraphDb.shutdown();
                }
            });
        }
    }

    /**
     * Provides Bean-factory methods for accessing application components.
     */
    private static final ApplicationContext ctx
            = new AnnotationConfigApplicationContext(DbConfig.class);

    /**
     * Exposes access to the singleton graphDb instance.
     *
     * @return the graphDb object - instance of {@link GraphDatabaseService}
     */
    protected static GraphDatabaseService getGraphDb() {
        return ctx.getBean(GraphDatabaseService.class);
    }

    /**
     * Exposes access to the mediator object for the graph related services.
     *
     * @return a mediator object - instance of {@link Neo4jTemplate}
     */
    protected static Neo4jTemplate getNeo4jTemplate() {
        return ctx.getBean(Neo4jTemplate.class);

    }

    /**
     * Loads a bean for a repository implementation in a generic fashion.
     *
     * @param clazz Repository class.
     * @return A repository implementation for T -an instance of
     * {@link org.springframework.data.neo4j.repository.NodeGraphRepositoryImpl}&lt;T&gt;
     */
    protected static <T> T getRepository(Class<T> clazz) {
        return ctx.getBean(clazz);
    }
    
}
