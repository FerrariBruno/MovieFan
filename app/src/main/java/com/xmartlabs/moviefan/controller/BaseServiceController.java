package com.xmartlabs.moviefan.controller;

import com.xmartlabs.bigbang.core.model.EntityWithId;
import com.xmartlabs.bigbang.retrofit.controller.ServiceController;

/**
 * Created by bruno on 12/21/17.
 */
public class BaseServiceController<Id, E extends EntityWithId<Id>> extends ServiceController<Id, E> { }
