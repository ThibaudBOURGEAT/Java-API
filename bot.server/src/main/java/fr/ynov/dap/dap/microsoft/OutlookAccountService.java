package fr.ynov.dap.dap.microsoft;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ynov.dap.dap.auth.AuthHelper;
import fr.ynov.dap.dap.data.AppUser;
import fr.ynov.dap.dap.data.OutlookAccount;
import fr.ynov.dap.dap.data.Token;
import fr.ynov.dap.dap.model.IdToken;
import fr.ynov.dap.dap.repository.AppUserRepository;
import fr.ynov.dap.dap.repository.OutlookAccountRepository;

@Service
public class OutlookAccountService {
	
	@Autowired
	private OutlookAccountRepository outlookAccountRepository;

	@Autowired
	private AppUserRepository appUserRepo;

	public String addAccount(final String accountName, final String userKey,
			final HttpServletRequest request,
			HttpSession session) {
		UUID state = UUID.randomUUID();
		UUID nonce = UUID.randomUUID();

		session = request.getSession();
		session.setAttribute("expected_state", state);
		session.setAttribute("expected_nonce", nonce);
		session.setAttribute("accountName", accountName);
		session.setAttribute("userKey", userKey);
		
		String loginUrl = AuthHelper.getLoginUrl(state, nonce);
		
		return "redirect:" + loginUrl;
	}

	public String authorize(HttpSession session, UUID state, String idToken,
			String code, String accountName,
			String userKey) {

		UUID expectedState = (UUID) session.getAttribute("expected_state");
		UUID expectedNonce = (UUID) session.getAttribute("expected_nonce");
		// Make sure that the state query parameter returned matches
		// the expected state
		if (state.equals(expectedState)) {
			IdToken idTokenObj = IdToken.parseEncodedToken(idToken, expectedNonce.toString());
			if (idTokenObj != null) {
				Token token = AuthHelper.getTokenFromAuthCode(code, idTokenObj.getTenantId());
				
				if(outlookAccountRepository.findByName(accountName) == null) {
					OutlookAccount outlookAccount = new OutlookAccount();
					AppUser appUser = appUserRepo.findByName(userKey);
					outlookAccount.setName(accountName);
					outlookAccount.setTenantId(idTokenObj.getTenantId());
					outlookAccount.addToken(token);
					appUser.addMicrosoftAccount(outlookAccount);
					outlookAccountRepository.save(outlookAccount);
				}else {
					session.setAttribute("error", "Account already exist.");
				}

			} else {
				session.setAttribute("error", "ID token failed validation.");
			}
		} else {
			session.setAttribute("error", "Unexpected state returned from authority.");
		}
		return "redirect:/microsoft/mails/" + userKey;
	}
}
