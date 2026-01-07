package com.project.peps.menu.controller;

import java.util.List;

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

import com.project.peps.menu.dto.MenuRequest;
import com.project.peps.menu.dto.MenuResponse;
import com.project.peps.menu.mapper.MenuMapper;
import com.project.peps.menu.model.Menu;
import com.project.peps.menu.service.MenuService;
import com.project.peps.user.model.User;
import com.project.peps.user.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/menus")
@CrossOrigin(origins = "*")
public class MenuController {

    private final MenuService menuService;
    private final UserService userService;
    private final MenuMapper menuMapper;

    public MenuController(MenuService menuService, UserService userService, MenuMapper menuMapper) {
        this.menuService = menuService;
        this.userService = userService;
        this.menuMapper = menuMapper;
    }

    @GetMapping
    public ResponseEntity<List<MenuResponse>> getAllMenus() {
        List<Menu> menus = menuService.findAllMenus();
        return ResponseEntity.ok(menuMapper.toResponseList(menus));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuResponse> getMenuById(@PathVariable Long id) {
        Menu menu = menuService.findMenuById(id);
        return ResponseEntity.ok(menuMapper.toResponse(menu));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MenuResponse>> getMenusByUserId(@PathVariable Long userId) {
        List<Menu> menus = menuService.findMenusByUserId(userId);
        return ResponseEntity.ok(menuMapper.toResponseList(menus));
    }

    @PostMapping
    public ResponseEntity<MenuResponse> createMenu(@Valid @RequestBody MenuRequest menuRequest) {
        User user = userService.findUserById(menuRequest.getUserId());
        Menu menu = menuMapper.toEntity(menuRequest);
        menu.setUser(user);
        Menu savedMenu = menuService.save(menu);
        return new ResponseEntity<>(menuMapper.toResponse(savedMenu), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuResponse> updateMenu(@PathVariable Long id, @Valid @RequestBody MenuRequest menuRequest) {
        Menu existingMenu = menuService.findMenuById(id);
        menuMapper.updateEntityFromRequest(menuRequest, existingMenu);
        
        // Update user if provided and different
        if (menuRequest.getUserId() != null && !menuRequest.getUserId().equals(existingMenu.getUser().getId())) {
             User newUser = userService.findUserById(menuRequest.getUserId());
             existingMenu.setUser(newUser);
        }

        Menu savedMenu = menuService.save(existingMenu);
        return ResponseEntity.ok(menuMapper.toResponse(savedMenu));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MenuResponse> deleteMenu(@PathVariable Long id) {
        Menu deletedMenu = menuService.deleteById(id);
        return ResponseEntity.ok(menuMapper.toResponse(deletedMenu));
    }
}
