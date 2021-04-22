package neo4j;

import org.neo4j.driver.*;
import static org.neo4j.driver.Values.parameters;


/**
 * @author douzhitong
 * @date 2021/4/19
 */
public class BaseTest implements AutoCloseable {

    private final Driver driver;

    public static void main(String[] args) throws Exception {
        try ( BaseTest greeter = new BaseTest( "bolt://localhost:7687", "neo4j", "password" ) )
        {
            greeter.printGreeting( "hello, world" );
        }
    }

    public BaseTest( String uri, String user, String password )
    {
        driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );
    }

    @Override
    public void close() throws Exception
    {
        driver.close();
    }

    public void printGreeting( final String message )
    {
        try ( Session session = driver.session() )
        {
            String greeting = session.writeTransaction(tx -> {
                Result result = tx.run( "CREATE (a:Greeting) " +
                                "SET a.message = $message " +
                                "RETURN a.message + ', from node ' + id(a)",
                        parameters( "message", message ));
                return result.single().get( 0 ).asString();
            });
            System.out.println( greeting );
        }
    }
}
