package com.epam.action;

import com.epam.entity.Order;
import com.epam.service.OrderService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DoOrderAction implements Action {
    private ActionResult result = new ActionResult("home", true);
    private ActionResult failed = new ActionResult("doOrder");
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        OrderService service = new OrderService();
        Order order = service.insertOrder(req);
        if (order == null) {
            req.setAttribute("orderResponse", "sending order failed");
            return failed;
        }
        req.setAttribute("orderResponse", "order has been sent successfully");
        return result;
    }
}
