package io.kt.elk.rest.controller;

import io.kt.elk.service.SampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static io.kt.elk.rest.constant.ApplicationHeaders.AMOUNT_HEADER;
import static io.kt.elk.rest.constant.ApplicationHeaders.PRODUCT_HEADER;

@RestController
@RequestMapping("/sample")
final class SampleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleController.class);

    private final SampleService sampleService;

    SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping("/{product}")
    Map<String, Object> echo(HttpServletResponse response, @PathVariable String product, @RequestParam Double amount) {
        response.setHeader(AMOUNT_HEADER, String.valueOf(amount));
        response.setHeader(PRODUCT_HEADER, product);
        LOGGER.info("Sample - product {} - amount {}", product, amount);
        return sampleService.echo(product, amount);
    }

    @GetMapping("/{product}/request")
    void request(HttpServletResponse response, @PathVariable String product) {
        MDC.put(PRODUCT_HEADER, product);
        response.setHeader(PRODUCT_HEADER, product);
        LOGGER.info("Instructions were sent to complete your application - product {}", product);
    }

}
