package br.com.Gabriel.APIPaymentsEFI.gerencianet.pix.pix.controller;

@lombok.Getter
@lombok.Setter
public class PixRequest {
    // private String cpf;
    // private String nome;
    // private String chavePix;
    private double valor;
    // private String solicitacaoPagador;
    // private String campo1;
    // private String campo2;

    @Override
    public String toString() {
        return "ChargeRequest{"
                + "valor=" + valor
                + '}';
    }

    /*
     * @public String toString() {
     * return "ChargeRequest{" +
     * "cpf='" + cpf + '\'' +
     * ", nome='" + nome + '\'' +
     * ", chavePix='" + chavePix + '\'' +
     * ", valor=" + valor +
     * ", solicitacaoPagador='" + solicitacaoPagador + '\'' +
     * ", campo1='" + campo1 + '\'' +
     * ", campo2='" + campo2 + '\'' +
     * '}';
     * }
     */

}
