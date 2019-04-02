package br.com.fiap.orderservice;

import static br.com.fiap.orderservice.Exception.Exceptions.*;

public class OrderNotUpdatedException extends Exception{
    public OrderNotUpdatedException(Class clazz, String... searchParamsMap) {
        super(notUpdated(clazz.getSimpleName(),
                toMap(String.class, String.class, searchParamsMap)));
    }
}
