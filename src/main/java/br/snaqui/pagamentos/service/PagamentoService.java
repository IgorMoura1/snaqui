package br.snaqui.pagamentos.service;

import br.snaqui.pagamentos.dto.PagamentoDto;
import br.snaqui.pagamentos.model.Pagamento;
import br.snaqui.pagamentos.model.Status;
import br.snaqui.pagamentos.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public Page<PagamentoDto> getAll(Pageable pagination){
        return pagamentoRepository
                .findAll(pagination)
                .map(pagamento -> modelMapper.map(pagamento, PagamentoDto.class));
    }

    public PagamentoDto getById(Long id){
        Pagamento pagamento = pagamentoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pagamento não encontrado"));

        return modelMapper.map(pagamentoRepository.findById(id).get(), PagamentoDto.class);
    }

    public PagamentoDto createPayment(PagamentoDto dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setStatus(Status.CRIADO);
        pagamentoRepository.save(pagamento);
        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public PagamentoDto updatePayment (Long id, PagamentoDto dto) {
        Pagamento payment = modelMapper.map(dto, Pagamento.class);
        payment.setId(id);
        payment = pagamentoRepository.save(payment);
        return modelMapper.map(payment, PagamentoDto.class);
    }

    public void deletePayment(Long id) {
        pagamentoRepository.deleteById(id);
    }

}