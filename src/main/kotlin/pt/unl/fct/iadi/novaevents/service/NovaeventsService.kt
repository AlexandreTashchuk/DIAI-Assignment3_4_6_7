package pt.unl.fct.iadi.novaevents.service

import org.springframework.stereotype.Service
import pt.unl.fct.iadi.novaevents.model.Club
import pt.unl.fct.iadi.novaevents.model.Event
import java.time.LocalDate

@Service
class NovaeventsService {

    private val clubs = listOf<Club>(
        Club(
            1,
            "Chess Club",
            "The Chess Club description",
            Club.ClubCategory.SPORTS
        ),
        Club(
            2,
            "Robotics Club",
            "The Robotics Club is the place to turn ideas into machines",
            Club.ClubCategory.TECHNOLOGY
        ),
        Club(
            3,
            "Photography Club",
            "The Photography Club description",
            Club.ClubCategory.SOCIAL
        ),
        Club(
            4,
            "Hiking & Outdoors Club",
            "The Hiking Club description",
            Club.ClubCategory.SOCIAL
        ),
        Club(
            5,
            "Film Society",
            "The Film Society description",
            Club.ClubCategory.CULTURAL
        )
    )

    private val clubMap = clubs.associateBy { it.id }
    private val events: MutableList<Event> = mutableListOf()

    fun listAllClubs(): List<Club> {
        return clubs
    }

    fun getClubById(id: Long): Club {
        return clubMap[id] ?: throw ClubNotFoundException(id)
    }

    fun getEventsForClub(clubId: Long): List<Event> {
        return events.filter { it.clubId == clubId }
    }

    fun filterEvents(
        type: Event.EventType?,
        clubId: Long?,
        from: LocalDate?,
        to: LocalDate?
    ): List<Event> {

        return events.filter { event ->

            (type == null || event.type == type) &&
                    (clubId == null || event.clubId == clubId) &&
                    (from == null || !event.date.isBefore(from)) &&
                    (to == null || !event.date.isAfter(to))

        }
    }
}