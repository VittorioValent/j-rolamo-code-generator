package it.contrader.jrolamo.codegenerator.workshop.service;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Vittorio Valent
 * @since 0.0.1
 *
 */
@Service
@SuppressWarnings("unused")
@Slf4j
public class ApplicationService {

    public void restart() {
        try {
            log.info("Restarting application due to new Entity creation");
            Process process = Runtime.getRuntime().exec("mvn clean install");
        } catch (IOException e) {
            log.error("Impossible to restart application", e);
        }
    }
}
