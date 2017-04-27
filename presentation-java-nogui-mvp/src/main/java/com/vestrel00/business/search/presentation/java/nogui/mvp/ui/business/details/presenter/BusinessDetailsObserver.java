/*
 * Copyright 2017 Vandolf Estrellado
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vestrel00.business.search.presentation.java.nogui.mvp.ui.business.details.presenter;

import com.vestrel00.business.search.domain.Business;
import com.vestrel00.business.search.presentation.java.model.mapper.ModelMapperFactory;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.business.details.view.BusinessDetailsView;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Observer for business details obtained from a use case.
 */
final class BusinessDetailsObserver extends DisposableSingleObserver<Business> {

    private final BusinessDetailsView view;
    private final ModelMapperFactory modelMapperFactory;

    BusinessDetailsObserver(BusinessDetailsView view, ModelMapperFactory modelMapperFactory) {
        this.view = view;
        this.modelMapperFactory = modelMapperFactory;
    }

    @Override
    public void onSuccess(@NonNull Business businesses) {
        Observable.just(businesses)
                .map(modelMapperFactory.businessModelMapper()::map)
                .subscribe(view::showBusiness);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        view.showError(e.getMessage());
    }
}
