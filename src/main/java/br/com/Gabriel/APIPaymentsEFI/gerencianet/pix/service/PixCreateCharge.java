package br.com.Gabriel.APIPaymentsEFI.gerencianet.pix.service;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.Gabriel.APIPaymentsEFI.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class PixCreateCharge {
  public static void main(String[] args) {

    Credentials credentials = null;
    try {
      credentials = new Credentials();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    JSONObject options = new JSONObject();
    options.put("client_id", credentials.getClientId());
    options.put("client_secret", credentials.getClientSecret());
    options.put("certificate", credentials.getCertificate());
    options.put("sandbox", credentials.isSandbox());

    HashMap<String, String> params = new HashMap<String, String>();
    params.put("txid", " ");

    JSONObject body = new JSONObject();
    body.put("calendario", new JSONObject().put("expiracao", 3600));
    body.put("devedor", new JSONObject().put("cpf", "71198826436").put("Lucas", "Gorbadoc Oldbuck"));
    body.put("valor", new JSONObject().put("original", "0.01"));
    body.put("chave", "71126436");
    body.put("solicitacaoPagador", "Serviço realizado.");

    JSONArray infoAdicionais = new JSONArray();
    infoAdicionais.put(new JSONObject().put("nome", "Campo 1").put("valor", "Informação Adicional1 do PSP-Recebedor"));
    infoAdicionais.put(new JSONObject().put("nome", "Campo 2").put("valor", "Informação Adicional2 do PSP-Recebedor"));
    body.put("infoAdicionais", infoAdicionais);

    try {
      Gerencianet gn = new Gerencianet(options);
      JSONObject response = gn.call("pixCreateCharge", params, body);
      System.out.println(response);

    } catch (GerencianetException e) {
      System.out.println(e.getError());
      System.out.println(e.getErrorDescription());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
