package bg.softuni.mobilele.web.interceptors;

import bg.softuni.mobilele.service.StatsService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class StatsInterceptor implements HandlerInterceptor {
    private final StatsService statService;

    public StatsInterceptor(StatsService statService) {
        this.statService = statService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        statService.onRequest();
        return true;
    }
}
