package com.fiap.parquimetro.dominio.controleEstacionamento.registro.service;

import com.fiap.parquimetro.dominio.controleCadastro.estacionamento.service.EstacionamentoService;
import com.fiap.parquimetro.dominio.controleCadastro.veiculo.service.VeiculoService;
import com.fiap.parquimetro.dominio.controleEstacionamento.registro.dto.DadosRegistro;
import com.fiap.parquimetro.dominio.controleEstacionamento.registro.entity.Registro;
import com.fiap.parquimetro.dominio.controleEstacionamento.registro.enumeration.TipoRegistro;
import com.fiap.parquimetro.dominio.controleEstacionamento.registro.repository.RegistroRepository;
import com.fiap.parquimetro.infra.exception.RegraDeNegocioException;
import com.fiap.parquimetro.infra.util.MessageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
@CacheConfig(cacheNames = {"registroCache"})
@Slf4j
public class RegistroService {

    private final RegistroRepository registroRepository;
    private final VeiculoService veiculoService;
    private final EstacionamentoService estacionamentoService;
    private final MessageService messageService;


    @Cacheable(key = "#pageable", unless = "#result == null")
    public Page<DadosRegistro> consultar(Pageable pageable) {
        var listaDeRegistros = registroRepository.findAll(pageable);
        return listaDeRegistros.map(DadosRegistro::new);
    }

    @Cacheable(key = "#id")
    public Registro carregar(Long id) {
        return registroRepository.findById(id)
                .orElseThrow(()
                        -> new EntityNotFoundException(messageService.getMessage("registroNaoEncontrado")));
    }

    @CacheEvict(allEntries = true, cacheNames = "registroCache")
    public Registro salvar(DadosRegistro dadosRegistro) {

        if (dadosRegistro.tipoRegistro().equals(TipoRegistro.FIXO)
                && (dadosRegistro.duracao() == null || dadosRegistro.duracao() == 0)) {
            throw new RegraDeNegocioException(messageService.getMessage("tempoDuracaoInvalido"));
        }

        var veiculo = veiculoService.buscarVeiculo(dadosRegistro.veiculoRegistro().id())
                .orElseThrow(()
                        -> new NoSuchElementException(messageService.getMessage("veiculoNaoEncontrado")));

        var estacionamento = estacionamentoService.buscarEstacionamentoPorId(
                        dadosRegistro.estacionamentoRegistro().id())
                .orElseThrow(()
                        -> new NoSuchElementException(messageService.getMessage("estacionamentoNaoEncontrado")));

        Registro registro = new Registro(dadosRegistro, veiculo, estacionamento);
        return registroRepository.save(registro);
    }

    @CacheEvict(allEntries = true, cacheNames = "registroCache")
    public void encerrar(Long id) {
        Registro registro = registroRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(messageService.getMessage("registroNaoEncontrado")));
        if (registro.getFim() != null) {
            throw new RegraDeNegocioException(messageService.getMessage("registroEncerrado"));
        }
        registro.setFim(LocalDateTime.now());
        registroRepository.save(registro);
    }

    public List<Registro> consultarRegistrosAbertos() {
        return registroRepository.findByDataFimIsNull();
    }

    /**
     * A cada minuto executa a consulta para checar quem está aberto e que já vai adicionar mais 1 hora
     */
    @Scheduled(cron = "0 0/1 * * * *", zone = "America/Fortaleza")
    public void notificar() {
        log.info("***Iniciando checagem.");
        List<Registro> registros = consultarRegistrosAbertos();
        registros.stream()
                .filter(Registro::isDeveNotificar)
                .forEach(registro -> {
                    registro.notificar();
                    registroRepository.save(registro);
                });
        log.info("***Finalizando checagem.");
    }


}
