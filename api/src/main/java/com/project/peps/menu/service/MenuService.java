package com.project.peps.menu.service;

import java.util.List;

import com.project.peps.menu.model.Menu;

public interface MenuService {

    List<Menu> findAllMenus();

    Menu findMenuById(Long id);

    Menu save(Menu menu);

    Menu deleteById(Long id);

    List<Menu> findMenusByUserId(Long userId);
}
