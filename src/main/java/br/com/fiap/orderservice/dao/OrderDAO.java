package br.com.fiap.orderservice.dao;

import br.com.fiap.orderservice.Order;
import br.com.fiap.orderservice.Itens;

import java.math.BigDecimal;

public class OrderDAO {

    private Order[] listaOrder = null;

    public OrderDAO(){
        listaOrder = new Order[1000];
        for(int x = 0; x < 500; x++) {
            Order order = new Order();

            order.setId(x);
            order.setEmail("xxxx@xxxx.com");
            order.setNome("Tony Santos");
            order.setEndereco("Av XXXXX, 12 - SP/SP");
            order.setTotal(new BigDecimal(300));
            order.setFormaPagamento("Débito");
            order.setData("23/03/2019");
            order.setStatus("Pagamento Aprovado");
            order.setIdOrder("1");
            order.setNroCartao("1234 5678 9012 3456");
            order.setValCartao("12/22");
            order.setCartaoBandeira("MasterCard");

            Itens[] item = new Itens[5];

            for (int i = 0; i < item.length; i++) {
                item[i] = new Itens();
                item[i].setDescricao("Item descrição " + i);
                item[i].setQtd(1);
                item[i].setValor(new BigDecimal(20 * (i + 1)));
            }
            order.setItemPedido(item);
            listaOrder[x] = order;
        }
    }

    public Order salvar(Order order) {

        Order orderNovo = new Order();

        orderNovo.setId(order.getId());
        orderNovo.setEmail(order.getEmail());
        orderNovo.setNome(order.getNome());
        orderNovo.setEndereco(order.getEndereco());
        orderNovo.setFormaPagamento(order.getFormaPagamento());
        orderNovo.setData(order.getData());
        orderNovo.setStatus(order.getStatus());
        orderNovo.setIdOrder(order.getIdOrder());
        orderNovo.setNroCartao(order.getNroCartao());
        orderNovo.setValCartao(order.getValCartao());
        orderNovo.setCartaoBandeira(order.getCartaoBandeira());

        Itens[] item = new Itens[5];

        BigDecimal total = new BigDecimal(0.0);

        for(int i = 0; i < item.length; i++){
            item[i] = new Itens();
            item[i].setDescricao(order.getItemPedido()[i].getDescricao());
            item[i].setQtd(order.getItemPedido()[i].getQtd());
            item[i].setValor(order.getItemPedido()[i].getValor());

            total = total.add(order.getItemPedido()[i].getValor());
        }
        orderNovo.setTotal(total);
        orderNovo.setItemPedido(item);

        listaOrder[order.getId()] = orderNovo;
        return orderNovo;
    }

    public Order findById(int id){
        return listaOrder[id];
    }

    public boolean atualizar(int id, Order order)
    {
        if(listaOrder[id] != null) {

            Order obj = listaOrder[id];
            obj.setEmail(order.getEmail());
            obj.setNome(order.getNome());
            obj.setEndereco(order.getEndereco());
            obj.setTotal(order.getTotal());
            obj.setFormaPagamento(order.getFormaPagamento());
            obj.setData(order.getData());
            obj.setStatus(order.getStatus());
            obj.setIdOrder("1");
            obj.setNroCartao(order.getNroCartao());
            obj.setValCartao(order.getValCartao());
            obj.setCartaoBandeira(order.getCartaoBandeira());

            int qtdItens = order.getItemPedido().length;

            Itens[] item = new Itens[qtdItens];

            for (int i = 0; i < (qtdItens); i++) {
                item[i] = new Itens();
                item[i].setDescricao(order.getItemPedido()[i].getDescricao());
                item[i].setQtd(order.getItemPedido()[i].getQtd());
                item[i].setValor(order.getItemPedido()[i].getValor());

            }
            obj.setItemPedido(item);

            return true;
        }

        return false;
    }

    public boolean deletar(int id){
        if(listaOrder[id] != null){
            listaOrder[id] = null;
            return true;
        }

        return false;
    }

    public Order[] getList() {
        return listaOrder;
    }
}
