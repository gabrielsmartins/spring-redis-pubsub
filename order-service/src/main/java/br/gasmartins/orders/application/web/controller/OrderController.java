package br.gasmartins.orders.application.web.controller;

import br.gasmartins.orders.application.web.controller.mapper.OrderControllerMapper;
import br.gasmartins.orders.application.web.controller.dto.OrderDto;
import br.gasmartins.orders.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.UUID;

import static net.logstash.logback.argument.StructuredArguments.kv;

@RestController
@RequestMapping("orders/v1")
@RequiredArgsConstructor
@Validated
@Slf4j
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestHeader HttpHeaders httpHeaders, @RequestBody @Valid OrderDto orderDto){
        log.info("Mapping request: {},{}", kv("headers", httpHeaders), kv("body", orderDto));
        var order = OrderControllerMapper.mapToDomain(orderDto);
        log.info("Order mapped: {}", kv("order", order));

        log.info("Creating order: {}", kv("order", order));
        var createdOrder = this.service.create(order);
        log.info("Order was created successfully: {}", kv("order", createdOrder));

        log.info("Submitting order: {}", kv("order", order));
        var submittedOrder = this.service.submit(createdOrder);
        log.info("Order was submitted successfully: {}", kv("order", submittedOrder));

        log.info("Mapping submitted mapped: {}", kv("order", submittedOrder));
        var submittedOrderDto = OrderControllerMapper.mapToDto(submittedOrder);
        log.info("Order submitted was mapped successfully: {}", kv("order", submittedOrderDto));
        return new ResponseEntity<>(submittedOrderDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@RequestHeader HttpHeaders httpHeaders, @PathVariable("id") UUID id){
        log.info("Mapping request: {},{}", kv("headers", httpHeaders), kv("id", id));

        log.info("Searching order: {}", kv("id", id));
        var order =  this.service.findById(id)
                                 .map(OrderControllerMapper::mapToDto)
                                 .orElseThrow(() -> new NoSuchElementException(String.format("Order %s not found", id)));
        log.info("Order was found successfully: {}", kv("order", order));
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
