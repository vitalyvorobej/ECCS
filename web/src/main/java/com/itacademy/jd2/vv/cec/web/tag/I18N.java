package com.itacademy.jd2.vv.cec.web.tag;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.itacademy.jd2.vv.cec.web.util.XMLResourceBundleControl;

public class I18N extends SimpleTagSupport {
	public static final String SESSION_LOCALE_KEY = "current-locale";

	private final Locale DEFAULT_LOCALE = new Locale("en");
	private String key;

	@Override
	public void doTag() throws JspException, IOException {

		final JspContext jspContext = getJspContext();

		Locale locale = (Locale) jspContext.findAttribute(SESSION_LOCALE_KEY);
		if (locale == null) {
			locale = DEFAULT_LOCALE;
		}

		jspContext.getOut().println(getLocalized(key, locale));
	}

	private String getLocalized(final String key, final Locale locale) {
		// FIXME: do not load bundle every time - use cached (simple hashmap)
		final ResourceBundle bundle = ResourceBundle.getBundle("i18n/translations", locale, new XMLResourceBundleControl());
		return bundle.getString(key);
	}

	public void setKey(final String key) {
		this.key = key;
	}

}