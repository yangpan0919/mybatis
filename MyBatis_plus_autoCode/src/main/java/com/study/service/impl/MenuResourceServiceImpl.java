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

import com.study.framework.service.impl.BaseServiceImpl;
import com.study.mapper.MenuResourceMapper;
import com.study.model.entity.MenuResource;
import com.study.service.IMenuResourceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单资源关系表 服务实现类
 * </p>
 *
 * @author Caratacus
 */
@Service
public class MenuResourceServiceImpl extends BaseServiceImpl<MenuResourceMapper, MenuResource> implements IMenuResourceService {

    @Override
    public void removeByMenuId(Integer menuId) {
        delete().eq(MenuResource::getMenuId, menuId).execute();
    }

    @Override
    public List<MenuResource> getMenuResources(Integer menuId, List<String> resourceIds) {
        return resourceIds.stream().map(resourceId -> new MenuResource(menuId, resourceId)).collect(Collectors.toList());
    }

}
