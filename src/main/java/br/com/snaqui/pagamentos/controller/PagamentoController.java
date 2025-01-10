package br.com.snaqui.pagamentos.controller;

import br.com.snaqui.pagamentos.dto.PagamentoDto;
import br.com.snaqui.pagamentos.model.Pagamento;
import br.com.snaqui.pagamentos.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping
    public Page<PagamentoDto> listAll(@PageableDefault(size = 10)Pageable pageable) {
        return pagamentoService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDto> getById(@PathVariable @NotNull Long id) {
        PagamentoDto dtoPayment = pagamentoService.getById(id);

        return ResponseEntity.ok(dtoPayment);
    }

    @PostMapping
    public ResponseEntity<PagamentoDto> registerPayment(@RequestBody @Valid PagamentoDto dtoPayment, UriComponentsBuilder uriBuilder) {
        PagamentoDto payment = pagamentoService.createPayment(dtoPayment);
        URI uri = uriBuilder.path("/pagamentos/{id}").buildAndExpand(payment.getId()).toUri();
        return ResponseEntity.ok(payment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDto> updatePayment(@PathVariable @NotNull @RequestBody @Valid Long id, PagamentoDto dtoPayment) {
        PagamentoDto paymentUpdated = pagamentoService.updatePayment(id, dtoPayment);
        return ResponseEntity.ok(paymentUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable @NotNull Long id) {
        pagamentoService.deletePayment(id);
        return ResponseEntity.ok().build();
    }
}
