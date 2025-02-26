package br.com.danilo.parallel.common.service;

import br.com.danilo.parallel.common.model.Chair;
import br.com.danilo.parallel.common.model.CommonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ChairService implements CommonService {
    private static final Logger log = LoggerFactory.getLogger(ChairService.class);

    @Override
    public void process(CommonData commonData) {
        log.info("2 - getting chair");
        var chair = new Chair(new Random().nextInt(9000) + 1000);

        commonData.setChair(chair);
    }
}
