package br.com.Gabriel.APIPaymentsEFI.gerencianet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Credentials {
    private String clientId;
    private String clientSecret;
    private String certificate;
    private boolean sandbox;
    private boolean debug;

    public Credentials() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(
                "C:\\Users\\auxlu\\OneDrive\\Documentos\\Workspaces\\React\\TicketRU\\TicketRU_BackEnd\\SpringTicketRU\\src\\main\\java\\br\\com\\Gabriel\\APIPaymentsEFI\\gerencianet\\credentials.json");
        JSONTokener tokener = new JSONTokener(fileInputStream);
        JSONObject credentials = new JSONObject(tokener);
        try {
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println("Impossible to close file credentials.json");
        }

        this.clientId = credentials.getString("client_id");
        this.clientSecret = credentials.getString("client_secret");
        this.certificate = credentials.getString("certificate");
        this.sandbox = credentials.getBoolean("sandbox");
        this.debug = credentials.getBoolean("debug");
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getCertificate() {
        return certificate;
    }

    public boolean isSandbox() {
        return sandbox;
    }

    public boolean isDebug() {
        return debug;
    }

    // testing purposes only, show credentials on console
    public static void main(String args[]) throws FileNotFoundException {
        Credentials credentials = new Credentials();
        System.out.println("CLIENT_ID: " + credentials.getClientId());
        System.out.println("CLIENT_SECRET: " + credentials.getClientSecret());
        System.out.println("CERTIFICATE: " + credentials.getCertificate());
        System.out.println("SANDBOX: " + credentials.isSandbox());
    }

}
