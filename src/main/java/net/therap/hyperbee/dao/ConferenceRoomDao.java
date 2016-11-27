package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.ConferenceRoom;

import java.util.List;

/**
 * @author rumman
 * @since 11/27/16
 */
public interface ConferenceRoomDao {

    void save(ConferenceRoom conferenceRoom);

    ConferenceRoom findById(int conferenceRoomId);

    List<ConferenceRoom> findAll();

    void delete(int conferenceRoomId);

}
