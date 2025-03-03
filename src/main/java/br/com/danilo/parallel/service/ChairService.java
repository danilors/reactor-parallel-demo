package br.com.danilo.parallel.service;

import br.com.danilo.parallel.model.Chair;
import br.com.danilo.parallel.model.CommonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ChairService implements CommonService {
    private static final Logger log = LoggerFactory.getLogger(ChairService.class);

    @Override
    public void process(CommonData commonData) {
        log.info("running in thread: {}", Thread.currentThread().getName());
        var chair = new Chair(new Random().nextInt(9000) + 100);
        commonData.setChair(chair);
        log.info("Chair: {} added", chair);
    }
}
