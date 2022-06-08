package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {

    interface TestCase {
        void run(EntityManager entityManager);
    }

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager entityManager = factory.createEntityManager();

        TestCase[] testCases = new TestCase[] {
                new BasicCRUD(),
                new BasicRelationships()
        };

        for (TestCase testCase : testCases) {
            testCase.run(entityManager);
        }
    }
}

