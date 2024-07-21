package com.fiap.parquimetro.dominio.util;

import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.fill.JRAbstractLRUVirtualizer;
import net.sf.jasperreports.engine.fill.JRGzipVirtualizer;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@AllArgsConstructor
@Service
public class RelatorioUtil {

    private ResourceLoader resourceLoader;

    public byte[] gerarRelatorioEmByte(String caminhoCompletoRelatorio, String caminhoSubReport, String nomeRelatorio,
                                       List listaDeObjetos, Map<String, Object> map) throws Exception {

        preencherParamentro(map, listaDeObjetos, nomeRelatorio, caminhoCompletoRelatorio, caminhoSubReport);
        return relatorioemByte(caminhoCompletoRelatorio, listaDeObjetos, map);
    }


    private void preencherParamentro(Map<String, Object> parametros, List dados, String nomeRelatorio,
                                     String caminhoCompletoRelatorio, String caminhoSubReport) throws Exception {
        if (dados.isEmpty()) {
            throw new Exception("A grade de dados está vazia! Realize Consulta para gerar Impressão.");
        }
        parametros.put("imgRod", pegaLocalImagem("img-log.png"));
        parametros.put("nomeRelatorio", nomeRelatorio);
        parametros.put("impressao", caminhoCompletoRelatorio);
        parametros.put("SUBREPORT_DIR", pegaLocalRelatorio(caminhoSubReport) + File.separator);
        parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

        //Instancia o virtualizador
        JRAbstractLRUVirtualizer virtualizer = new JRGzipVirtualizer(50);
        //Seta o parametro REPORT_VIRTUALIZER com a instância da virtualização
        parametros.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
    }

    private String pegaLocalImagem(String imagem) {
        return this.getClass().getResource("/imagens/" + imagem).toString();
    }

    private String pegaLocalRelatorio(String relatorio) {
        URL url = this.getClass().getResource("/relatorio/" + relatorio);
        return url == null ? "" : url.toString();
    }

    private byte[] relatorioemByte(String relatorio, List dados, Map<String, Object> parametros) throws JRException {
        JRDataSource jrRS = new JRBeanCollectionDataSource(dados);
        InputStream inputStream = this.getClass().getResourceAsStream(relatorio);
        JasperPrint print = JasperFillManager.fillReport(inputStream, parametros, jrRS);
        return JasperExportManager.exportReportToPdf(print);
    }

}
