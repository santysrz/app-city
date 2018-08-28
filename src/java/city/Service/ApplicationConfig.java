/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package city.Service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author sfsuarez
 */
@javax.ws.rs.ApplicationPath("api-rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(city.Service.CategoriasService.class);
        resources.add(city.Service.IncidenciaService.class);
        resources.add(city.Service.MultiService.class);
        resources.add(city.Service.UsuarioService.class);
    }
    
}
