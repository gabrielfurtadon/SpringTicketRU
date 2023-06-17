package br.com.Gabriel.APIPaymentsEFI.gerencianet.pix.service;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.Gabriel.APIPaymentsEFI.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

public class PixCreateImmediateCharge {
  public static void main(String[] args) throws FileNotFoundException {

    Credentials credentials = new Credentials();

    JSONObject options = new JSONObject();
    options.put("client_id", credentials.getClientId());
    options.put("client_secret", credentials.getClientSecret());
    options.put("certificate", credentials.getCertificate());
    options.put("sandbox", credentials.isSandbox());

    JSONObject body = new JSONObject();
    body.put("calendario", new JSONObject().put("expiracao", 3600));
    body.put("devedor", new JSONObject().put("cpf", "04145605403").put("nome", "Leonardo da Silva Sena")); // Dados do
                                                                                                           // pagador
    body.put("valor", new JSONObject().put("original", "0.01"));
    body.put("chave", "acbf2098-fc24-4e9d-ace8-6b99fd5dee24"); // Chave pix do recebedor
    body.put("solicitacaoPagador", "Serviço realizado.");

    JSONArray infoAdicionais = new JSONArray();
    infoAdicionais.put(new JSONObject().put("nome", "Campo 1").put("valor", "Informação Adicional1 do PSP-Recebedor"));
    infoAdicionais.put(new JSONObject().put("nome", "Campo 2").put("valor", "Informação Adicional2 do PSP-Recebedor"));
    body.put("infoAdicionais", infoAdicionais);

    try {
      Gerencianet gn = new Gerencianet(options);
      JSONObject response = gn.call("pixCreateImmediateCharge", new HashMap<String, String>(), body);
      System.out.println(response);
    } catch (GerencianetException e) {
      System.out.println(e.getError());
      System.out.println(e.getErrorDescription());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
