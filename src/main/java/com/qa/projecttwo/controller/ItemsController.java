package com.qa.projecttwo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.projecttwo.dto.ItemsDto;
import com.qa.projecttwo.persistence.domain.Items;
import com.qa.projecttwo.service.ItemsService;

@RestController
@CrossOrigin
@RequestMapping ("/items")
public class ItemsController {

	private ItemsService service;
	
	@Autowired
	public ItemsController(ItemsService itemsService) {
		super();
		this.service = itemsService;
	}
	
	//CREATE
	@PostMapping("/create")
	public ResponseEntity<ItemsDto> create(@RequestBody Items items){
 		ItemsDto created = this.service.create(items);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}
	
	//READ
	@GetMapping("/read")
	public ResponseEntity<List<ItemsDto>> read (){
		return ResponseEntity.ok(this.service.readAll());
	}
	
	//READ ID
	@GetMapping("/read/{id}")
	public ResponseEntity <ItemsDto> readSolo (@PathVariable Long id){
		return ResponseEntity.ok(this.service.readById(id));
	}
	
	//DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity <ItemsDto> delete (@PathVariable Long id){
		return this.service.delete(id)? new ResponseEntity <> (HttpStatus.NO_CONTENT): new ResponseEntity <> (HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//UPDATE
	@PutMapping("/update/{id}")
	public ResponseEntity <ItemsDto> update (@PathVariable Long id, @RequestBody ItemsDto itemsDto){
		return new ResponseEntity<>(this.service.update(itemsDto, id),HttpStatus.ACCEPTED);
	}
}
