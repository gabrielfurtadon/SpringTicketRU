package br.com.Gabriel.APIPaymentsEFI.gerencianet.pix.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import br.com.Gabriel.APIPaymentsEFI.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

@Service
public class PixService {

    public JSONObject generateCharge2(String cpf, String nome, String chavePix, double valor,
            String solicitacaoPagador) throws FileNotFoundException {
        String campo1 = "Informação Adicional1 do PSP-Recebedor";
        String campo2 = "Informação Adicional3 do PSP-Recebedor";
        Credentials credentials = new Credentials();

        JSONObject options = new JSONObject();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("certificate", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());

        JSONObject body = new JSONObject();
        body.put("calendario", new JSONObject().put("expiracao", 3600));
        body.put("devedor", new JSONObject().put("cpf", cpf).put("nome", nome));
        body.put("valor", new JSONObject().put("original", String.valueOf(valor)));
        body.put("chave", "acbf2098-fc24-4e9d-ace8-6b99fd5dee24");
        body.put("solicitacaoPagador", solicitacaoPagador);

        JSONArray infoAdicionais = new JSONArray();
        infoAdicionais.put(new JSONObject().put("nome", "Campo 1").put("valor", campo1));
        infoAdicionais.put(new JSONObject().put("nome", "Campo 2").put("valor", campo2));
        body.put("infoAdicionais", infoAdicionais);

        try {
            Gerencianet gn = new Gerencianet(options);
            JSONObject response = gn.call("pixCreateImmediateCharge", new HashMap<String, String>(), body);
            return response;
        } catch (GerencianetException e) {
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * This Java function generates a charge using the Gerencianet API for a given
     * value and returns a
     * list of extracted values from the response.
     * 
     * @param valor The amount of money to be charged.
     * @return A List of Strings containing information about a payment charge
     *         generated through the
     *         Gerencianet API.
     */
    public List<String> generateCharge(String valor) throws FileNotFoundException {
        String campo1 = "Informação Adicional1 do PSP-Recebedor";
        String campo2 = "Informação Adicional3 do PSP-Recebedor";
        String cpf = "04145605403";
        String nome = "Leonardo da Silva Sena";
        String chavePix = "auxlucas57@gmail.com";
        String solicitacaoPagador = "Serviço realizado";
        Credentials credentials = new Credentials();
        System.out.println(valor);

        JSONObject options = new JSONObject();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("certificate", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());

        JSONObject body = new JSONObject();
        body.put("calendario", new JSONObject().put("expiracao", 3600));
        body.put("devedor", new JSONObject().put("cpf", cpf).put("nome", nome));
        body.put("valor", new JSONObject().put("original", String.valueOf(valor)));
        body.put("chave", "acbf2098-fc24-4e9d-ace8-6b99fd5dee24");
        body.put("solicitacaoPagador", solicitacaoPagador);

        JSONArray infoAdicionais = new JSONArray();
        infoAdicionais.put(new JSONObject().put("nome", "Campo 1").put("valor", campo1));
        infoAdicionais.put(new JSONObject().put("nome", "Campo 2").put("valor", campo2));
        body.put("infoAdicionais", infoAdicionais);

        try {
            Gerencianet gn = new Gerencianet(options);
            JSONObject response = gn.call("pixCreateImmediateCharge", new HashMap<String, String>(), body);

            // Extract the values from the response and store them in an ArrayList
            List<String> values = new ArrayList<>();
            values.add(response.getJSONObject("loc").getString("location"));
            values.add(String.valueOf(response.getJSONObject("loc").getInt("id")));
            values.add(response.getJSONObject("loc").getString("criacao"));
            values.add(response.getJSONObject("loc").getString("tipoCob"));
            values.add(response.getJSONObject("devedor").getString("cpf"));
            values.add(response.getJSONObject("devedor").getString("nome"));
            values.add(response.getJSONObject("valor").getString("original"));
            values.add(response.getString("chave"));
            values.add(String.valueOf(response.getJSONObject("calendario").getInt("expiracao")));

            return values;
        } catch (GerencianetException e) {
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String generateQRCode(String id) throws FileNotFoundException {
        Credentials credentials = new Credentials();

        JSONObject options = new JSONObject();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("certificate", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id", id);

        try {
            Gerencianet gn = new Gerencianet(options);
            Map<String, Object> response = gn.call("pixGenerateQRCode", params, new HashMap<String, Object>());

            String base64Image = ((String) response.get("imagemQrcode")).split(",")[1];
            return base64Image;
        } catch (GerencianetException e) {
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        PixService pixService = new PixService();

        System.out.println(pixService.generateCharge(0.01));

        // call the method for generate QRCode
        System.out.println(pixService.generateQRCode("17"));
    }

}
