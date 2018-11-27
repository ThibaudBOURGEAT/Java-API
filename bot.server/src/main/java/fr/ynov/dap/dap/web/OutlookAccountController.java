package fr.ynov.dap.dap.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.ynov.dap.dap.auth.AuthHelper;
import fr.ynov.dap.dap.google.GoogleAccountService;
import fr.ynov.dap.dap.microsoft.OutlookAccountService;
import fr.ynov.dap.dap.repository.GoogleAccountRepository;

import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OutlookAccountController {

	@Autowired
	private OutlookAccountService microsoftAccountService;

	private final Logger LOG = LogManager.getLogger(GoogleAccountService.class);

	@RequestMapping("/account/add/microsoft/{accountName}")
	public String addAccount(@PathVariable("accountName") final String accountName,
			@RequestParam("userKey") final String userKey, final HttpServletRequest request,
			final HttpSession session) {

		return microsoftAccountService.addAccount(accountName, userKey, request, session);
	}

	@RequestMapping(value = "/authorize", method = RequestMethod.POST)
	public String authorize(@RequestParam("code") String code, @RequestParam("id_token") String idToken,
			@RequestParam("state") UUID state, HttpServletRequest request) throws ServletException {

		HttpSession session = request.getSession();

		final String accountName = getUserAccount(session);
		final String userKey = getUserKey(session);

		return microsoftAccountService.authorize(session, state, idToken, 
				code, accountName, userKey);
	}

	private String getUserAccount(final HttpSession session) throws ServletException {
		String accountName = null;
		if (null != session && null != session.getAttribute("accountName")) {
			accountName = (String) session.getAttribute("accountName");
		}

		if (null == accountName) {
			LOG.error("accountName in Session is NULL in Callback");
			throw new ServletException("Error when trying to add Google acocunt : userId is NULL is User Session");
		}
		return accountName;
	}

	private String getUserKey(final HttpSession session) throws ServletException {
		String userKey = null;
		if (null != session && null != session.getAttribute("userKey")) {
			userKey = (String) session.getAttribute("userKey");
		}

		if (null == userKey) {
			LOG.error("userId in Session is NULL in Callback");
			throw new ServletException("Error when trying to add Google acocunt : userId is NULL is User Session");
		}
		return userKey;
	}
}