package iti.jets.configuration;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;

public class EntityManagerFactoryProvider {
    @Getter
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("hr");
}
