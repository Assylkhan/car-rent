package com.epam.servlet;

import com.epam.dao.*;
import com.epam.entity.Car;
import com.epam.pool.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ResourceBundle;

public class ImageServlet extends HttpServlet {
    ResourceBundle resource = ResourceBundle.getBundle("database");
    String url = resource.getString("url");
    String driver = resource.getString("driver");
    String dbUser = resource.getString("user");
    String pass = resource.getString("password");

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long carId = Long.parseLong(req.getPathInfo().substring(1));

        if (carId == null) return;

        DaoFactory factory = DaoFactory.getDaoFacroty(Database.H2);
        if (factory instanceof H2DaoFactory){
            H2DaoFactory h2DaoFactory = (H2DaoFactory) factory;
//            h2DaoFactory.setConnectionPool(ConnectionPool.getInstance(driver, url, dbUser, pass, 10));
            try {
                h2DaoFactory.createConnection();
            } catch (DaoException e) {
                throw new ServletException(e);
            }
        }
        CarDao carDao = null;
        Car car = null;
        try {
            carDao = factory.getCarDao();
            car = carDao.findById(carId);
        } catch (DaoException e) {
            throw new ServletException(e);
        }

        String imagePath = car.getImagePath();
        if (imagePath == null){
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        imagePath = new String(imagePath.getBytes("ISO-8859-1"),"UTF-8");


        String mimeType = req.getServletContext().getMimeType(imagePath);
        if (mimeType == null) {
            resp.sendError(HttpServletResponse.SC_NO_CONTENT);
            return;
        }

        resp.setContentType(mimeType);

        ServletOutputStream out = resp.getOutputStream();
        File file = new File(imagePath);
        FileInputStream in = new FileInputStream(file);

        byte[] buf = new byte[1024];
        int length = 0;
        while ((length = in.read(buf)) >= 0){
            out.write(buf);
        }
        out.close();
        in.close();
    }
}