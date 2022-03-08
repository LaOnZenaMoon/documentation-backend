package me.lozm.global.config;

import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lozm.code.AuditorCode;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.AuditorAware;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

import static me.lozm.utils.JwtUtils.getJwtObject;
import static me.lozm.utils.JwtUtils.getUserIdFromJwt;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuditorConfig implements AuditorAware<Long> {

    private final Environment environment;


    @Override
    public Optional<Long> getCurrentAuditor() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        Jwt jwt = getJwt((ServletRequestAttributes) requestAttributes);
        if (isEmpty(jwt)) {
            return Optional.of(AuditorCode.SYSTEM.getCode());
        }

        return Optional.ofNullable(getUserIdFromJwt(jwt));
    }

    private Jwt getJwt(ServletRequestAttributes requestAttributes) {
        try {
            String jwt = requestAttributes.getRequest().getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer", "");
            return getJwtObject(jwt, environment.getProperty("jwt-token.secret-key"));
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }

        return null;
    }

}
