package com.example.PIQResponseMock.dto;

import com.example.PIQResponseMock.responses.Attributes;
import lombok.Data;

@Data
public class CancelDTO {
    // Required fields
    String userId; // User's unique id, which can be a number or a username. Max 50 char.
    String authCode; // Authorization code from authorize response.
    String txAmount; // Amount credited or debited user's account, formatted like 100.50.
    String txAmountCy; // 'txAmount' currency, 3-letter code according to ISO standard, e.g SEK.
    String txId; // PaymentIQ unique transaction-id.
    String txTypeId; // Transaction type, e.g 101 = CreditcardDeposit.
    String txName; // Transaction name, e.g CreditcardDeposit.
    String provider; // Provider that processed the transaction, e.g. Neteller.

    // Optional fields
    String originTxId; // Optional reference to the origin PaymentIQ transaction.
    String accountId; // Optional ref to PaymentIQ account-id.
    String accountHolder; // Optional name of the card holder or bank account holder.
    String maskedAccount; // Optional masked account.
    String statusCode; // Optional PaymentIQ specific status code.
    String pspStatusCode; // Optional PSP specific status code.
    String pspFee; // Expected fee from the provider.
    String pspFeeCy; // PSP fee currency.
    String pspFeeBase; // Expected fee from the provider in the base currency.
    String pspFeeBaseCy; // Currency for the fee in the base currency.
    String pspRefId; // The reference ID used by the Provider for the transaction.
    String pspStatusMessage; // The status message returned from the PSP provider.
    String attributes; // Echoing back the optional parameter sent in the payment request by merchant.

}
