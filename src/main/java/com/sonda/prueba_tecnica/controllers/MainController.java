package com.sonda.prueba_tecnica.controllers;

import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import static com.sonda.prueba_tecnica.PruebaTecnicaApplication.BinaryOperations;

@RestController
@RequestMapping("/")
public class MainController {

    @PostMapping("/")
    public Map<String, Integer> getOperation(@RequestBody Map<String, Integer> input) throws IOException {
        int numero_de_registro = input.get("numero de registro");   //get record number for search it in the binary file
        short operation = BinaryOperations.get(numero_de_registro);
        Map<String, Integer> response = new HashMap<>();
        response.put("numero de registro", numero_de_registro);
        response.put("numero de operacion", (int)operation);
        return response;

    }
}
