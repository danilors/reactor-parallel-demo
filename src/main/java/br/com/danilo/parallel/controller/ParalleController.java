package br.com.danilo.parallel.controller;

import br.com.danilo.parallel.common.model.CommonData;
import br.com.danilo.parallel.common.service.ParallelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/cars")
public class ParalleController {

    private final ParallelService parallelService;

    public ParalleController(ParallelService parallelService) {
        this.parallelService = parallelService;
    }

    @GetMapping
    public Mono<CommonData> getAll() {
        return parallelService.processServices();
    }
}
