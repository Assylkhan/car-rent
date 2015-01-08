package com.epam.service;

import com.epam.dao.*;
import com.epam.entity.Birth;
import com.epam.entity.Client;
import com.epam.entity.Order;
import com.epam.entity.PassportInfo;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class OrderService {

    public Order insertOrder(HttpServletRequest req) {
        PassportInfo passportBean = createPassportBean(req);
        Order insertedOrder = null;
        DaoManager daoManager = null;
        try {
            DaoFactory factory = H2DaoFactory.getDaoFactory(Database.H2);
            daoManager = factory.getDaoManager();

            // beginnig transaction
            daoManager.beginTransaction();

            Client client = (Client) req.getSession().getAttribute("client");
            ClientDao clientDao = daoManager.getClientDao();
            PassportInfo passportInfo = clientDao.insertPassportInfo(passportBean);
            client.setPassportInfo(passportInfo);
            Order order = new Order();
            order.setClient(client);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            Date pickupDate = new Date(df.parse(req.getParameter("pickupDate")).getTime());
            Date returnDate = new Date(df.parse(req.getParameter("returnDate")).getTime());
            order.setCarId(Long.valueOf(req.getParameter("carId")));
            order.setPickupDate(pickupDate);
            order.setPickupDate(returnDate);
            OrderDao orderDao = daoManager.getOrderDao();
            insertedOrder = orderDao.insert(order);
            daoManager.commit();
        } catch (DaoException e) {
            daoManager.rollback();
        } catch (ParseException e) {
            System.err.println(e);
        } finally {
            daoManager.closeConnection();
        }
        if (insertedOrder == null) return null;
        return insertedOrder;
    }

    public static PassportInfo createPassportBean(HttpServletRequest req) {
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String citizenship = req.getParameter("citizenship");
        String gender = req.getParameter("gender");
        String birthDate = req.getParameter("birthDate");
        String birthPlace = req.getParameter("birthPlace");
        String issuedAgencyName = req.getParameter("issuedAgencyName");
        String issuedDate = req.getParameter("issuedDate");
        String validity = req.getParameter("validity");
        PassportInfo passport = new PassportInfo();
        passport.setSurname(surname);
        passport.setName(name);
        passport.setCitizenship(citizenship);
        passport.setGender(gender);
        Birth birth = null;
        Date parsedIssuedDate = null;
        Date parsedValidity = null;
        try {

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            parsedIssuedDate = new Date(df.parse(issuedDate).getTime());
            parsedValidity = new Date(df.parse(validity).getTime());
            birth = new Birth(new Date(df.parse(birthDate).getTime()), birthPlace);

        } catch (ParseException e) {
            System.err.println(e);
        }
        passport.setBirth(birth);
        passport.setIssuedAgencyName(issuedAgencyName);
        passport.setIssuedDate(parsedIssuedDate);
        passport.setValidity(parsedValidity);
        return passport;
    }
}