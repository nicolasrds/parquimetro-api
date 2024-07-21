package com.fiap.parquimetro.dominio.util;

import lombok.Getter;

@Getter
public enum TipoExtencaoArquivo {
    EXCEL("application/msexcel",".xls",false),
    XML("application/xml",".xml",false),
    TXT("application/txt",".txt",false),
    PDF("application/pdf",".pdf",false)
    ;

    private final String mime;
    private final String extencao;
    private final boolean renderimagem;

    private TipoExtencaoArquivo(String mime, String extencao, boolean renderimagem) {
        this.mime = mime;
        this.extencao = extencao;
        this.renderimagem = renderimagem;
    }

    public static String getMime(String ext){
        switch(ext){
            case ".xls":
                return TipoExtencaoArquivo.EXCEL.getMime();
            case ".pdf":
                return TipoExtencaoArquivo.PDF.getMime();
            case ".xml":
                return TipoExtencaoArquivo.XML.getMime();
            case ".txt":
                return TipoExtencaoArquivo.TXT.getMime();
            }
        return null;
    }

}
