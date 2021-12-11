package org.academiadecodigo.soundpark.controller.web;

import org.academiadecodigo.soundpark.persistence.dao.PlayerDao;
import org.academiadecodigo.soundpark.persistence.model.Player;
import org.academiadecodigo.soundpark.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/player")
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     * Adds a customer
     *
     * @param model the model object
     * @return the view to render
     */
    @RequestMapping(method = RequestMethod.GET, path = "/add")
    public String addPlayer(Model model) {
        model.addAttribute("player", new Player());
        return "player/add-update";
    }

    /**
     * Renders a view with customer details
     *
     * @param id    the customer id
     * @param model the model object
     * @return the view to render
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String showCustomer(@PathVariable Integer id, Model model) throws Exception {

        Player player = playerService.get(id);

        // command objects for customer show view
        model.addAttribute("customer", player);

        return "player/show";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/edit")
    public String editCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute("player", playerService.get(id));
        return "player/add-update";
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""}, params = "action=save")
    public String saveCustomer(@Valid @ModelAttribute("player") Player player, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "player/add-update";
        }

        Player savedPlayer = playerService.save(player);

        redirectAttributes.addFlashAttribute("lastAction", "Saved " + savedPlayer.getUserName());
        return "redirect:/customer/" + savedPlayer.getId();
    }

}
