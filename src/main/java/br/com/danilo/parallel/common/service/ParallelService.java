package br.com.danilo.parallel.common.service;

import br.com.danilo.parallel.common.model.CommonData;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
public class ParallelService {

    private final List<CommonService> services;
    private final TaskExecutorService taskExecutorService;

    public ParallelService(List<CommonService> services, TaskExecutorService taskExecutorService) {
        this.services = services;
        this.taskExecutorService = taskExecutorService;
    }

    public Mono<CommonData> processServicesReactive() {
        CommonData initialData = new CommonData();
        return Flux.fromIterable(services)
                .flatMap(commonService -> Mono.fromRunnable(() -> commonService.process(initialData))
                        .subscribeOn(Schedulers.parallel()))
                .then(Mono.just(initialData));

    }

    public CommonData processServicesNormal() {
        return taskExecutorService.processTask(services);
    }
}
