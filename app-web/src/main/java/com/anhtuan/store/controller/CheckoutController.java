package com.anhtuan.store.controller;

import com.anhtuan.store.commons.constants.EndPointConst;
import com.anhtuan.store.commons.constants.Messages;
import com.anhtuan.store.commons.constants.ModelViewConst;
import com.anhtuan.store.commons.constants.ViewHtmlConst;
import com.anhtuan.store.config.Principal;
import com.anhtuan.store.dto.request.OrderRqDto;
import com.anhtuan.store.payment.config.PaypalPaymentIntent;
import com.anhtuan.store.payment.config.PaypalPaymentMethod;
import com.anhtuan.store.payment.utils.Utils;
import com.anhtuan.store.service.CartService;
import com.anhtuan.store.service.OrderService;
import com.anhtuan.store.service.impl.PaypalService;
import com.anhtuan.store.support.MessageHelper;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/checkout")
@Slf4j
public class CheckoutController extends BaseController {
    public static final String URL_PAYPAL_SUCCESS = "checkout/process/success";
    public static final String URL_PAYPAL_CANCEL = "process/cancel";
    private static final Integer USD = 23251;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaypalService paypalService;

    @GetMapping
    public String showPage(@AuthenticationPrincipal Principal principal, Model model, HttpSession session) {
        model.addAttribute(ModelViewConst.checkouts.USER_INFO, principal);
        model.addAttribute(ModelViewConst.checkouts.CART_INFO, cartService.getAll(session));
        model.addAttribute(ModelViewConst.checkouts.TOTAL_CART, cartService.totalCart(session));
        return ViewHtmlConst.Checkout.CHECKOUT;
    }

    @PostMapping(value = "/process")
    public String processCheckout(@AuthenticationPrincipal Principal principal,
                                  HttpSession session,
                                  @ModelAttribute(ModelViewConst.checkouts.ORDER_INFO) OrderRqDto orderRqDto,
                                  HttpServletRequest request) {

        String cancelUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
        String successUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;

        try {
            Payment payment = paypalService.createPayment(
                    Double.valueOf(cartService.calculateTotalCart(session)) / Double.valueOf(USD),
                    "USD",
                    PaypalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    "Thanh toan hoa don",
                    cancelUrl,
                    successUrl);
            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    orderService.orderProduct(orderRqDto, principal, session);
                    return "redirect:" + links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }

        return redirect(EndPointConst.Checkouts.CHECKOUT);
    }

    @GetMapping(URL_PAYPAL_CANCEL)
    public String cancelPay() {
        return ViewHtmlConst.Checkout.CHECKOUT;
    }

    @GetMapping(value = "/process/success")
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, RedirectAttributes ra) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                MessageHelper.addSuccessAttribute(ra, Messages.Checkouts.PAYMENT_SUCCESS);
                return redirect(EndPointConst.Checkouts.CHECKOUT);
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        MessageHelper.addSuccessAttribute(ra, Messages.Checkouts.PAYMENT_SUCCESS);
        return redirect(EndPointConst.Checkouts.CHECKOUT);
    }
}
