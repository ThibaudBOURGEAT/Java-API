package fr.ynov.dap.dap.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.ynov.dap.dap.google.GoogleAccountService;
import fr.ynov.dap.dap.microsoft.OutlookMailService;

@Controller
@RequestMapping("/outlook")
public class OutlookController {
	
	@Autowired
	OutlookMailService outlookMailService;

	private final Logger LOG = LogManager.getLogger(GoogleAccountService.class);

	@RequestMapping("/mails")
	public String getMailForAllAccounts(@RequestParam("userKey") final String userKey,
			ModelMap model) {
		model.put("users",
				outlookMailService.getMailForAllAccounts(userKey)
				.get("users"));
		model.put("messages",
				outlookMailService.getMailForAllAccounts(userKey)
				.get("messages"));

		return "mail";
	}
}
