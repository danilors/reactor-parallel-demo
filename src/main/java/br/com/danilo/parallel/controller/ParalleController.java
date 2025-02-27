package br.com.danilo.parallel.controller;

import br.com.danilo.parallel.common.model.CommonData;
import br.com.danilo.parallel.common.service.ParallelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/parallel")
public class ParalleController {

    private static final Logger log = LoggerFactory.getLogger(ParalleController.class);

    private final ParallelService parallelService;

    public ParalleController(ParallelService parallelService) {
        this.parallelService = parallelService;
    }

    @GetMapping("/reactive")
    public ResponseEntity<Mono<CommonData>> getAllDataReactive() {
        log.info("/reactive -> Request to get all data");
        return new ResponseEntity<>(parallelService.processServicesReactive(), HttpStatus.OK);
    }

    @GetMapping("/normal")
    public ResponseEntity<CommonData> getAllDataNormal() {
        log.info("/normal -> Request to get all data");
        CommonData result = parallelService.processServicesNormal();
        log.info("/normal -> Request processed successfully: {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
