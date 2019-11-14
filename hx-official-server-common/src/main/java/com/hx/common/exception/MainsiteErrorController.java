package com.hx.common.exception;

import com.hx.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class MainsiteErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @Autowired
    ErrorAttributes errorAttributes;

    @RequestMapping(
            value = {ERROR_PATH},
            produces = {"application/json"}
    )
    public R errorHtml(HttpServletRequest request, HttpServletResponse response) {
        int code = response.getStatus();
        if (R.NOT_FOUND_RESOURCE_CODE == code) {
            return R.error(R.NOT_FOUND_RESOURCE_CODE,"404");
        } else if (R.UNAUTHORIZED_CODE == code) {
            return R.error(R.UNAUTHORIZED_CODE,"403");
        } else if (R.NOT_LOGIN_CODE == code) {
            return R.error(R.NOT_LOGIN_CODE,"401");
        } else {
            return R.error(R.ERROR_CODE,"500");
        }

    }

//    @RequestMapping(value = ERROR_PATH)
//    public R handleError(HttpServletRequest request, HttpServletResponse response) {
////        response.setStatus(200);
//        int code = response.getStatus();
//        if (404 == code) {
//            return R.error(404, "404");
//        } else if (403 == code) {
//            return R.error(403, "403");
//        } else if (401 == code) {
//            return R.error(401, "401");
//        } else {
//            return R.error(500, "500");
//        }
//    }

    @Override
    public String getErrorPath() {
        // TODO Auto-generated method stub
        return ERROR_PATH;
    }
}