package br.com.fiap.orderservice;

import static br.com.fiap.orderservice.Exception.Exceptions.*;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(Class clazz, String... searchParamsMap) {
        super(notFound(clazz.getSimpleName(),
                toMap(String.class, String.class, searchParamsMap)));
    }
}
