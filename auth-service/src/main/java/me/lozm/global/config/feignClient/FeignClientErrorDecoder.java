package me.lozm.global.config.feignClient;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class FeignClientErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 400:
            case 404:
                break;
            default:
                return new IllegalStateException(response.reason());
        }

        return null;
    }

}
