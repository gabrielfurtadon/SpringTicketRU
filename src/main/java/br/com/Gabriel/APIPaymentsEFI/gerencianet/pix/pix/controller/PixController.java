package br.com.Gabriel.APIPaymentsEFI.gerencianet.pix.pix.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<List<String>>> generateCharge(@RequestBody Map<String, String> request) {
        System.out.println(request.get("valor"));
        try {
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

}
