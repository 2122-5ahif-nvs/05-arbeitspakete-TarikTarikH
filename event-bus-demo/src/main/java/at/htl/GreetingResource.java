package at.htl;

import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.context.ManagedExecutor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GreetingResource {
    @Inject
    ManagedExecutor executor;


    @ConsumeEvent("greeting")
    public String consume(String name){
        return name.toUpperCase();
    }

    @ConsumeEvent("greeting2")
    public Uni<String> process(String name) {
        return Uni.createFrom().item(name::toUpperCase).emitOn(executor);
    }
}