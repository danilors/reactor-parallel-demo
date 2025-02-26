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

    public ParallelService(List<CommonService> services) {
        this.services = services;
    }

    public Mono<CommonData> processServicesReactive() {
        CommonData initialData = new CommonData();
        return Flux.fromIterable(services)
                .flatMap(commonService -> Mono.fromRunnable(() -> commonService.process(initialData))
                        .subscribeOn(Schedulers.parallel()))
                .then(Mono.just(initialData));

    }

    public  CommonData processServicesNormal() {
        CommonData initialData = new CommonData();
        services.parallelStream().forEach(service ->  service.process(initialData));
        return initialData;
    }
}
