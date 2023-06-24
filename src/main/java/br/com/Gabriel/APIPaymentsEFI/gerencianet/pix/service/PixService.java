package br.com.Gabriel.APIPaymentsEFI.gerencianet.pix.service;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import br.com.Gabriel.APIPaymentsEFI.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;

@Service
public class PixService {

    // constructor
    public PixService() {
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

        // transform the value to string

        String campo1 = "Informação Adicional1 do PSP-Recebedor";
        String campo2 = "Informação Adicional3 do PSP-Recebedor";
        String cpf = "04145605403";
        String nome = "Leonardo da Silva Sena";
        String chavePix = "auxlucas57@gmail.com";
        String solicitacaoPagador = "Serviço realizado";
        Credentials credentials = new Credentials();

        JSONObject options = new JSONObject();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("certificate", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());

        JSONObject body = new JSONObject();
        body.put("calendario", new JSONObject().put("expiracao", 3600));
        body.put("devedor", new JSONObject().put("cpf", cpf).put("nome", nome));
        body.put("valor", new JSONObject().put("original", valor));
        body.put("chave", "acbf2098-fc24-4e9d-ace8-6b99fd5dee24");
        body.put("solicitacaoPagador", solicitacaoPagador);

        JSONArray infoAdicionais = new JSONArray();
        infoAdicionais.put(new JSONObject().put("nome", "Campo 1").put("valor", campo1));
        infoAdicionais.put(new JSONObject().put("nome", "Campo 2").put("valor", campo2));
        body.put("infoAdicionais", infoAdicionais);

        try {
            Gerencianet gn = new Gerencianet(options);
            JSONObject response = gn.call("pixCreateImmediateCharge", new HashMap<String, String>(), body);
            // System.out.println(response.toString());
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
            String status = response.getString("status");
            values.add(status);

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

    public JSONObject listCharges(LocalDateTime startTime, LocalDateTime endTime) throws FileNotFoundException {
        Credentials credentials = new Credentials();

        String startTimeString = startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        String endTimeString = endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));

        System.out.println("start time list charges" + startTimeString);
        System.out.println("end time list charges" + endTimeString);
        JSONObject options = new JSONObject();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("certificate", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());

        HashMap<String, String> params = new HashMap<>();
        params.put("inicio", startTimeString); // "2023-06-22T00:00:00Z
        params.put("fim", endTimeString);

        try {
            Gerencianet gn = new Gerencianet(options);
            JSONObject response = gn.call("pixListCharges", params, new JSONObject());

            return response;

        } catch (GerencianetException e) {
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Map<String, String>> getChargeStatus(Integer id, LocalDateTime startTime, LocalDateTime endTime)
            throws FileNotFoundException {
        JSONObject response = listCharges(startTime, endTime);

        if (response != null) {
            JSONArray cobs = response.getJSONArray("cobs");
            if (cobs != null) {
                for (int i = 0; i < cobs.length(); i++) {
                    JSONObject cob = cobs.getJSONObject(i);
                    JSONObject charge = cob.getJSONObject("loc");
                    if (charge.getInt("id") == id) {
                        Map<String, String> values = new HashMap<>();
                        values.put("location", charge.getString("location"));
                        values.put("id", String.valueOf(charge.getInt("id")));
                        values.put("criacao", charge.getString("criacao"));
                        values.put("tipoCob", charge.getString("tipoCob"));
                        values.put("cpf", cob.getJSONObject("devedor").getString("cpf"));
                        values.put("nome", cob.getJSONObject("devedor").getString("nome"));
                        values.put("original", cob.getJSONObject("valor").getString("original"));
                        values.put("chave", cob.getString("chave"));
                        values.put("expiracao", String.valueOf(cob.getJSONObject("calendario").getInt("expiracao")));
                        values.put("status", cob.getString("status"));
                        System.out.println("values" + values);
                        return Arrays.asList(values);
                    }
                }
            }
        }
        return null;
    }

    public String getChargeStatus2(String id) throws FileNotFoundException {
        Credentials credentials = new Credentials();

        JSONObject options = new JSONObject();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("certificate", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());

        try {
            Gerencianet gn = new Gerencianet(options);
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", "85");
            JSONObject response = gn.call("pixDetailCharge", params, new JSONObject());
            System.out.println(response.toString());

            String status = response.getString("status");
            return status;

        } catch (GerencianetException e) {
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) throws FileNotFoundException {
        PixService pixService = new PixService();

        // System.out.println(pixService.generateCharge("0.01"));
        // System.out.println("\n\n\n\n\n\n\n -- \n\n\n\n\n\n\n");

        // call the method for generate QRCode
        // System.out.println(pixService.generateQRCode("17"));

        // System.out.println(pixService.getChargeInformation("55"));

        // pixService.getStatus("55");

        pixService.listCharges(LocalDateTime.now(), LocalDateTime.now().plusDays(1));
    }

}
