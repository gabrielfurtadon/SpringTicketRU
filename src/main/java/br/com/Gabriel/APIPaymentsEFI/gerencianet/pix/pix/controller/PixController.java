package br.com.Gabriel.APIPaymentsEFI.gerencianet.pix.pix.controller;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.util.Base64;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.Gabriel.APIPaymentsEFI.gerencianet.pix.service.PixService;

@RestController
@RequestMapping("/pix")
public class PixController {

    private final PixService pixService;

    public PixController(PixService pixService) {
        this.pixService = pixService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/copia-e-cola", produces = "application/json")
    public ResponseEntity<List<List<String>>> generateCharge(@RequestBody PixRequest request) {
        try {
            double valor = request.getValor();

            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat decimalFormat = new DecimalFormat("0.00", symbols);
            String valorString = decimalFormat.format(valor);

            List<String> response = pixService.generateCharge(valorString);
            List<List<String>> responseList = Arrays.asList(response);
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            // error response
            List<String> errorResponse = Arrays.asList("error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Arrays.asList(errorResponse));
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/copia-e-cola2", produces = "application/json")
    public ResponseEntity<List<List<String>>> generateCharge2(@RequestBody Map<String, String> request) {
        try {
            // Convert the request map to JSON string
            String jsonRequest = new ObjectMapper().writeValueAsString(request);

            // Use ObjectMapper to validate the JSON
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.readTree(jsonRequest);

            String valor = request.get("valor");

            List<String> response = pixService.generateCharge(valor);

            List<List<String>> responseList = Arrays.asList(response);
            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            // error response
            List<String> errorResponse = Arrays.asList("error");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Arrays.asList(errorResponse));
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/qrcode/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generateQRCode(@PathVariable("id") String id) {
        try {
            String base64Image = pixService.generateQRCode(id);

            // Convert the base64 image to byte array
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);

            // Set the response headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentLength(imageBytes.length);

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/charges")
    public void listCharges(
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime)
            throws FileNotFoundException {
        LocalDateTime startDateTime = LocalDateTime.parse(startTime, DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime endDateTime = LocalDateTime.parse(endTime, DateTimeFormatter.ISO_DATE_TIME);

        pixService.listCharges(startDateTime, endDateTime);
    }

    @GetMapping("/charge-status/{id}/{startTime}/{endTime}")
    public ResponseEntity<List<Map<String, String>>> getChargeStatus(
            @PathVariable String id,
            @PathVariable String startTime,
            @PathVariable String endTime) {
        try {

            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

            LocalDateTime startDateTime = LocalDateTime.parse(startTime, formatter);
            LocalDateTime endDateTime = LocalDateTime.parse(endTime, formatter);
            List<Map<String, String>> chargeStatus = pixService.getChargeStatus(Integer.parseInt(id), startDateTime,
                    endDateTime);
            System.out.println(chargeStatus);
            return chargeStatus != null ? ResponseEntity.ok(chargeStatus) : ResponseEntity.notFound().build();
        } catch (FileNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
