package it.contrader.jrolamo.codegenerator.workshop.controller;

import it.contrader.jrolamo.codegenerator.workshop.JRolamoCodeGenerator;
import it.contrader.jrolamo.codegenerator.workshop.service.ApplicationService;
import it.contrader.jrolamo.codegenerator.workshop.service.WorkshopService;
import it.contrader.jrolamo.codegenerator.workshop.utils.EntityInfo;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vittorio Valent
 * @since 1.0
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/workshop")
@ConditionalOnProperty(prefix = "jrolamo.codegenerator", name = "endpoint-enabled", havingValue = "true")
public class WorkshopController {

    @Autowired
    ApplicationService applicationService;

    @Autowired
    WorkshopService workshopService;

    @PostMapping("/entityflow")
    public void generateEntityFlow(@RequestBody EntityInfo entityInfo) {
        JRolamoCodeGenerator.generateEntityFlow(entityInfo);
        applicationService.restart();
    }

    @GetMapping("/entities")
    public List<EntityInfo> getAllEntities() {
        try {
            return workshopService.getAllEntities();
        } catch (ClassNotFoundException e) {
            log.error("ClassNotFoundException", e);
            return null;
        }
    }

}
