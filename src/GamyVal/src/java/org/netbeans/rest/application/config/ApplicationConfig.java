package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("api/v1")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ch.heigvd.amt.gamyval.restapi.ressources.AccountResource.class);
        resources.add(ch.heigvd.amt.gamyval.restapi.ressources.ApplicationResource.class);
        resources.add(ch.heigvd.amt.gamyval.restapi.ressources.EndUserResource.class);
        resources.add(ch.heigvd.amt.gamyval.restapi.ressources.EventResource.class);
        resources.add(ch.heigvd.amt.gamyval.restapi.ressources.RankRessource.class);
        resources.add(ch.heigvd.amt.gamyval.restapi.ressources.TaskResource.class);
        resources.add(org.netbeans.rest.application.config.MyObjectMapperProvider.class);
    }

}
