package com.epam.controller.web.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.model.language.Language;

public class LocaleFilter extends BaseFilter {

	private String language;

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		language = fConfig.getInitParameter("language");

		if (language == null)
			language = "en_US";
	}

	@Override
	public void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession session = request.getSession(true);

		if (session.getAttribute("sessionLocale") == null) {
			session.setAttribute("sessionLocale", request.getLocale());
		} else if ((session.getAttribute("sessionLocale").toString().equals(""))) {
			session.setAttribute("sessionLocale", language);
		}

		session.setAttribute("currLanguage", Language.findByLocale(session
				.getAttribute("sessionLocale").toString()));
		session.setAttribute("languages", Language.getLanguages());
		chain.doFilter(request, response);

	}
}
