package com.myhandler.mvc;

import com.myhandler.beans.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by anatoliy on 23.08.14.
 */
@Controller
@RequestMapping("/handle")
public class HandlerController {

    private static Logger logger = Logger.getLogger("com.myhandler.mvc.HandlerController");

    @Autowired
    private Bank bank;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String randomTransfer(){
        int toAccount = (int) (bank.size() * Math.random()) + 1;
        int fromAccount = (int) (bank.size() * Math.random()) +1;
        int amount = (int) (1000 * Math.random());
        bank.transfer(fromAccount, toAccount, amount);
        String msg = "From " + fromAccount + " to " + toAccount + " amount = " + amount +"; Total balance = " + bank.getTotalBalance();
        //logger.log(Level.INFO, msg);
        //System.out.println(msg);
        return msg;
    }
}
