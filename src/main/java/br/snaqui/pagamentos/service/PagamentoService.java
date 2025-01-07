package br.snaqui.pagamentos.service;

import br.snaqui.pagamentos.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    private PagamentoRepository pagamentoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

}
