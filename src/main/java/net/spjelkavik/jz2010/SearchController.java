package net.spjelkavik.jz2010;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

/**
 * User: hennings
 * Date: 25.aug.2010
 */
@Controller
@EnableAutoConfiguration
@ComponentScan
public class SearchController {

    Logger log = Logger.getLogger(SearchController.class);

    @Autowired
    RiceCookerSearcher cookersDao;

    @RequestMapping(value="/detail/{id}", method = RequestMethod.GET)
    public String getDetail(Model model, @PathVariable int id) {
        log.info("detail / " + id);
        RiceCooker rc = cookersDao.findByPk(id);
        model.addAttribute("detail", rc);
        return "detail";
    }

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String getListOfThings(Model model) {
        List<RiceCooker> r = cookersDao.getAll();
        model.addAttribute("r",r);
        return "list";
    }


/*
    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        //resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
           */

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SearchController.class, args);
    }
}
