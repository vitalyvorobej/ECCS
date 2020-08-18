package com.itacademy.jd2.vv.cec.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Random;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-context.xml")
public abstract class AbstractTest {
    @Autowired
    private IUserAccountService userAccountService;
    @Autowired
    private IClientService clientService;
    @Autowired
    private ICardService cardService;
    @Autowired
    private IOrderObjectService orderObjectService;
    @Autowired
    private IPaymentService paymentService;
    @Autowired
    private IBraceletService braceletService;
    @Autowired
    private ITicketTypeService ticketTypeService;
    private static final Random RANDOM = new Random();

    protected String getRandomPrefix() {
        return RANDOM.nextInt(99999) + "";
    }

    public Double getRandomDouble() {
        double templateDouble = RANDOM.nextDouble();

        double newDouble = new BigDecimal(templateDouble).setScale(3, RoundingMode.UP).doubleValue();

        return newDouble;
    }

    protected int getRandomObjectsCount() {
        return RANDOM.nextInt(9) + 1;
    }

    public ITicketTypeService getTicketTypeService() {
        return ticketTypeService;
    }

    public IPaymentService getPaymentService() {
        return paymentService;
    }

    public IBraceletService getBraceletService() {
        return braceletService;
    }

    public IOrderObjectService getOrderObjectService() {
        return orderObjectService;
    }

    public IUserAccountService getUserAccountService() {
        return userAccountService;
    }

    public IClientService getClientService() {
        return clientService;
    }

    // херню написал, спросить как лучше сделать
    protected Boolean getIsFree() {
        return true;

    }

    public ICardService getCardService() {
        return cardService;
    }

    public Random getRANDOM() {
        return RANDOM;
    }

    protected Date getRandomDate() {
        final long randDay = RANDOM.nextInt(17600);
        final long datelong = Math.abs(System.currentTimeMillis() - randDay * 24 * 60 * 1000);
        final Date date = new Date(datelong);
        return date;

    }

}
