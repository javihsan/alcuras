package com.alcuras.datastore.data;

/**
 * Wrapper for an Objectify transaction
 * 
 * @author Alejandro Aranda
 * 
 * @param <O>
 */
public interface ITransaction<O> {

    O execute(ObjectifyService service);

}
