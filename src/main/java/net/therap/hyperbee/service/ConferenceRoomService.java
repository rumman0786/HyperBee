package net.therap.hyperbee.service;

import net.therap.hyperbee.domain.ConferenceRoom;
import net.therap.hyperbee.domain.Notice;

import java.util.List;

/**
 * @author rumman
 * @since 11/27/16
 */
public interface ConferenceRoomService {

    void saveConferenceRoom(ConferenceRoom conferenceRoom);

    ConferenceRoom findConferenceRoomById(int conferenceRoomId);

    List<ConferenceRoom> findAllConferenceRoom();

    void delete(int conferenceRoomId);

}