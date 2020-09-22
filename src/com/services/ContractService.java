package com.services;

import com.entities.Contract;
import com.entities.Installment;

import java.util.Calendar;
import java.util.Date;

public class ContractService {

    OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService){
        this.onlinePaymentService = onlinePaymentService;
    }

    public void processContract(Contract contract, int month){

        double parcel = contract.getTotalValue()/month;

        for(int i = 1; i <= month; i ++){
            Date dueDate = addMonth(contract.getDate(), i);
            double totalValue = parcel + onlinePaymentService.interest(parcel,i);
                   totalValue = totalValue + onlinePaymentService.paymentFee(totalValue);

            contract.getInstallments().add(new Installment(dueDate, totalValue));
        }

    }

    public Date addMonth(Date date, int month){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,month);
        return calendar.getTime();

    }
}
