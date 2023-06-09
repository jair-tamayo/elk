package io.kt.elk.rest.controller;

import io.kt.elk.service.SampleService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static io.kt.elk.rest.constant.ApplicationHeaders.*;

@RestController
@RequestMapping("/sample")
final class SampleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleController.class);

    private final SampleService sampleService;

    SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping("/{product}")
    @Parameters({
            @Parameter(name = CORRELATION_HEADER, in = ParameterIn.HEADER, schema = @Schema(type = "string"))
    })
    Map<String, Object> echo(HttpServletResponse response, @PathVariable String product, @RequestParam Double amount) {
        response.setHeader(AMOUNT_HEADER, String.valueOf(amount));
        response.setHeader(PRODUCT_HEADER, product);
        LOGGER.info("Sample - product {} - amount {}", product, amount);
        return sampleService.echo(product, amount);
    }

    @PostMapping("/{product}/request")
    @Parameters({
            @Parameter(name = CORRELATION_HEADER, in = ParameterIn.HEADER, schema = @Schema(type = "string"))
    })
    void request(HttpServletResponse response, @PathVariable String product, @RequestBody Map<String, String> body) {
        MDC.put(PRODUCT_HEADER, product);
        response.setHeader(PRODUCT_HEADER, product);
        LOGGER.info("Instructions were sent to complete your application - product {}", product);
    }

}
