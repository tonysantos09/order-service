package br.com.fiap.orderservice.controller;

import br.com.fiap.orderservice.Order;
import br.com.fiap.orderservice.dao.OrderDAO;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping("order/{id}")
    public ResponseEntity<Order> getHello(@PathVariable(value="id", required = true) int id) {
        return new ResponseEntity(dao.findById(id), HttpStatus.OK);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity update(@PathVariable(value="id", required = true) int id,
                                 @RequestBody Order order){
        return new ResponseEntity(dao.atualizar(id, order), HttpStatus.OK);

    }

    @PostMapping("/order")
    public ResponseEntity salvar(@RequestBody Order order) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dao.salvar(order).getId()).toUri();

        return new ResponseEntity(location, HttpStatus.OK);
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity delete(@PathVariable(value="id", required = true) int id) {
        return new ResponseEntity(dao.deletar(id), HttpStatus.OK);
    }
}
