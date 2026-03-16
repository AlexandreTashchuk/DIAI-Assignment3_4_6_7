package pt.unl.fct.iadi.novaevents.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import pt.unl.fct.iadi.novaevents.model.Event
import pt.unl.fct.iadi.novaevents.service.NovaeventsService
import java.time.LocalDate

@Controller
class NovaeventsController (val service: NovaeventsService) : NovaeventsAPI {

    override fun listAllClubs(model: Model): String {
        model.addAttribute("clubs", service.listAllClubs())
        return "clubs/list"
    }

    override fun getClubDetail(id: Long, model: Model): String {

        val club = service.getClubById(id)
        val events = service.getEventsForClub(id)

        model.addAttribute("club", club)
        model.addAttribute("events", events)

        return "clubs/detail"
    }

    override fun listEvents(
        type: Event.EventType?,
        clubId: Long?,
        from: LocalDate?,
        to: LocalDate?,
        model: Model
    ): String {

        val events = service.filterEvents(type, clubId, from, to)

        model.addAttribute("events", events)
        model.addAttribute("clubs", service.listAllClubs())
        model.addAttribute("types", Event.EventType.values())
        //model.addAttribute("types", Event.EventType.entries.toTypedArray())

        return "events/list"
    }
}