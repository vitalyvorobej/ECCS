package com.itacademy.jd2.vv.cec.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itacademy.jd2.vv.cec.web.tag.I18N;

@Controller
@RequestMapping(value = "/")
public class DefaultController {

	private static final Locale LOCALE_RU = new Locale("ru");
	private static final Locale LOCALE_EN = new Locale("en");

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(final HttpServletRequest req, @RequestParam(name = "language", required = false) final String lang) {
		if (lang != null) {
			if ("ru".equals(lang)) {
				req.getSession().setAttribute(I18N.SESSION_LOCALE_KEY, LOCALE_RU);
			} else {
				req.getSession().setAttribute(I18N.SESSION_LOCALE_KEY, LOCALE_EN);
			}
		}

		return "index";
	}

}
