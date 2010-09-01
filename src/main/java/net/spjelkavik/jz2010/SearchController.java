package net.spjelkavik.jz2010;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hennings
 * Date: 25.aug.2010
 */
@Controller
public class SearchController {

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String getListOfThings(Model model) {
        List<RiceCooker> r = new ArrayList<RiceCooker>();
        r.add(new RiceCooker(1, "Tefal Simple", 100));
        r.add(new RiceCooker(2, "Tefal Medium", 300));
        r.add(new RiceCooker(3, "Tefal Plus", 500));
        r.add(new RiceCooker(4, "Tefal Platinum", 1000));
        model.addAttribute("r", r);
        return "list";
    }
}
