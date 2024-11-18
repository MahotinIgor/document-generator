package ru.mahotin.service.impl;

import lombok.extern.log4j.Log4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.mahotin.service.FileContentProducer;

@Service
@Log4j
public class FileContentProducerImpl implements FileContentProducer {

    private final RabbitTemplate rabbitTemplate;

    public FileContentProducerImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void produce(String rabbitQueue, String content) {
        log.debug(content + " sending to RabbitMQ");
        rabbitTemplate.convertAndSend("FILE_CONTENT", content);
    }
}
