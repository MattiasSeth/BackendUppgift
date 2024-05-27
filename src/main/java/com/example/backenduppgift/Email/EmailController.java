package com.example.backenduppgift.Email;

import com.example.backenduppgift.DTO.BlacklistDto;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/template")
public class EmailController {

    //@Autowired
    private final EmailService emailService;

    @RequestMapping("/all")
    public String getTemplate(Model model){
        List<EmailTemplate> templates = emailService.getTemplate();
        model.addAttribute("alltemplates", templates);
        return "showTemplate";
    }
    @RequestMapping("/edit/{id}")
    public String editTemplate(@PathVariable long id, Model model) {
        EmailTemplate template = emailService.getById(id);
        model.addAttribute("template", template);
        model.addAttribute("header", "");
        model.addAttribute("checkInTime", "");
        model.addAttribute("checkOutTime", "");
        model.addAttribute("currency", "");
        return "editTemplateForm";
    }

    @PostMapping("/update")
    public String updateTemplate(@RequestParam long id,
                                 @RequestParam String header,
                                 @RequestParam String checkInTime,
                                 @RequestParam String checkOutTime,
                                 @RequestParam String currency) {
        EmailTemplate template = emailService.getById(id);

        String updatedContent = updateTemplateContent(template.getContent(), header, checkInTime, checkOutTime, currency);
        template.setContent(updatedContent);
        emailService.deleteTemplate(id);
        emailService.saveTemplate(template);

        return "redirect:/template/all";
    }

    private String updateTemplateContent(String content, String header, String checkInTime, String checkOutTime, String currency) {
        content = content.replaceAll("<h2[^>]*id=\"headerId\">.*?</h2>", "<h2 id=\"headerId\">" + header + "</h2>");
        content = content.replaceAll("<span[^>]*id=\"checkInTime\">.*?</span>", "<span id=\"checkInTime\">" + checkInTime + "</span>");
        content = content.replaceAll("<span[^>]*id=\"checkOutTime\">.*?</span>", "<span id=\"checkOutTime\">" + checkOutTime + "</span>");
        content = content.replaceAll("<span[^>]*id=\"currency\">.*?</span>", "<span id=\"currency\">" + currency + "</span>");

        return content;
    }


    /*
    <h2 id="headerId">Booking Details</h2>
    </span> <span id="checkInTime">(from 15:00)</span>
    </span> <span id="checkOutTime">(from 12:00)</span>
    </span> <span id="currency">$</span>


    <p id="message">
    Regards, <br />
    <em id="bookingEmail">Booking@Backend2.com</em>
</p>
     */
}
