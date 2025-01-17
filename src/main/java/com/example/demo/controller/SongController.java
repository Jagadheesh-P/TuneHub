package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.entities.Song;
import com.example.demo.services.SongService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SongController {

	@Autowired
	SongService service;

	@PostMapping("/addSong")
	public String addsong(@ModelAttribute Song song) {
		boolean songStatus = service.songExists(song.getName());
		if (songStatus == false) {
			service.addSong(song);
			System.out.println("Song added Successfully");
		} else {
			System.out.println("Song already Exists");
		}
		return "adminHome";

	}

	@GetMapping("/viewSongs")
	public String viewSongs(Model model) {

		List<Song> songsList = service.fetchAllSongs();

		model.addAttribute("songs", songsList);

		return "displaySongs";
	}

	@GetMapping("/playSongs")
	public String playSongs(Model model) {

		boolean premiumUser = false;

		if (premiumUser == true) {
			List<Song> songsList = service.fetchAllSongs();
			model.addAttribute("songs", songsList);
			return "displaySongs";
		} else {
			return "makePayment";
		}
	}

}
