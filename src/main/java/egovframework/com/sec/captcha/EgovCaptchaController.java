package egovframework.com.sec.captcha;

import egovframework.com.sec.captcha.service.EgovCaptchaService;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/captcha")
public class EgovCaptchaController {

  @Autowired
  private EgovCaptchaService service;


  @GetMapping
  public void getCaptcha(HttpServletResponse response) throws IOException {
    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
    service.generateCaptcha();

    final ServletOutputStream out = response.getOutputStream();
    service.writeCaptcha(out);
  }

  @GetMapping("/confirm")
  @ResponseBody
  public String getCaptcha(String captcha) throws EgovBizException {
    return Boolean.toString(service.matches(captcha));
  }

}
