package br.com.danilo.parallel.service;

import br.com.danilo.parallel.model.CommonData;
import br.com.danilo.parallel.model.Pen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PenService implements CommonService {
    private static final Logger log = LoggerFactory.getLogger(PenService.class);

    @Override
    public void process(CommonData commonData) {
        log.info("running in thread: {}", Thread.currentThread().getName());
        var pen = new Pen("blue" + new Random().nextInt(9000) + 1000);
        commonData.setPen(pen);
        log.info("Pen: {} added", pen);
    }
}
