package net.spjelkavik.jz2010;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * User: hennings
 * Date: 25.aug.2010
 */
@Controller
public class SearchController {

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping(value="/detail/{id}", method = RequestMethod.GET)
    public String getDetail(Model model, @PathVariable int id) {

        RiceCookerSearcher cookersDao = applicationContext.getBean(RiceCookerSearcher.class);
        RiceCooker rc = cookersDao.findByPk(id);
        model.addAttribute("detail", rc);
        return "detail";
    }

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String getListOfThings(Model model) {
        RiceCookerSearcher cookersDao = applicationContext.getBean(RiceCookerSearcher.class);
        List<RiceCooker> r = cookersDao.getAll();
        model.addAttribute("r",r);
        return "list";
    }
}
