package com.example.PIQResponseMock.services;

import com.example.PIQResponseMock.dto.AuthorizeDTO;
import com.example.PIQResponseMock.dto.CancelDTO;
import com.example.PIQResponseMock.dto.TransferDTO;
import com.example.PIQResponseMock.model.Authorize;
import com.example.PIQResponseMock.model.Cancel;
import com.example.PIQResponseMock.model.Transfer;
import com.example.PIQResponseMock.repositories.AuthorizeRepository;
import com.example.PIQResponseMock.repositories.CancelRepository;
import com.example.PIQResponseMock.repositories.TransferRepository;
import com.example.PIQResponseMock.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ResponseService {

    AuthorizeRepository authorizeRepository;
    TransferRepository transferRepository;
    CancelRepository cancelRepository;
    UserRepository userRepository;

    public ResponseService(AuthorizeRepository authorizeRepository, TransferRepository transferRepository, CancelRepository cancelRepository, UserRepository userRepository) {
        this.authorizeRepository = authorizeRepository;
        this.transferRepository = transferRepository;
        this.cancelRepository = cancelRepository;
        this.userRepository = userRepository;
    }

    //The resId will be the same as the users userId. So in the FE it is the userId that will need to be sent

/*    public ResponseEntity<Authorize> getAuthorize(String userId) {
        Authorize authorize = authorizeRepository.findByUserId(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Authorize not found " + userId));
        return ResponseEntity.ok(authorize);
    }*/

    public ResponseEntity<Transfer> getTransfer(String resId) {
        Transfer transfer = transferRepository.findById(resId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transfer not found " + resId));
        return new ResponseEntity(transfer, HttpStatus.OK);
    }

    public ResponseEntity<Cancel> getCancel(String resId) {
        Cancel cancel = cancelRepository.findById(resId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cancel not found " + resId));
        return new ResponseEntity(cancel, HttpStatus.OK);
    }

    public ResponseEntity<Authorize> updateAuthorize(String resId, AuthorizeDTO authorizeDTO) {
        Authorize authorize = authorizeRepository.findById(resId).orElseThrow(() -> new ResponseStatusException(HttpStatus.OK, "Authorize not found" + resId));
        authorize.setUserId(authorizeDTO.getUserId());//wrong
        authorize.setSuccess(authorizeDTO.isSuccess());
        authorize.setMerchantTxId(authorizeDTO.getMerchantTxId());
        authorize.setAuthCode(authorizeDTO.getAuthCode());
        authorize.setErrCode(authorizeDTO.getErrCode());
        authorize.setErrMsg(authorizeDTO.getErrMsg());
        authorizeRepository.save(authorize);
        return new ResponseEntity(authorize, HttpStatus.OK);
    }

    public ResponseEntity<Transfer> updateTransfer(String resId, TransferDTO transferDTO) {
        Transfer transfer = transferRepository.findById(resId).orElseThrow(() -> new ResponseStatusException(HttpStatus.OK, "Transfer not found" + resId));
        transfer.setUserId(transferDTO.getUserId());
        transfer.setSuccess(transferDTO.isSuccess());
        transfer.setMerchantTxId(transferDTO.getMerchantTxId());
        transfer.setErrCode(transferDTO.getErrCode());
        transfer.setErrMsg(transferDTO.getErrMsg());
        transferRepository.save(transfer);
        return new ResponseEntity(transfer, HttpStatus.OK);
    }

    public ResponseEntity<Cancel> updateCancel(String resId, CancelDTO cancelDTO) {
        Cancel cancel = cancelRepository.findById(resId).orElseThrow(() -> new ResponseStatusException(HttpStatus.OK, "Cancel not found" + resId));
        cancel.setUserId(cancelDTO.getUserId());
        cancel.setSuccess(cancelDTO.isSuccess());
        cancel.setErrCode(cancelDTO.getErrCode());
        cancel.setErrMsg(cancelDTO.getErrMsg());
        cancelRepository.save(cancel);
        return new ResponseEntity(cancel, HttpStatus.OK);
    }
}
