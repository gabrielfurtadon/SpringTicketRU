package br.com.Gabriel.APIPaymentsEFI.gerencianet.pix.service;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import br.com.Gabriel.APIPaymentsEFI.gerencianet.Credentials;
import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;
import org.json.JSONObject;

@Service
public class CreditCardService {
    Credentials credentials;

    public CreditCardService() {
        try {
            credentials = new Credentials();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // You should handle this situation properly,
            // perhaps by re-throwing it as an unchecked exception
            throw new RuntimeException(e);
        }
    }

    public String createChargeAndCheckStatus(String value, String paymentToken, String name, String cpf, String email) {
        JSONObject options = new JSONObject();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("pix_cert", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());

        JSONObject body = new JSONObject();
        body.put("items", new JSONObject[] {
                new JSONObject().put("name", "Product 1").put("value", Integer.parseInt(value)).put("amount", 1)
        });

        Gerencianet gn;
        JSONObject chargeResponse, paymentResponse;
        String paymentStatus = "";
        try {
            gn = new Gerencianet(options);
            chargeResponse = gn.call("createCharge", new HashMap<String, String>(), body);

            int chargeId = chargeResponse.getJSONObject("data").getInt("charge_id");
            Map<String, String> params = new HashMap<String, String>() {
                {
                    put("id", String.valueOf(chargeId));
                }
            };

            JSONObject customer = new JSONObject();
            customer.put("name", name);
            customer.put("cpf", cpf);
            customer.put("phone_number", "87981682519");
            customer.put("email", email);

            JSONObject billingAddress = new JSONObject() {
                {
                    put("street", "Av. JK");
                    put("number", 909);
                    put("neighborhood", "Bauxita");
                    put("zipcode", "35400000");
                    put("city", "Ouro Preto");
                    put("state", "MG");
                }
            };

            JSONObject creditCardInfo = new JSONObject();
            creditCardInfo.put("installments", 1);
            creditCardInfo.put("billing_address", billingAddress);
            creditCardInfo.put("payment_token", paymentToken);
            creditCardInfo.put("customer", customer);

            JSONObject paymentBody = new JSONObject();
            paymentBody.put("payment", new JSONObject() {
                {
                    put("credit_card", creditCardInfo);
                }
            });

            paymentResponse = gn.call("payCharge", params, paymentBody);
            paymentStatus = paymentResponse.getString("status");

        } catch (GerencianetException e) {
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return paymentStatus;
    }

    public static void main(String[] args) {
        // Initialize the CreditCardService
        CreditCardService creditCardService = new CreditCardService();

        // Set your parameters
        String value = "100"; // charge value
        String cardNumber = "4012001037141112"; // credit card number
        String cvv = "123"; // credit card cvv
        String expirationMonth = "05"; // credit card expiration month
        String expirationYear = "2028"; // credit card expiration year
        String brand = "visa"; // credit card brand
        String name = "Gorbadoc Oldbuck"; // customer's name
        String cpf = "04267484171"; // customer's cpf
        String email = "oldbuck@shire.com"; // customer's email

        try {
            // Create a charge and check its status
            // String status = creditCardService.createChargeAndCheckStatus(value,
            // cardNumber, cvv, expirationMonth, expirationYear, brand, name, cpf, email);

            // Print the status
            // System.out.println("Payment status: " + status);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
