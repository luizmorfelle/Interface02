package com.company;

import com.entities.Contract;
import com.entities.Installment;
import com.services.ContractService;
import com.services.PayPalService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);


        System.out.println("Enter contract Data: ");

        System.out.print("Number: ");
        Integer numberContract = sc.nextInt();

        System.out.print("Date (DD/MM/YYYY): ");
        sc.nextLine();
        Date dateContract = sdf.parse(sc.nextLine());

        System.out.print("Contract Value: ");
        double valueContract = sc.nextDouble();

        Contract contract = new Contract(numberContract, dateContract, valueContract);

        System.out.print("Enter number of installments: ");
        int numberInstalments = sc.nextInt();

        ContractService contractService = new ContractService(new PayPalService());
        contractService.processContract(contract,numberInstalments);
        for (Installment inst : contract.getInstallments()) System.out.println(inst.toString());

        sc.close();
    }
}
