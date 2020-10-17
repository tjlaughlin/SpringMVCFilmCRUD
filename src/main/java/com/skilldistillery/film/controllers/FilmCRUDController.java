package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.dao.FilmDAO;

@Controller
public class FilmCRUDController {

	@Autowired
	private FilmDAO filmDAO;

	@RequestMapping(path = "index.do", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		return mv;
	}

	@RequestMapping(path = "Films.do", method = RequestMethod.GET, params = "filmId")
	public ModelAndView getById(int filmId) {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("result");
		mv.addObject("film", filmDAO.findFilmById(filmId));
		return mv;
	}

}
