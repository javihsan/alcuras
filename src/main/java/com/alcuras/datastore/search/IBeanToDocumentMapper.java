package com.alcuras.datastore.search;

import com.alcuras.datastore.data.IStorable;
import com.google.appengine.api.search.Document;

/**
 * Genera un documento indexable a partir de un bean
 * 
 * @author abcarracedo
 * 
 */
public interface IBeanToDocumentMapper<T extends IStorable<?>> {

    /**
     * @param bean
     *            a indexar
     * @return documento para indexar
     */
    Document create(T bean);
}
