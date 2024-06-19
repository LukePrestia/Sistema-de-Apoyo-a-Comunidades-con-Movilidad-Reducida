package main;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import javax.persistence.EntityTransaction;

public class MainExample implements WithSimplePersistenceUnit {

    public static void main(String[] args) {
        new MainExample().start();
    }

    private void start() {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        tx.commit();
    }
}