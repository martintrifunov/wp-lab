package mk.finki.ukim.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.wp.lab.model.TicketOrder;
import mk.finki.ukim.wp.lab.service.MovieService;
import mk.finki.ukim.wp.lab.service.TicketOrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name="ticketOrder", urlPatterns = "/servlet/ticketOrder")
public class TicketOrderServlet extends HttpServlet {
    private final TicketOrderService ticketOrderService;
    private final SpringTemplateEngine springTemplateEngine;

    public TicketOrderServlet(TicketOrderService ticketOrderService, SpringTemplateEngine springTemplateEngine) {
        this.ticketOrderService = ticketOrderService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("ticketOrder", (TicketOrder)req.getSession().getAttribute("ticketOrder"));

        this.springTemplateEngine.process("orderConfirmation.html", context, resp.getWriter());
    }
}
