/*
 * Copyright (c) 2018-2022 Caratacus, (caratacus@qq.com).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.study.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.study.common.utils.TypeUtils;
import com.study.enums.MenuTypeEnum;
import com.study.enums.StatusEnum;
import com.study.framework.enums.ErrorCodeEnum;
import com.study.framework.service.impl.BaseServiceImpl;
import com.study.framework.utils.ApiAssert;
import com.study.framework.utils.TreeUtils;
import com.study.mapper.MenuMapper;
import com.study.model.dto.MenuDTO;
import com.study.model.dto.MenuTreeDTO;
import com.study.model.entity.Menu;
import com.study.model.entity.MenuResource;
import com.study.service.IMenuResourceService;
import com.study.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author Caratacus
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private IMenuResourceService menuResourceService;

    @Override
    @Transactional
    public void saveMenu(Menu menu, List<String> resourceIds) {
        save(menu);
        if (CollectionUtils.isNotEmpty(resourceIds)) {
            Integer menuId = menu.getId();
            //添加resource关联
            menuResourceService.saveBatch(menuResourceService.getMenuResources(menuId, resourceIds)
            );
        }
    }

    @Override
    @Transactional
    public void updateMenu(Menu menu, List<String> resourceIds) {
        updateById(menu);
        if (CollectionUtils.isNotEmpty(resourceIds)) {
            Integer menuId = menu.getId();
            //删除resource关联
            menuResourceService.removeByMenuId(menuId);
            //添加resource关联
            menuResourceService.saveBatch(menuResourceService.getMenuResources(menuId, resourceIds)
            );
        }
    }

    @Override
    @Transactional
    public void removeMenu(Integer menuId) {
        if (parentIdNotNull(menuId)) {
            query().eq(Menu::getParentId, menuId)
                    .list()
                    .stream()
                    .filter(e -> parentIdNotNull(e.getParentId()))
                    .forEach(e -> removeMenu(e.getId()));
            //删除resource关联
            menuResourceService.removeByMenuId(menuId);
            //删除菜单
            removeById(menuId);
        }

    }

    @Override
    @Transactional
    public void updateStatus(Integer menuId, StatusEnum status) {
        Menu menu = getById(menuId);
        ApiAssert.notNull(ErrorCodeEnum.MENU_NOT_FOUND, menu);
        menu.setStatus(status);
        updateById(menu);
    }

    /**
     * 父ID不为0并且不为空
     *
     * @param parentId
     * @return
     */
    private boolean parentIdNotNull(Integer parentId) {
        return Objects.nonNull(parentId) && parentId != 0;
    }

    @Override
    public MenuDTO getMenuDTODetails(Integer menuId) {
        Menu menu = getById(menuId);
        ApiAssert.notNull(ErrorCodeEnum.MENU_NOT_FOUND, menu);
        MenuDTO menuDTO = menu.convert(MenuDTO.class);
        List<String> resourceIds = menuResourceService.query()
                .select(MenuResource::getResourceId)
                .eq(MenuResource::getMenuId, menuId)
                .listObjs(TypeUtils::castToString);
        menuDTO.setResourceIds(resourceIds);
        return menuDTO;
    }

    @Override
    public List<MenuTreeDTO> getUserPermMenus(Integer uid) {
        List<MenuTreeDTO> menus = baseMapper.getUserPermMenus(uid, StatusEnum.NORMAL, Arrays.asList(MenuTypeEnum.CATALOG, MenuTypeEnum.MENU));
        return menus.stream().filter(e -> !parentIdNotNull(e.getParentId())).map(e -> TreeUtils.findChildren(e, menus)).collect(Collectors.toList());
    }

    @Override
    public Set<String> getUserPermButtonAliases(Integer uid) {
        return baseMapper.getUserPermMenus(uid, StatusEnum.NORMAL, Collections.singletonList(MenuTypeEnum.BUTTON))
                .stream()
                .map(MenuTreeDTO::getAlias)
                .collect(Collectors.toSet());
    }
}
