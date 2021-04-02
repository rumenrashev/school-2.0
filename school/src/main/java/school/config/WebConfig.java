package school.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import school.web.interceptors.ErrorInterceptor;
import school.web.interceptors.TitleInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final TitleInterceptor titleInterceptor;
    private final ErrorInterceptor errorInterceptor;

    public WebConfig(TitleInterceptor titleInterceptor,
                     ErrorInterceptor errorInterceptor) {
        this.titleInterceptor = titleInterceptor;
        this.errorInterceptor = errorInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(titleInterceptor);
        registry.addInterceptor(errorInterceptor).addPathPatterns("/error");

    }
}
