package br.com.snaqui.pagamentos.repository;

import br.com.snaqui.pagamentos.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {



}
