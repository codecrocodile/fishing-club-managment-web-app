package com.codecrocodile.club.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codecrocodile.club.model.catchrecord.CatchRecord;
import com.codecrocodile.club.model.external.Weather;

@Service("WeatherDao")
@Repository
public class WeatherDao {
	
    @PersistenceContext // you can also add the unit name if there is more than one persistence context
    private EntityManager em;
	
	public Weather getWeather(long venueId, Date date) {
		System.out.println("get weather");
    	String qry = "SELECT w FROM Weather w WHERE w.venueId = :venueId AND w.date = :date";
    	TypedQuery<Weather> query = em.createQuery(qry, Weather.class)
    			.setParameter("venueId", venueId)
    	        .setParameter("date", date, TemporalType.DATE);
    	try {
    		return query.getSingleResult();
		} catch (NoResultException e) { }
    	
    	return null;
	}
	
	@Transactional
	public void saveWeather(Weather weather) {
    	if (weather.getWeatherId() < 1) {
    		em.persist(weather);
    	} else {
    		em.merge(weather);
    	}
	}

}
