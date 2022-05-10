package br.gasmartins.pubsub.application.web.controller;

import br.gasmartins.pubsub.application.web.controller.dto.OrderDto;
import br.gasmartins.pubsub.application.web.controller.mapper.OrderControllerMapper;
import br.gasmartins.pubsub.domain.enums.Status;
import br.gasmartins.pubsub.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static net.logstash.logback.argument.StructuredArguments.kv;

@RestController
@RequestMapping("orders/v1")
@RequiredArgsConstructor
@Validated
@Slf4j
public class OrderController {

    private final OrderControllerMapper mapper;
    private final OrderService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestHeader HttpHeaders httpHeaders, @RequestBody @Valid OrderDto orderDto){
        log.info("Mapping request: {},{}", kv("headers", httpHeaders), kv("body", orderDto));
        var order = this.mapper.mapToDomain(orderDto);
        log.info("Order mapped: {}", kv("order", order));
        order.setStatus(Status.CREATED);

        log.info("Creating order: {}", kv("order", order));
        var createdOrder = this.service.create(order);
        log.info("Order was created successfully: {}", kv("order", createdOrder));

        log.info("Submitting order: {}", kv("order", order));
        var submittedOrder = this.service.submit(createdOrder);
        log.info("Order was submitted successfully: {}", kv("order", submittedOrder));

        log.info("Mapping submitted mapped: {}", kv("order", submittedOrder));
        var submittedOrderDto = this.mapper.mapToDto(submittedOrder);
        log.info("Order submitted was mapped successfully: {}", kv("order", submittedOrderDto));
        return new ResponseEntity<>(submittedOrderDto, HttpStatus.ACCEPTED);
    }

}
