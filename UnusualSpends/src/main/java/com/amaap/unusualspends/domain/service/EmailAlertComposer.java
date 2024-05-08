package com.amaap.unusualspends.domain.service;

import com.amaap.unusualspends.domain.service.dto.SpendDto;

import java.util.List;

public class EmailAlertComposer {
    public String composeEmail(String name, List<SpendDto> record) {
        StringBuilder body = new StringBuilder(" \n hello " + name + "!\n We have detected unusually high spending on your card in these categories:\n ");
        double totalUnusualSpend = 0;
        double totalUsualSpend = 0;
        for (SpendDto spend : record) {
            body.append(" * You spent " + (spend.getUnusualAmountSpend() + spend.getUsualAmountSpend()) + " on " + spend.getCategory() + "\n");
            totalUsualSpend += spend.getUnusualAmountSpend();
            totalUnusualSpend += spend.getUsualAmountSpend();
        }
        body.append("Thanks,\n" +
                "\n" +
                "The Credit Card Company\n");
        body.insert(0, "Total Unusual spending of " + totalUnusualSpend + " detected!\nYour usual spending of last month was " + totalUsualSpend);
        return body.toString();
    }
}
