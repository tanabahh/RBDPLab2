package itmo.rbdplab2;


import org.hibernate.event.spi.PostCommitUpdateEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

@Component
public class Stremer implements PostCommitUpdateEventListener {

    @Override
    public boolean requiresPostCommitHanding(EntityPersister var1) {
        return true;
    }

    @Override
    public void onPostUpdate(PostUpdateEvent event) {
        System.out.println("Hello there");
    }

    @Override
    public void onPostUpdateCommitFailed(PostUpdateEvent postInsertEvent) {
        System.out.println("fail");
    }
}

