package com.unisp.gestione.controllers;

import com.unisp.gestione.models.Documento;
import com.unisp.gestione.services.DocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/documenti")
@RequiredArgsConstructor
public class DocumentoController {
    private final DocumentoService documentoService;

    @PostMapping("/carica")
    public ResponseEntity<Documento> caricaDocumento(
            @RequestParam Long membroId,
            @RequestParam String tipo,
            @RequestParam MultipartFile file) {
        return ResponseEntity.ok(documentoService.caricaDocumento(membroId, tipo, file));
    }

    @PutMapping("/{id}/approva")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<Documento> approvaDocumento(@PathVariable Long id) {
        return ResponseEntity.ok(documentoService.approvaDocumento(id));
    }

    @PutMapping("/{id}/rifiuta")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<Documento> rifiutaDocumento(
            @PathVariable Long id,
            @RequestParam String motivazione) {
        return ResponseEntity.ok(documentoService.rifiutaDocumento(id, motivazione));
    }

    @GetMapping("/membro/{membroId}")
    public ResponseEntity<List<Documento>> getDocumentiMembro(@PathVariable Long membroId) {
        return ResponseEntity.ok(documentoService.getDocumentiMembro(membroId));
    }
}
