package com.audibene;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import ru.yandex.qatools.embed.postgresql.EmbeddedPostgres;
import ru.yandex.qatools.embed.postgresql.config.PostgresConfig;
import ru.yandex.qatools.embed.postgresql.distribution.Version;
import ru.yandex.qatools.embed.postgresql.util.SocketUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static java.lang.String.format;

@Configuration
@Slf4j
public class TestDBConfig {

    @Bean(destroyMethod = "close")
    @ConfigurationProperties(
            prefix = "spring.datasource.hikari"
    )
    @DependsOn("embeddedPostgres")
    public HikariDataSource dataSource(DataSourceProperties properties, EmbeddedPostgres embeddedPostgres) {
        PostgresConfig config = embeddedPostgres.getConfig().get();

        DataSourceProperties newProperties = new DataSourceProperties();
        BeanUtils.copyProperties(properties, newProperties);
        newProperties.setUsername(config.credentials().username());
        newProperties.setPassword(config.credentials().password());
        newProperties.setUrl(format("jdbc:postgresql://%s:%s/%s",
                config.net().host(),
                config.net().port(),
                config.storage().dbName()
        ));

        return (HikariDataSource) newProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(destroyMethod = "stop")
    public EmbeddedPostgres embeddedPostgres() throws IOException {
        for (int i = 0; i < 10; i++) {
            EmbeddedPostgres postgres = new EmbeddedPostgres(Version.Main.V9_5);
            Path tempPostgresDir = Files.createTempDirectory("postgres-extracted");
            tempPostgresDir.toFile().deleteOnExit();
            postgres.start(EmbeddedPostgres.cachedRuntimeConfig(tempPostgresDir),
                    "localhost", SocketUtil.findFreePort(),
                    "test", "user", "password",
                    Arrays.asList("-E", "UTF-8"));

            if (postgres.getProcess().get().isProcessRunning()) {
                return postgres;
            }
            postgres.stop();
        }
        throw new IllegalStateException("Cannot start Postgres");
    }

}
