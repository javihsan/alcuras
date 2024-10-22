package com.alcuras.datastore.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Respuesta paginada según documentación del banco
 * Created by jpelaez on 12/7/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponsePage<T> extends ResponseOk<T> {

    private Pagination pagination;

    private List<ResponseMessage> messages;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<ResponseMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ResponseMessage> messages) {
        this.messages = messages;
    }
}

