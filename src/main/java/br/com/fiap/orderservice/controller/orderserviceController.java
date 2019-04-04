package br.com.fiap.orderservice.controller;

import br.com.fiap.orderservice.Exception.ServerException;
import br.com.fiap.orderservice.Order;
import br.com.fiap.orderservice.Exception.OrderNotFoundException;
import br.com.fiap.orderservice.Exception.OrderNotUpdatedException;
import br.com.fiap.orderservice.dao.OrderDAO;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Server;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
public class orderserviceController {

    private Order  listaOrder[];
    OrderDAO dao = null;

    public orderserviceController() {
        dao = new OrderDAO();
        listaOrder = dao.getList();
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getHello(@PathVariable(value="id", required = true) int id) {
        try {
            if (id == 0) {
                throw new OrderNotFoundException("ID não é válido.");
            }
            return new ResponseEntity(dao.findById(id), HttpStatus.OK);
        }
        catch(Exception ex)
        {
            throw new ServerException("Erro: " + ex);
        }
    }

    @PutMapping("/order/{id}")
    public ResponseEntity update(@PathVariable(value="id", required = true) int id,
                                 @RequestBody Order order) {
        try{
            if(id == 0){
                throw new OrderNotUpdatedException("Atualização inválida");
            }

            boolean status = dao.atualizar(id, order);

            if(status)
                return new ResponseEntity(status, HttpStatus.OK);
            else
                return new ResponseEntity(status, HttpStatus.NOT_FOUND);
        }
        catch(Exception ex)
        {
            throw new ServerException("Erro: " + ex);
        }

    }

    @PostMapping("/order")
    public ResponseEntity salvar(@RequestBody Order order) {
        try {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(dao.salvar(order).getId()).toUri();

            return new ResponseEntity(location.toString(), HttpStatus.OK);
        }
        catch (Exception ex)
        {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity delete(@PathVariable(value="id", required = true) int id) {
        boolean status = dao.deletar(id);
        if(status)
            return new ResponseEntity(status, HttpStatus.OK);
        else
            return new ResponseEntity(status, HttpStatus.NOT_FOUND);
    }
}
