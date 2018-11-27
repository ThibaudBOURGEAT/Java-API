package fr.ynov.dap.dap.web;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.ynov.dap.dap.auth.AuthHelper;
import fr.ynov.dap.dap.data.Token;
import fr.ynov.dap.dap.google.GoogleAccountService;
import fr.ynov.dap.dap.microsoft.OutlookService;
import fr.ynov.dap.dap.microsoft.OutlookServiceBuilder;
import fr.ynov.dap.dap.model.IdToken;
import fr.ynov.dap.dap.model.OutlookUser;

@Controller
public class AuthorizeController {

	private final Logger LOG = LogManager.getLogger(GoogleAccountService.class);
	
	/*@RequestMapping(value = "/authorize", method = RequestMethod.POST)
	public String authorize(@RequestParam("code") String code, @RequestParam("id_token") String idToken,
			@RequestParam("state") UUID state, HttpServletRequest request) {
		{
			// Get the expected state value from the session
			HttpSession session = request.getSession();
			UUID expectedState = (UUID) session.getAttribute("expected_state");
			UUID expectedNonce = (UUID) session.getAttribute("expected_nonce");

			// Make sure that the state query parameter returned matches
			// the expected state
			if (state.equals(expectedState)) {
				IdToken idTokenObj = IdToken.parseEncodedToken(idToken, expectedNonce.toString());
				if (idTokenObj != null) {
					TokenResponse tokenResponse = AuthHelper.getTokenFromAuthCode(code, idTokenObj.getTenantId());
					session.setAttribute("tokens", tokenResponse);
					session.setAttribute("userConnected", true);
					session.setAttribute("userName", idTokenObj.getName());

					OutlookService outlookService = OutlookServiceBuilder
							.getOutlookService(tokenResponse.getAccessToken(), null);
					OutlookUser user;
					try {
						user = outlookService.getCurrentUser().execute().body();
						session.setAttribute("userEmail", user.getMail());
					} catch (IOException e) {
						session.setAttribute("error", e.getMessage());
					}
						
					session.setAttribute("userTenantId", idTokenObj.getTenantId());
				} else {
					session.setAttribute("error", "ID token failed validation.");
				}
			} else {
				session.setAttribute("error", "Unexpected state returned from authority.");
			}
			return "redirect:/microsoft/mails";
		}
	}*/

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/index";
	}
}