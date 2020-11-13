package io.kt.elk.rest.filter;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import static io.kt.elk.rest.constant.ApplicationHeaders.CORRELATION_HEADER;

@Component
final class CorrelationFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String correlation = Optional.ofNullable(((HttpServletRequest) request).getHeader(CORRELATION_HEADER))
                    .orElse(UUID.randomUUID().toString());

            ((HttpServletResponse) response).addHeader(CORRELATION_HEADER, correlation);
            MDC.put(CORRELATION_HEADER, correlation);

            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

}
