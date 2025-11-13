package com.example.PIQResponseMock.helpers;

import com.example.PIQResponseMock.dto.*;
import com.example.PIQResponseMock.model.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class Convert {
    public static UserDTO convertUserToDTO(User user) {

        AuthorizeDTO authorizeDTO = null;
        if (user.getAuthorize() != null) {
            authorizeDTO = new AuthorizeDTO(
                    user.getAuthorize().getUserId(),
                    user.getAuthorize().isSuccess(),
                    user.getAuthorize().getMerchantTxId(),
                    user.getAuthorize().getAuthCode(),
                    user.getAuthorize().getErrCode(),
                    user.getAuthorize().getErrMsg()
            );
        }

        TransferDTO transferDTO = null;
        if (user.getTransfer() != null) {
            transferDTO = new TransferDTO(
                    user.getTransfer().getUserId(),
                    user.getTransfer().isSuccess(),
                    user.getTransfer().getTxId(),
                    user.getTransfer().getMerchantTxId(),
                    user.getTransfer().getErrCode(),
                    user.getTransfer().getErrMsg()
            );
        }

        CancelDTO cancelDTO = null;
        if (user.getCancel() != null) {
            cancelDTO = new CancelDTO(
                    user.getCancel().getUserId(),
                    user.getCancel().isSuccess(),
                    user.getCancel().getErrCode(),
                    user.getCancel().getErrMsg()
            );
        }

        VerifyUserLogDTO verifyUserLogDTO = null;
        if (user.getVerifyUserLog() != null) {
            ObjectMapper mapper = new ObjectMapper();

            JsonNode incomingNode = null;
            JsonNode outgoingNode = null;

            try {
                if (user.getVerifyUserLog().getIncomingData() != null) {
                    incomingNode = mapper.readTree(user.getVerifyUserLog().getIncomingData());
                }
                if (user.getVerifyUserLog().getOutgoingData() != null) {
                    outgoingNode = mapper.readTree(user.getVerifyUserLog().getOutgoingData());
                }
            } catch (Exception e) {
                // Optional: log or ignore
            }
            verifyUserLogDTO = new VerifyUserLogDTO(incomingNode, outgoingNode);
        }

        AuthorizeLogDTO authorizeLogDTO = null;
        if (user.getAuthorizeLog() != null) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode incomingNode = null;
            JsonNode outgoingNode = null;
            try {
                if (user.getAuthorizeLog().getIncomingData() != null) {
                    incomingNode = mapper.readTree(user.getAuthorizeLog().getIncomingData());
                }
                if (user.getAuthorizeLog().getOutgoingData() != null) {
                    outgoingNode = mapper.readTree(user.getAuthorizeLog().getOutgoingData());
                }
            } catch (Exception e) {
                // Optional: log or ignore
            }
            authorizeLogDTO = new AuthorizeLogDTO(incomingNode, outgoingNode);
        }

        TransferLogDTO transferLogDTO = null;
        if (user.getTransferLog() != null) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode incomingNode = null;
            JsonNode outgoingNode = null;
            try {
                if (user.getTransferLog().getIncomingData() != null) {
                    incomingNode = mapper.readTree(user.getTransferLog().getIncomingData());
                }
                if (user.getTransferLog().getOutgoingData() != null) {
                    outgoingNode = mapper.readTree(user.getTransferLog().getOutgoingData());
                }
            } catch (Exception e) {
                // Optional: log or ignore
            }
            transferLogDTO = new TransferLogDTO(incomingNode, outgoingNode);
        }

        CancelLogDTO cancelLogDTO = null;
        if (user.getCancelLog() != null) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode incomingNode = null;
            JsonNode outgoingNode = null;
            try {
                if (user.getCancelLog().getIncomingData() != null) {
                    incomingNode = mapper.readTree(user.getCancelLog().getIncomingData());
                }
                if (user.getCancelLog().getOutgoingData() != null) {
                    outgoingNode = mapper.readTree(user.getCancelLog().getOutgoingData());
                }
            } catch (Exception e) {
                // Optional: log or ignore
            }
            cancelLogDTO = new CancelLogDTO(incomingNode, outgoingNode);
        }


        return new UserDTO(
                user.getId(),
                user.getUserId(),
                user.getSessionId(),
                user.getToken(),
                user.isSuccess(),
                user.getFirstName(),
                user.getLastName(),
                user.getDob(),
                user.getSex(),
                user.getCountry(),
                user.getCity(),
                user.getState(),
                user.getStreet(),
                user.getZip(),
                user.getMobile(),
                user.getEmail(),
                twoDecimals(user.getBalance()),
                user.getBalanceCy(),
                user.isActivated(),
                user.getUserCat(),
                user.getKycStatus(),
                user.getErrCode(),
                user.getErrMsg(),
                user.getLocale(),
                authorizeDTO,
                transferDTO,
                cancelDTO,
                verifyUserLogDTO,
                authorizeLogDTO,
                transferLogDTO,
                cancelLogDTO
        );
    }

    public static double twoDecimals(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
