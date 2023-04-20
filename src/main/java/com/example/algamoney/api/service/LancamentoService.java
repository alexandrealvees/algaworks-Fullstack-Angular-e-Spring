package com.example.algamoney.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.LancamentoRepository;
import com.example.algamoney.api.repository.PessoaRepository;
import com.example.algamoney.api.service.exception.PessoaInexistenteOuInativaException;

import jakarta.validation.Valid;

@Service
public class LancamentoService {

	@Autowired
	PessoaRepository pessoaRepository;

	@Autowired
	LancamentoRepository lancamentoRepository;

	public Lancamento salvar(@Valid Lancamento lancamento) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
		if (pessoa.isPresent() || pessoa.get().isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}

		return lancamentoRepository.save(lancamento);
	}

}
