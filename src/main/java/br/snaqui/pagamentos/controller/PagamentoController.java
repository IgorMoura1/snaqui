package br.snaqui.pagamentos.controller;

import br.snaqui.pagamentos.dto.PagamentoDto;
import br.snaqui.pagamentos.service.PagamentoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping
    public Page<PagamentoDto> list(@PageableDefault(size = 10) Pageable pagination) {
        return pagamentoService.getAll(pagination);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDto> detail(@PathVariable @Valid @NotNull Long id) {
        return ResponseEntity.ok(pagamentoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PagamentoDto> create(@RequestBody @Valid PagamentoDto dto, UriComponentsBuilder uriBuilder) {
        PagamentoDto payment = pagamentoService.createPayment(dto);
        URI address = uriBuilder.path("/pagamentos/{id}").buildAndExpand(payment.getId()).toUri();
        return ResponseEntity.ok(pagamentoService.createPayment(dto));
    }

    @PutMapping
    public ResponseEntity<PagamentoDto> update(@PathVariable @Valid @NotNull Long id, @RequestBody @Valid PagamentoDto dto) {
        PagamentoDto payment = pagamentoService.updatePayment(id, dto);
        return ResponseEntity.ok(payment);
    }

    @DeleteMapping
    public ResponseEntity<PagamentoDto> delete(@PathVariable @Valid @NotNull Long id) {
        pagamentoService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
