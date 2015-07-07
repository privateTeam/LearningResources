package org.beadle.framework.servlet;

import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

public class MainServlet extends DispatcherServlet{

	@Override
	protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LocaleResolver localeResolver = (LocaleResolver)request.getAttribute(LOCALE_RESOLVER_ATTRIBUTE);
		// Determine locale for request and apply it to the response.
		Locale locale = localeResolver.resolveLocale(request);
		response.setLocale(locale);

		View view;
		
		Method method = mv.getClass().getDeclaredMethod("getModelInternal");
		method.setAccessible(true);
		Map<String, Object> paramMap = (Map<String, Object>)method.invoke(mv);
		method.setAccessible(false);
		if (mv.isReference()) {
			// We need to resolve the view name.
			request.setAttribute("ModelAndView", mv);
			view = resolveViewName(mv.getViewName(), paramMap, locale, request);
			if (view == null) {
				throw new ServletException("Could not resolve view with name '" + mv.getViewName() +
						"' in servlet with name '" + getServletName() + "'");
			}
		}
		else {
			// No need to lookup: the ModelAndView object contains the actual View object.
			view = mv.getView();
			if (view == null) {
				throw new ServletException("ModelAndView [" + mv + "] neither contains a view name nor a " +
						"View object in servlet with name '" + getServletName() + "'");
			}
		}

		// Delegate to the View object for rendering.
		if (logger.isDebugEnabled()) {
			logger.debug("Rendering view [" + view + "] in DispatcherServlet with name '" + getServletName() + "'");
		}
		try {
			view.render(paramMap, request, response);
		}
		catch (Exception ex) {
			if (logger.isDebugEnabled()) {
				logger.debug("Error rendering view [" + view + "] in DispatcherServlet with name '" +
						getServletName() + "'", ex);
			}
			throw ex;
		}
	}
}
