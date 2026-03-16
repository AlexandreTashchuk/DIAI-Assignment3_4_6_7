package pt.unl.fct.iadi.novaevents.controller

import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import pt.unl.fct.iadi.novaevents.model.Event
import java.time.LocalDate

interface NovaeventsAPI {

    @RequestMapping(
        value = ["/clubs"],
        method = [RequestMethod.GET]
    )
    fun listAllClubs(model: Model): String

    @RequestMapping(
        value = ["/clubs/{id}"],
        method = [RequestMethod.GET]
    )
    fun getClubDetail(@PathVariable id: Long, model: Model): String

    @RequestMapping(
        value = ["/events"],
        method = [RequestMethod.GET]
    )
    fun listEvents(
        @RequestParam(required = false) type: Event.EventType?,
        @RequestParam(required = false) clubId: Long?,
        @RequestParam(required = false) from: LocalDate?,
        @RequestParam(required = false) to: LocalDate?,
        model: Model
    ): String
}