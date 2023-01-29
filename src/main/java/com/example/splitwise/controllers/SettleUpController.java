package com.example.splitwise.controllers;

import com.example.splitwise.dtos.SettleUpGroupRequestDTO;
import com.example.splitwise.dtos.SettleUpGroupResponseDTO;
import com.example.splitwise.services.settleup.SettleUpService;
import com.example.splitwise.services.settleup.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SettleUpController {

    @Autowired
    private SettleUpService settleUpService;

    public SettleUpGroupResponseDTO settleUp(SettleUpGroupRequestDTO requestDTO)
    {
        List<Transaction> transactionList = settleUpService.settleUpGroup(requestDTO.getGroupId());

        SettleUpGroupResponseDTO settleUpGroupResponseDTO = new SettleUpGroupResponseDTO();
        settleUpGroupResponseDTO.setTransactions(transactionList);
        return settleUpGroupResponseDTO;
    }
}
