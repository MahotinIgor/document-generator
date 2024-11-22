package ru.mahotin.service;

public interface FileContentProducer {
    void produce(String rabbitQueue, String content);
}
