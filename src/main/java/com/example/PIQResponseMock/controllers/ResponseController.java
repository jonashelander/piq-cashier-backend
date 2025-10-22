package com.example.PIQResponseMock.controllers;

import com.example.PIQResponseMock.dto.AuthorizeDTO;
import com.example.PIQResponseMock.dto.CancelDTO;
import com.example.PIQResponseMock.dto.TransferDTO;
import com.example.PIQResponseMock.model.Authorize;
import com.example.PIQResponseMock.model.Cancel;
import com.example.PIQResponseMock.model.Transfer;
import com.example.PIQResponseMock.services.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/response", produces = "application/json;charset=utf8")
public class ResponseController {
    ResponseService responseService;

    @Autowired
    public ResponseController(ResponseService responseService) {
        this.responseService = responseService;
    }

/*    @GetMapping("/authorize/{userId}")
    public ResponseEntity<Authorize> getAuthorize(@PathVariable String userId) {
        return responseService.getAuthorize(userId);
    }*/

    @GetMapping("/transfer/{resId}")
    public ResponseEntity<Transfer> getTransfer(@PathVariable String userId) {
        return responseService.getTransfer(userId);
    }

    @GetMapping("/cancel/{resId}")
    public ResponseEntity<Cancel> getCancel(@PathVariable String userId) {
        return responseService.getCancel(userId);
    }

    @PutMapping("/authorize/{id}")
    public ResponseEntity<Authorize> updateAuthorize(@PathVariable String userId, @RequestBody AuthorizeDTO authorizeDTO) {
        System.out.println(authorizeDTO);
        return responseService.updateAuthorize(userId, authorizeDTO);
    }

    @PutMapping("/transfer/{id}")
    public ResponseEntity<Transfer> updateTransfer(@PathVariable String userId, @RequestBody TransferDTO transferDTO) {
        return responseService.updateTransfer(userId, transferDTO);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Cancel> updateCancel(@PathVariable String userId, @RequestBody CancelDTO cancelDTO) {
        return responseService.updateCancel(userId, cancelDTO);
    }
}
