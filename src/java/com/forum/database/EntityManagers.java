package com.forum.database;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagers {

    public static EntityManagerFactory factory;

    public static EntityManagerFactory getFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory("ForumFrameworkPU");
        }
        return factory;
    }

}
