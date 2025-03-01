package br.com.danilo.parallel.common.service;

import br.com.danilo.parallel.common.model.CommonData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
public class TaskExecutorService {
    private final Executor taskExecutor;

    public TaskExecutorService(@Qualifier("taskExecutor") Executor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public CommonData processTask(List<CommonService> services) {
        CommonData initialData = new CommonData();
        List<CompletableFuture<Void>> futures = new ArrayList<>(services.size());
        services.forEach(service -> {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> service.process(initialData), taskExecutor);
            futures.add(future);
        });
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        return initialData;
    }

}
