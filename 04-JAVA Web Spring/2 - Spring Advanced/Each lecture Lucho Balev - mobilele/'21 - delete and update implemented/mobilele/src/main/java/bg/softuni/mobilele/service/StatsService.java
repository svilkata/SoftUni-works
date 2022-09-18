package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.view.StatsView;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class StatsService {
    private int anonymousRequests, authRequests;
    private StatsView statsView;

    public void onRequest(){
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication != null && (authentication.getPrincipal() instanceof UserDetails)) {
            authRequests++;
        } else {
            anonymousRequests++;
        }
    }

    public StatsView getStats(){
        return new StatsView(authRequests, anonymousRequests);
    }
}
