package br.com.snaqui.pagamentos.service;

import br.com.snaqui.pagamentos.dto.PagamentoDto;
import br.com.snaqui.pagamentos.model.Pagamento;
import br.com.snaqui.pagamentos.model.Status;
import br.com.snaqui.pagamentos.repository.PagamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<PagamentoDto> getAll(Pageable pageable) {
        return pagamentoRepository.findAll(pageable)
                .map(pagamento -> modelMapper.map(pagamento, PagamentoDto.class));
    }

    public PagamentoDto getById(Long id) {
        Pagamento payment = pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        return modelMapper.map(payment, PagamentoDto.class);
    }

    public PagamentoDto createPayment(PagamentoDto dtoPayment) {
        Pagamento payment = modelMapper.map(dtoPayment, Pagamento.class);
        payment.setStatus(Status.CRIADO);
        pagamentoRepository.save(payment);

        return modelMapper.map(payment, PagamentoDto.class);
    }

    public PagamentoDto updatePayment(Long id, PagamentoDto dtoPayment) {
        Pagamento payment = modelMapper.map(dtoPayment, Pagamento.class);
        payment.setId(id);
        pagamentoRepository.save(payment);

        return modelMapper.map(payment, PagamentoDto.class);
    }

    public void deletePayment(Long id) {
        pagamentoRepository.deleteById(id);
    }
}
