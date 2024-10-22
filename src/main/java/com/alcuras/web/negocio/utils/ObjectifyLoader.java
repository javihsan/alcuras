package com.alcuras.web.negocio.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.alcuras.web.persist.entities.Articulo;
import com.alcuras.web.persist.entities.Evento;
import com.alcuras.web.persist.entities.Mensaje;
import com.alcuras.web.persist.entities.Web;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.impl.translate.opt.joda.JodaTimeTranslators;


/**
 * Class to load bean to persistent into DataStore.
 *
 * @author emaria.ruiz
 * @version 1.0
 *
 */
public class ObjectifyLoader implements ServletContextListener {

    static {
        JodaTimeTranslators.add(ObjectifyService.factory()); 

        ObjectifyService.register(Articulo.class);
        ObjectifyService.register(Evento.class);
        ObjectifyService.register(Mensaje.class);
        ObjectifyService.register(Web.class);     

    }

    @Override
    public void contextInitialized(final ServletContextEvent sce) {
    }

    @Override
    public void contextDestroyed(final ServletContextEvent sce) {
    }
}
