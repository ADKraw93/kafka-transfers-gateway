package com.kodilla.transfersgateway.controller;

import com.kodilla.commons.Transfer;
import com.kodilla.transfersgateway.controller.request.TransferRequest;
import com.kodilla.transfersgateway.domain.Account;
import com.kodilla.transfersgateway.service.AccountsGenerator;
import com.kodilla.transfersgateway.service.TransferProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferProducer transferProducer;
    private final AccountsGenerator generator;

    @PostMapping
    public void saveTransfer(@RequestBody TransferRequest request) throws Exception {
        List<Account> accountList = generator.generateAccounts();
        log.info("Received transfer request: {}", request);
        Account account = accountList.stream().filter(a -> a.getAccountNumber().equals(request.getSenderAccount())).findAny().orElse(new Account());
        if(account.getSaldo().compareTo(request.getAmount())==-1) {
            throw new Exception("Not enough money on the account.");
        } else {
            Transfer transfer = new Transfer();
            transfer.setAmount(request.getAmount());
            transfer.setRecipientAccount(request.getRecipientAccount());
            transfer.setTitle(request.getTitle());
            transfer.setSenderAccount(request.getSenderAccount());

            transferProducer.sendTransfer(transfer);
        }
    }
}
