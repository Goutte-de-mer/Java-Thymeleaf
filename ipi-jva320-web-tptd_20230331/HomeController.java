package com.ipi.jva320.controller;

import com.ipi.jva320.exception.SalarieException;
import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.repository.SalarieAideADomicileRepository;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private SalarieAideADomicileService salarieAideADomicileService;

    @Autowired
    private SalarieAideADomicileRepository salarieAideADomicileRepository;

    @GetMapping(value = "/")
    public String home(ModelMap model) {
        model.put("salarieCount", salarieAideADomicileService.countSalaries());
        return "home";
    }

    @GetMapping(value = "/salaries/{id}")
    public String salarie(ModelMap model, @PathVariable Long id) {
        model.put("salarie", salarieAideADomicileService.getSalarie(id));
        return "detail_Salarie";
    }

    @GetMapping(value = "/salaries/aide/new")
    public String newSalarie(ModelMap model) {
        return "detail_Salarie";
    }

    @PostMapping(value = "/salaries/save")
    public String createSalarie(SalarieAideADomicile salarie) throws SalarieException {
        salarieAideADomicileService.creerSalarieAideADomicile(salarie);
        return "redirect:/salaries/" + salarie.getId();
    }

    @PostMapping(value = "/salaries/{id}")
    public String updateSalarie(SalarieAideADomicile salarie) throws SalarieException {
        salarieAideADomicileService.updateSalarieAideADomicile(salarie);
        return "redirect:/salaries/" + salarie.getId();
    }

    @GetMapping(value = "/salaries")
    public String getSalaries(ModelMap model) {
        model.put("salaries", salarieAideADomicileService.getSalaries());
        return "list";
    }

    // Supprimer salarié
    @GetMapping(value = "/salaries/{id}/delete")
    public String deleteSalarie(@PathVariable("id") Long id) {
        salarieAideADomicileRepository.deleteById(id);

        return "redirect:/salaries";
    }

    // Rechercher salarié

    @RequestMapping(value = "/salarie/findSalarie")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView findSalarie(@RequestParam("nom") String nom) {
        ModelAndView modelAndView = new ModelAndView("list");
        List<SalarieAideADomicile> salarieList = salarieAideADomicileService.getSalaries(nom);

        if(salarieList.isEmpty()){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun salarié avec le nom \"" + nom + "\" trouvé");

        }
        else{
            modelAndView.addObject("salaries", salarieList);
            modelAndView.addObject("salarieCount", salarieList.size());
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/salaries/new"})
    public ModelAndView addSalarie(){
        ModelAndView salarie = new ModelAndView("new_Salarie");
        SalarieAideADomicile salarieAideADomicile = new SalarieAideADomicile();
        salarie.addObject("salarieCount", salarieAideADomicileService.countSalaries());
        salarie.addObject("salarie",salarieAideADomicile);

        return salarie;
    }





}


