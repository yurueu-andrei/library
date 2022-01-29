package by.library.yurueu.service;

import org.flywaydb.core.Flyway;

import static by.library.yurueu.service.Property.*;

public class FlywayService {
    private Flyway flyway;

    public FlywayService() {
        init();
    }

    public void migrate() {
        flyway.migrate();
    }

    public void clean() {
        flyway.clean();
    }

    public void init() {
        flyway = Flyway.configure().dataSource(H2_URL, H2_USER, H2_PASSWORD).locations(MIGRATION_LOCATION).load();
    }
}