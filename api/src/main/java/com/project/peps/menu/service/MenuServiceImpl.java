package com.project.peps.menu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.peps.menu.model.Menu;
import com.project.peps.menu.repository.MenuRepository;
import com.project.peps.shared.exception.ResourceNotFoundException;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<Menu> findAllMenus() {
        return menuRepository.findAll();
    }

    @Override
    public Menu findMenuById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu", "id", id));
    }

    @Override
    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public Menu deleteById(Long id) {
        Menu menu = findMenuById(id);
        menuRepository.delete(menu);
        return menu;
    }

    @Override
    public List<Menu> findMenusByUserId(Long userId) {
        return menuRepository.findByUserId(userId);
    }
}
