package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.film.dao.FilmDAO;
import com.skilldistillery.film.entities.Film;

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

	@RequestMapping(path = "FilmsList.do", method = RequestMethod.GET)
	public ModelAndView getByKeyword(String keyword) {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("resultList");
		mv.addObject("filmList", filmDAO.findFilmsByKeyword(keyword));
		return mv;
	}

	@RequestMapping(path = "deleteFilm.do")
	public ModelAndView deleteFilm(int filmId) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("delete");
		mv.addObject("successful", filmDAO.deleteFilm(filmId));

		return mv;

	}

	@RequestMapping(path = "createFilm.do", method = RequestMethod.POST)
	public ModelAndView newFilm(@ModelAttribute("film") Film film, RedirectAttributes redir) {
		film = filmDAO.addFilm(film);
		ModelAndView mv = new ModelAndView();
		redir.addFlashAttribute("film", film);
		mv.setViewName("redirect:filmCreated.do");
		return mv;
	}

	@RequestMapping(path = "filmCreated.do", method = RequestMethod.GET)
	public ModelAndView createdFilm() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("film");
		mv.setViewName("added");
		return mv;
	}

	@RequestMapping(path = "FilmsUpdate.do", method = RequestMethod.POST, params = "title")
	public ModelAndView editFilmTitle(int id, String title) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("updated");
		mv.addObject("updatedFilmId", Integer.valueOf(id));

		mv.addObject("film", filmDAO.editFilm("title", title, id));
		return mv;
	}

	@RequestMapping(path = "FilmsUpdate.do", method = RequestMethod.POST, params = "description")
	public ModelAndView editFilmDescription(int id, String description) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("updated");
		mv.addObject("updatedFilmId", Integer.valueOf(id));

		mv.addObject("film", filmDAO.editFilm("description", description, id));
		return mv;
	}

	@RequestMapping(path = "FilmsUpdate.do", method = RequestMethod.POST, params = "releaseYear")
	public ModelAndView editReleaseYear(int id, String releaseYear) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("updated");
		mv.addObject("updatedFilmId", Integer.valueOf(id));

		mv.addObject("film", filmDAO.editFilm("release_year", releaseYear, id));
		return mv;
	}

	@RequestMapping(path = "FilmsUpdate.do", method = RequestMethod.POST, params = "rentalDuration")
	public ModelAndView editRentalDuration(int id, String rentalDuration) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("updated");
		mv.addObject("updatedFilmId", Integer.valueOf(id));

		mv.addObject("film", filmDAO.editFilm("rental_duration", rentalDuration, id));
		return mv;
	}

	@RequestMapping(path = "FilmsUpdate.do", method = RequestMethod.POST, params = "rentalRate")
	public ModelAndView editRentalRate(int id, String rentalRate) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("updated");
		mv.addObject("updatedFilmId", Integer.valueOf(id));

		mv.addObject("film", filmDAO.editFilm("rental_rate", rentalRate, id));
		return mv;
	}

	@RequestMapping(path = "FilmsUpdate.do", method = RequestMethod.POST, params = "replacementCost")
	public ModelAndView editReplacementCost(int id, String replacementCost) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("updated");
		mv.addObject("updatedFilmId", Integer.valueOf(id));

		mv.addObject("film", filmDAO.editFilm("replacement_cost", replacementCost, id));
		return mv;
	}

	@RequestMapping(path = "FilmsUpdate.do", method = RequestMethod.POST, params = "length")
	public ModelAndView editLength(int id, String length) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("updated");
		mv.addObject("updatedFilmId", Integer.valueOf(id));
		mv.addObject("film", filmDAO.editFilm("length", length, id));
		return mv;
	}

}
