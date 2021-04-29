package study.resolver.handlerresolvertest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class PersonHandlerResolver implements HandlerMethodArgumentResolver {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("supportsParameter");

        return parameter.hasParameterAnnotation(PersonInfoCheck.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        log.info("resolveArgument");
        PersonInfo person = getParameter(webRequest);

        return person;
    }

    private PersonInfo getParameter(NativeWebRequest webRequest) throws IOException {

        HttpServletRequest nativeRequest = (HttpServletRequest) webRequest.getNativeRequest();
        ServletInputStream inputStream = nativeRequest.getInputStream();
        String s = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        return objectMapper.readValue(s, PersonInfo.class);
    }
}
