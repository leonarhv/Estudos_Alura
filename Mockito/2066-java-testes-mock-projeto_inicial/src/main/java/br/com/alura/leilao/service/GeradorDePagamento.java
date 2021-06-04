package br.com.alura.leilao.service;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.leilao.dao.PagamentoDao;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Pagamento;

@Service
public class GeradorDePagamento {

	private PagamentoDao pagamentos;
	private Clock clock;

	@Autowired
	public GeradorDePagamento(PagamentoDao pagamentoDao, Clock clock) {
		this.pagamentos = pagamentoDao;
		this.clock = clock;
	}

	public void gerarPagamento(Lance lanceVencedor) {
		LocalDate vencimento = LocalDate.now(clock).plusDays(1);
		Pagamento pagamento = new Pagamento(lanceVencedor, proximoDiaUtil(vencimento));
		this.pagamentos.salvar(pagamento);
	}

	private LocalDate proximoDiaUtil(LocalDate database) {
		DayOfWeek diaDaSemana = database.getDayOfWeek();
		if (diaDaSemana == DayOfWeek.SATURDAY) {
			return database.plusDays(2);
		} else if (diaDaSemana == DayOfWeek.SUNDAY) {
			return database.plusDays(1);
		}
		return database;
	}

}
