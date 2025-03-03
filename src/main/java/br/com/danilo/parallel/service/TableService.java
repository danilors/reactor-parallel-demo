package br.com.danilo.parallel.service;

import br.com.danilo.parallel.model.CommonData;
import br.com.danilo.parallel.model.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TableService implements CommonService {
    private static final Logger log = LoggerFactory.getLogger(TableService.class);

    @Override
    public void process(CommonData commonData) {
        log.info("running in thread: {}", Thread.currentThread().getName());
        var table = new Table(new Random().nextInt(9000) + 1000);
        commonData.setTable(table);
        log.info("Table: {} added", table);
    }
}
