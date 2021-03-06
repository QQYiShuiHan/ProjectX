/*
 * Copyright (C) 2018 AlexMofer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package am.project.x.business.others.font;

import am.util.mvp.AMPresenter;

/**
 * Presenter
 */
class FontPresenter extends AMPresenter<FontView, FontModel> implements FontView, FontViewModel {

    private final FontModel mModel = new FontModel(this);

    FontPresenter(FontView view) {
        super(view);
    }

    @Override
    protected FontModel getModel() {
        return mModel;
    }

    // View
    @Override
    public void onLoadConfigFailure() {
        if (isDetachedFromView())
            return;
        getView().onLoadConfigFailure();
    }

    @Override
    public void onLoadConfigSuccess() {
        if (isDetachedFromView())
            return;
        getView().onLoadConfigSuccess();
    }

    // PickerViewModel
    @Override
    public String getDefaultFamilyName() {
        return getModel().getDefaultFamilyName();
    }

    @Override
    public int getFamilyNameOrAliaCount() {
        return getModel().getFamilyNameOrAliaCount();
    }

    @Override
    public String getFamilyNameOrAlia(int position) {
        return getModel().getFamilyNameOrAlia(position);
    }

    @Override
    public boolean isFamilyAlia(int position) {
        return getModel().isFamilyAlia(position);
    }
// AdapterViewModel

    // ViewModel
    @Override
    public void loadConfig() {
        getModel().loadConfig();
    }
}
