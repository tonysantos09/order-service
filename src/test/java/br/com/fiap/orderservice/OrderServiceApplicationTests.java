package br.com.fiap.orderservice;

import br.com.fiap.orderservice.controller.orderserviceController;
import br.com.fiap.orderservice.dao.OrderDAO;
import br.com.fiap.orderservice.Order;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.*;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.containsString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(orderserviceController.class)
public class OrderServiceApplicationTests {

	@Autowired
	private MockMvc mvc;
	@MockBean
	private OrderDAO repository;

	@Test
	public void AcharOrder() throws Exception {
		int id = 1;
		Order order = new Order();
		order.setId(1);
		when(this.repository.findById(id)).thenReturn(order);
		mvc.perform(get("/order/" + id)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(response -> {
					String json = response.getResponse().getContentAsString();
					Order acharOrder = new ObjectMapper().readValue(json, Order.class);
					assertThat(order.getId()).isEqualToComparingFieldByField(acharOrder.getId());
				});
	}

	@Test
	public void NaoPossuiOrder() throws Exception {
		int id = 0;
		Order order = new Order();
		order.setId(1);
		when(this.repository.findById(id)).thenReturn(order);
		mvc.perform(get("/order/" + id)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isInternalServerError());
	}

	@Test
	public void criarOrder() throws Exception {
		Order order = new Order();

		order.setId(501);
		order.setEmail("xxxx@xxxx.com");
		order.setNome("Tony Santos");
		order.setEndereco("Av XXXXX, 12 - SP/SP");
		order.setFormaPagamento("Débito");
		order.setData("23/03/2019");
		order.setStatus("Pagamento Aprovado");
		order.setIdOrder("1");
		order.setNroCartao("1234 5678 9012 3456");
		order.setValCartao("12/22");
		order.setCartaoBandeira("MasterCard");

		Itens[] item = new Itens[5];

		BigDecimal total = new BigDecimal(0.0);

		for (int i = 0; i < item.length; i++) {
			item[i] = new Itens();
			item[i].setDescricao("Item descrição " + i);
			item[i].setQtd(1);
			item[i].setValor(new BigDecimal(20 * (i + 1)));

			total = total.add(item[i].getValor());
		}
		order.setTotal(total);
		order.setItemPedido(item);

		when(this.repository.salvar(order)).thenReturn(order);
		mvc.perform(
				post("/order")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(order)))
				.andExpect(status().isOk())
				.andExpect(content().string("http://localhost/order/"+ order.getId()));;
	}

	@Test
	public void falhacriarOrder() throws Exception {
		Order order = new Order();

		order.setId(501);
		order.setEmail("xxxx@xxxx.com");
		order.setNome("Tony Santos");
		order.setEndereco("Av XXXXX, 12 - SP/SP");
		order.setFormaPagamento("Débito");
		order.setData("23/03/2019");
		order.setStatus("Pagamento Aprovado");
		order.setIdOrder("1");
		order.setNroCartao("1234 5678 9012 3456");
		order.setValCartao("12/22");
		order.setCartaoBandeira("MasterCard");

		Itens[] item = new Itens[5];

		BigDecimal total = new BigDecimal(0.0);

		for (int i = 0; i < item.length; i++) {
			item[i] = new Itens();
			item[i].setDescricao("Item descrição " + i);
			item[i].setQtd(1);
			item[i].setValor(new BigDecimal(20 * (i + 1)));

			total = total.add(item[i].getValor());
		}
		order.setTotal(total);
		order.setItemPedido(item);

		when(repository.findById(order.getId())).thenReturn(order);

		mvc.perform(
				post("/order")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(order)))
				.andExpect(status().isConflict());
	}

	@Test
	public void atualizarOrder() throws Exception {
		Order order = new Order();

		order.setId(1);
		order.setEmail("xxxx@xxxx.com");
		order.setNome("Tony Santos");
		order.setEndereco("Av XXXXX, 12 - SP/SP");
		order.setFormaPagamento("Débito");
		order.setData("23/03/2019");
		order.setStatus("Pagamento Aprovado");
		order.setIdOrder("1");
		order.setNroCartao("1234 5678 9012 3456");
		order.setValCartao("12/22");
		order.setCartaoBandeira("MasterCard");

		Itens[] item = new Itens[5];

		BigDecimal total = new BigDecimal(0.0);

		for (int i = 0; i < item.length; i++) {
			item[i] = new Itens();
			item[i].setDescricao("Item descrição " + i);
			item[i].setQtd(1);
			item[i].setValor(new BigDecimal(20 * (i + 1)));

			total = total.add(item[i].getValor());
		}
		order.setTotal(total);
		order.setItemPedido(item);

		mvc.perform(
				put("/order/{id}", order.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(order)))
				.andExpect(status().isOk());
	}

	@Test
	public void falhaatualizarOrder() throws Exception {
		Order order = new Order();

		order.setId(600);
		order.setEmail("xxxx@xxxx.com");
		order.setNome("Tony Santos");
		order.setEndereco("Av XXXXX, 12 - SP/SP");
		order.setFormaPagamento("Débito");
		order.setData("23/03/2019");
		order.setStatus("Pagamento Aprovado");
		order.setIdOrder("1");
		order.setNroCartao("1234 5678 9012 3456");
		order.setValCartao("12/22");
		order.setCartaoBandeira("MasterCard");

		Itens[] item = new Itens[5];

		BigDecimal total = new BigDecimal(0.0);

		for (int i = 0; i < item.length; i++) {
			item[i] = new Itens();
			item[i].setDescricao("Item descrição " + i);
			item[i].setQtd(1);
			item[i].setValor(new BigDecimal(20 * (i + 1)));

			total = total.add(item[i].getValor());
		}
		order.setTotal(total);
		order.setItemPedido(item);

		when(repository.findById(order.getId())).thenReturn(null);

		mvc.perform(
				put("/order/{id}", order.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(order)))
				.andExpect(status().isNotFound());
	}

	@Test
	public void deletarOrder() throws Exception {
		Order order = new Order();
		order.setId(200);

		mvc.perform(
				delete("/order/{id}", order.getId()))
				.andExpect(status().isOk());
	}

	@Test
	public void falhadeletarOrder() throws Exception {
		Order order = new Order();
		order.setId(601);

		when(repository.findById(order.getId())).thenReturn(null);

		mvc.perform(
				delete("/order/{id}", order.getId()))
				.andExpect(status().isNotFound());
	}
}
